package com.sickasian.magicshitmod.ModItems.custom;

import net.minecraft.world.item.Item;

import java.util.Properties;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
public class SpelEarthItem extends Item {
    public SpelEarthItem(Item.Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = target.level();

        if (!level.isClientSide) {
            // Use the entity's actual bounding box center, not blockPosition()
            AABB box = target.getBoundingBox();
            double centerX = (box.minX + box.maxX) / 2.0;
            double centerZ = (box.minZ + box.maxZ) / 2.0;
            double topY = box.maxY; // top of the entity's hitbox

            // Snap the center to the nearest block-grid alignment so the 3x3 sits evenly
            int originX = Mth.floor(centerX) - 1; // -1, 0, +1 around center block
            int originZ = Mth.floor(centerZ) - 1;
            int originY = Mth.ceil(topY) + 1;       // start 1 block above the entity's head

            for (int dx = 0; dx < 3; dx++) {
                for (int dz = 0; dz < 3; dz++) {
                    for (int dy = 0; dy < 2; dy++) {
                        BlockPos spawnPos = new BlockPos(
                                originX + dx,
                                originY + dy,
                                originZ + dz
                        );

                        FallingBlockEntity.fall(level, spawnPos, Blocks.GRAVEL.defaultBlockState());
                    }
                }
            }
        }



        return InteractionResult.SUCCESS;
    }
}
