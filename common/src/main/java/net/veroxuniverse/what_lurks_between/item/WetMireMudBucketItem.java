package net.veroxuniverse.what_lurks_between.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.block.Block;

public class WetMireMudBucketItem extends SolidBucketItem {
    public WetMireMudBucketItem(Block block, Properties properties) {
        super(block, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, properties);
    }
}