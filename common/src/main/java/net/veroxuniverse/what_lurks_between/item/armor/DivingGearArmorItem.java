package net.veroxuniverse.what_lurks_between.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.veroxuniverse.veroxlib.VeroxLib;
import net.veroxuniverse.veroxlib.api.ISanityModifier;
import net.veroxuniverse.veroxlib.registry.ModAttributes;

public class DivingGearArmorItem extends ArmorItem implements ISanityModifier {

    public DivingGearArmorItem(Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(holder, type, properties);
    }

    @Override
    public float getSanityResistance(ItemStack itemStack) {
        return 0.025F;
    }

    @Override
    public float getSanityRegen(ItemStack stack) {
        return 0.025F;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers vanillaModifiers = super.getDefaultAttributeModifiers();
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        vanillaModifiers.modifiers().forEach(entry -> {
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        });

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(this.type.getSlot());

        double protectionValue = (double)getSanityResistance(ItemStack.EMPTY);
        if (protectionValue != 0) {
            builder.add(
                    ModAttributes.SANITY_RESISTANCE,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(VeroxLib.MOD_ID, "sanity_protection"),
                            protectionValue,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ),
                    slotGroup
            );
        }

        double regenValue = (double)getSanityRegen(ItemStack.EMPTY);
        if (regenValue != 0) {
            builder.add(
                    ModAttributes.SANITY_REGEN,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(VeroxLib.MOD_ID, "sanity_regeneration"),
                            regenValue,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ),
                    slotGroup
            );
        }

        return builder.build();
    }
}
