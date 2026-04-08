package net.veroxuniverse.what_lurks_between.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.veroxuniverse.what_lurks_between.registry.ModBlocks;
import net.veroxuniverse.what_lurks_between.registry.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WetMireMudBlock extends Block implements BucketPickup {
    private static final VoxelShape FALLING_SHAPE = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.9, 1.0);

    public WetMireMudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (living.getType().toShortString().contains("frog")) return;

            boolean isPanicking = Math.abs(living.zza) > 0.05F || Math.abs(living.xxa) > 0.05F || living.getDeltaMovement().y > 0;
            double multiplier = isPanicking ? 0.25D : 0.50D;
            entity.makeStuckInBlock(state, new Vec3(multiplier, isPanicking ? 1.2D : 0.1D, multiplier));

            if (!level.isClientSide) {
                living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 1, false, false, false));
                if (living.isOnFire()) living.clearFire();
            }

            if (level.isClientSide && level.random.nextInt(isPanicking ? 2 : 20) == 0) {
                level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.MIRE_MUD.get().defaultBlockState()),
                        entity.getX() + (level.random.nextDouble() - 0.5D), pos.getY() + 1.0D, entity.getZ() + (level.random.nextDouble() - 0.5D), 0, 0.08D, 0);
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext ecc && ecc.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.FEET).is(Items.LEATHER_BOOTS)) return Shapes.block();
            if (living.fallDistance > 2.5F) return FALLING_SHAPE;
            return Shapes.empty();
        }
        return Shapes.block();
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        return new ItemStack(ModItems.WET_MIRE_MUD_BUCKET.get());
    }

    @Override
    public Optional<net.minecraft.sounds.SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
}