package net.veroxuniverse.what_lurks_between.sanity;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.veroxuniverse.what_lurks_between.api.ISanityCondition;
import net.veroxuniverse.what_lurks_between.api.SanityAPI;
import net.veroxuniverse.what_lurks_between.config.SanityConfig;
import net.veroxuniverse.what_lurks_between.network.SanityNetworking;
import net.veroxuniverse.what_lurks_between.registry.ModAttributes;
import net.veroxuniverse.what_lurks_between.registry.ModMobEffects;
import net.veroxuniverse.what_lurks_between.util.LightExtinguisher;

public class SanityEventHandler {

    private static boolean debugEnabled = false;

    private static final String[] SLEEP_MESSAGE_KEYS = {
            "message.what_lurks_between.sleep_1",
            "message.what_lurks_between.sleep_2",
            "message.what_lurks_between.sleep_3",
            "message.what_lurks_between.sleep_4",
            "message.what_lurks_between.sleep_5",
            "message.what_lurks_between.sleep_6"
    };

    private static final String[] CULTIST_SLEEP_MESSAGE_KEYS = {
            "message.what_lurks_between.sleep_cultist_1",
            "message.what_lurks_between.sleep_cultist_2",
            "message.what_lurks_between.sleep_cultist_3"
    };

    public static void init() {
        TickEvent.PLAYER_POST.register(SanityEventHandler::onPlayerTick);

        PlayerEvent.PLAYER_JOIN.register(player -> {
            if (player instanceof ServerPlayer sp) {
                SanityNetworking.syncToClient(sp, SanityAPI.getSanity(sp));
            }
        });

        PlayerEvent.PLAYER_RESPAWN.register((player, atCheckpoint, status) -> {
            if (SanityAPI.getSanity(player) <= 0.1f) {
                AttributeInstance corruption = player.getAttribute(ModAttributes.CORRUPTION);
                if (corruption != null) {
                    double newValue = Math.min(100.0, corruption.getBaseValue() + 5.0);
                    corruption.setBaseValue(newValue);
                    player.sendSystemMessage(Component.translatable("message.what_lurks_between.corruption_increased")
                            .withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC));
                }
            }
        });

        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
            var baseCmd = Commands.literal("wlb").requires(s -> s.hasPermission(2));

            baseCmd.then(Commands.literal("sanity")
                    .then(Commands.literal("set").then(Commands.argument("value", FloatArgumentType.floatArg(0, 100)).executes(c -> {
                        ServerPlayer p = c.getSource().getPlayerOrException();
                        float value = FloatArgumentType.getFloat(c, "value");
                        SanityAPI.modifySanity(p, value - SanityAPI.getSanity(p));
                        c.getSource().sendSuccess(() -> Component.literal("§aSanity set to: " + String.format("%.1f", SanityAPI.getSanity(p))), true);
                        return 1;
                    })))
                    .then(Commands.literal("get").executes(c -> {
                        ServerPlayer p = c.getSource().getPlayerOrException();
                        c.getSource().sendSuccess(() -> Component.literal("§eCurrent Sanity: " + String.format("%.1f", SanityAPI.getSanity(p))), false);
                        return 1;
                    })));

            baseCmd.then(Commands.literal("corruption")
                    .then(Commands.literal("set").then(Commands.argument("value", DoubleArgumentType.doubleArg(0, 100)).executes(c -> {
                        ServerPlayer p = c.getSource().getPlayerOrException();
                        double value = DoubleArgumentType.getDouble(c, "value");
                        AttributeInstance inst = p.getAttribute(ModAttributes.CORRUPTION);
                        if (inst != null) inst.setBaseValue(value);
                        c.getSource().sendSuccess(() -> Component.literal("§dCorruption set to: " + value + "%"), true);
                        return 1;
                    })))
                    .then(Commands.literal("get").executes(c -> {
                        ServerPlayer p = c.getSource().getPlayerOrException();
                        double val = p.getAttributeValue(ModAttributes.CORRUPTION);
                        c.getSource().sendSuccess(() -> Component.literal("§dCurrent Corruption: " + String.format("%.1f", val) + "%"), false);
                        return 1;
                    })));

            baseCmd.then(Commands.literal("cultist")
                    .then(Commands.literal("on").executes(c -> {
                        SanityAPI.setCultist(c.getSource().getPlayerOrException(), true);
                        c.getSource().sendSuccess(() -> Component.literal("§dCultist mode enabled!"), true);
                        return 1;
                    }))
                    .then(Commands.literal("off").executes(c -> {
                        SanityAPI.setCultist(c.getSource().getPlayerOrException(), false);
                        c.getSource().sendSuccess(() -> Component.literal("§7Cultist mode disabled."), true);
                        return 1;
                    })));

