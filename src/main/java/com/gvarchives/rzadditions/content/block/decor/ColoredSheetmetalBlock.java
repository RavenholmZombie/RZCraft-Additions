package com.gvarchives.rzadditions.content.block.decor;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.world.level.block.state.BlockState;

public class ColoredSheetmetalBlock extends Block implements IWrenchable
{
    public ColoredSheetmetalBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, net.minecraft.world.item.context.UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (level.isClientSide) return InteractionResult.SUCCESS;

        if (context.getPlayer() instanceof ServerPlayer player)
        {
            ItemStack stack = new ItemStack(this.asItem());
            level.levelEvent(2001, pos, Block.getId(state));
            level.removeBlock(pos, false);
            if (!player.getInventory().add(stack)) player.drop(stack, false);
        }
        return InteractionResult.SUCCESS;
    }
}
