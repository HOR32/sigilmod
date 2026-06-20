package com.sickasian.magicshitmod.ModItems.custom;

import com.sickasian.magicshitmod.ModItems.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraft.world.level.block.Blocks;

public class SpelFireItem extends Item {
    public SpelFireItem(Properties properties) {
        super(properties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            BlockPos pos = pPlayer.blockPosition().relative(pPlayer.getDirection(), 2);
            for (int i = 3; i < 10; i++){
                pLevel.setBlock(pos, Blocks.FIRE.defaultBlockState(), i);
             }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
