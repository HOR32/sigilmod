package com.sickasian.magicshitmod.ModItems.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class SpelBookItem extends Item {

    public static int selected = -1;

    public SpelBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new SelectScreen());
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.level().isClientSide) return super.hurtEnemy(stack, target, attacker);

        switch (selected) {
            case 0 -> castFire(target);
            case 1 -> castIce(target);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    private void castFire(LivingEntity target) {
        Level level = target.level();
        BlockPos feet = target.blockPosition();

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = feet.offset(x, 0, z);
                if (level.isEmptyBlock(pos)) {
                    level.setBlock(pos, BaseFireBlock.getState(level, pos), 3);
                }
            }
        }
    }

    private void castIce(LivingEntity target) {
        AABB box = target.getBoundingBox();
        double centerX = (box.minX + box.maxX) / 2.0;
        double centerZ = (box.minZ + box.maxZ) / 2.0;
        double topY = box.maxY; // top of the entity's hitbox

        // Snap the center to the nearest block-grid alignment so the 3x3 sits evenly
        int originX = Mth.floor(centerX) - 1; // -1, 0, +1 around center block
        int originZ = Mth.floor(centerZ) - 1;
        int originY = Mth.ceil(topY) + 1;       //
        for(int x = 0; x<=3; x++){
            for(int z = 0; z<3; z++){
                for(int y =0; y<3; y++){
                    BlockPos spawnpos = new BlockPos(
                            originX+x,
                            originY+y,
                            originZ+z

                    );
                    FallingBlockEntity xx = FallingBlockEntity.fall(target.level(), spawnpos, Blocks.ICE.defaultBlockState());

                }
            }
        }


    }
}