            baseCmd.then(Commands.literal("debug")
                    .then(Commands.literal("on").executes(c -> {
                        debugEnabled = true;
                        c.getSource().sendSuccess(() -> Component.literal("§6Sanity Debug: §aEnabled"), true);
                        return 1;
                    }))
                    .then(Commands.literal("off").executes(c -> {
                        debugEnabled = false;
                        c.getSource().sendSuccess(() -> Component.literal("§6Sanity Debug: §cDisabled"), true);
                        return 1;
                    })));

            baseCmd.then(Commands.literal("reload").executes(c -> {
                AutoConfig.getConfigHolder(SanityConfig.class).load();
                SanityConfig.INSTANCE = AutoConfig.getConfigHolder(SanityConfig.class).getConfig();
                c.getSource().sendSuccess(() -> Component.literal("§aConfig reloaded from file!"), true);
                return 1;
            }));

            dispatcher.register(baseCmd);
            dispatcher.register(Commands.literal("whatlurksbetween").redirect(dispatcher.getRoot().getChild("wlb")));
        });
    }

    private static void onPlayerTick(Player player) {
        if (!player.level().isClientSide) {
            if (player.isSleeping() && player.getSleepTimer() == 100) {
                if (!SanityConditionManager.isBlocked(player, ISanityCondition.ConditionType.RESET)) {
                    float current = SanityAPI.getSanity(player);
                    boolean isCultist = SanityAPI.isCultist(player);

                    if (current < 100f) {
                        float amountToHeal;
                        if (SanityConfig.INSTANCE.sleepResetsCompletely) {
                            amountToHeal = 100f - current;
                        } else {
                            amountToHeal = Math.min(SanityConfig.INSTANCE.sanityGainFromSleep, 100f - current);
                        }

                        if (amountToHeal > 0) {
                            SanityAPI.modifySanity(player, amountToHeal);
                            String randomKey;
                            ChatFormatting color;

                            if (isCultist) {
                                randomKey = CULTIST_SLEEP_MESSAGE_KEYS[player.getRandom().nextInt(CULTIST_SLEEP_MESSAGE_KEYS.length)];
                                color = ChatFormatting.DARK_PURPLE;
                            } else {
                                randomKey = SLEEP_MESSAGE_KEYS[player.getRandom().nextInt(SLEEP_MESSAGE_KEYS.length)];
                                color = ChatFormatting.GREEN;
                            }
                            player.sendSystemMessage(Component.translatable(randomKey).withStyle(color));
                        }
                    }
                }
            }

            var darknessEffectHolder = player.level().registryAccess()
                    .registryOrThrow(Registries.MOB_EFFECT)
                    .getHolderOrThrow(ModMobEffects.ABSOLUTE_DARKNESS.getKey());

            if (player.hasEffect(darknessEffectHolder)) {
                if (player.tickCount % 20 == 0 && SanityConfig.INSTANCE.absoluteDarkness.extinguishLamps) {
                    LightExtinguisher.extinguishAroundPlayer(player, SanityConfig.INSTANCE.absoluteDarkness.radius);
                }
            }

            if (player.tickCount % 20 == 0) {
                handleSanityLogic(player);
            }
        }

        if (player.tickCount % 20 == 0 && SanityConfig.INSTANCE.enableSanityEffects) {
            SanityEffectManager.tick(player);
        }
    }

    private static void handleSanityLogic(Player player) {
        int light = player.level().getMaxLocalRawBrightness(player.blockPosition());
        boolean cultist = SanityAPI.isCultist(player);
        float modifier = SanityAPI.getSanityModifier(player);

        double corruption = player.getAttributeValue(ModAttributes.CORRUPTION);
        float corruptionMult = 1.0f + (float)(corruption / 100.0);

        if (light < SanityConfig.INSTANCE.darknessThreshold) {
            if (!SanityConditionManager.isBlocked(player, ISanityCondition.ConditionType.DECREASE)) {
                SanityAPI.modifySanity(player, SanityConfig.INSTANCE.sanityReduction * modifier * corruptionMult);
            }
        } else if (light > SanityConfig.INSTANCE.brightnessThreshold) {
            if (!SanityConditionManager.isBlocked(player, ISanityCondition.ConditionType.INCREASE)) {
                SanityAPI.modifySanity(player, SanityConfig.INSTANCE.sanityGain * modifier);
            }
        }

        if (debugEnabled) {
            String modeInfo = cultist ? "§d[Cultist Mode]" : "§b[Human Mode]";
            player.displayClientMessage(
                    Component.literal("§eSanity: §f" + String.format("%.1f", SanityAPI.getSanity(player)) +
                            " §8| §dCorr: §f" + String.format("%.1f", corruption) + "%" +
                            " §8| §eLight: §f" + light + " §8| §6Mod: §f" + String.format("%.1f", modifier) + " " + modeInfo),
                    true
            );
        }
    }
}