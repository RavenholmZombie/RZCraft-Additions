package com.gvarchives.rzadditions.content.block.decor;

import com.gvarchives.rzadditions.core.ModBlocks;
import com.gvarchives.rzadditions.core.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ColoredSheetmetalBlock extends Block implements IWrenchable
{
    private final DyeColor color;
    public ColoredSheetmetalBlock(Properties properties, DyeColor color)
    {
        super(properties);
        this.color = color;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit)
    {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(ModItems.PAINT_SCRAPER.get()))
        {
            if (this.color == DyeColor.WHITE)
                return InteractionResult.PASS;

            if (!level.isClientSide)
            {
                level.levelEvent(2001, pos, Block.getId(state));

                level.setBlock(
                        pos,
                        ModBlocks.WHITE_SHEETMETAL_BLOCK.get().defaultBlockState(),
                        Block.UPDATE_ALL
                );

                level.playSound(
                        null,
                        pos,
                        SoundEvents.AXE_SCRAPE,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F
                );

                if (!player.getAbilities().instabuild)
                {
                    stack.hurtAndBreak(
                            1,
                            player,
                            p -> p.broadcastBreakEvent(hand)
                    );
                }
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (!(stack.getItem() instanceof DyeItem dyeItem))
            return InteractionResult.PASS;

        DyeColor newColor = dyeItem.getDyeColor();

        if (newColor == this.color)
            return InteractionResult.PASS;

        Block newBlock = getSheetmetalBlock(newColor);

        if (newBlock == null)
            return InteractionResult.PASS;

        if (!level.isClientSide)
        {
            level.setBlock(pos, newBlock.defaultBlockState(), Block.UPDATE_ALL);
            level.levelEvent(2001, pos, Block.getId(state));

            level.playSound(
                    null,
                    pos,
                    SoundEvents.SLIME_BLOCK_HIT,
                    SoundSource.BLOCKS,
                    0.8F,
                    0.9F + level.random.nextFloat() * 0.2F
            );

            if (!player.getAbilities().instabuild)
                stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static Block getSheetmetalBlock(DyeColor color)
    {
        return switch (color)
        {
            case WHITE -> ModBlocks.WHITE_SHEETMETAL_BLOCK.get();
            case LIGHT_GRAY -> ModBlocks.SILVER_SHEETMETAL_BLOCK.get();
            case GRAY -> ModBlocks.GRAY_SHEETMETAL_BLOCK.get();
            case BLACK -> ModBlocks.BLACK_SHEETMETAL_BLOCK.get();
            case BROWN -> ModBlocks.BROWN_SHEETMETAL_BLOCK.get();
            case RED -> ModBlocks.RED_SHEETMETAL_BLOCK.get();
            case ORANGE -> ModBlocks.ORANGE_SHEETMETAL_BLOCK.get();
            case YELLOW -> ModBlocks.YELLOW_SHEETMETAL_BLOCK.get();
            case LIME -> ModBlocks.LIME_SHEETMETAL_BLOCK.get();
            case GREEN -> ModBlocks.GREEN_SHEETMETAL_BLOCK.get();
            case CYAN -> ModBlocks.CYAN_SHEETMETAL_BLOCK.get();
            case LIGHT_BLUE -> ModBlocks.LIGHT_BLUE_SHEETMETAL_BLOCK.get();
            case BLUE -> ModBlocks.BLUE_SHEETMETAL_BLOCK.get();
            case PURPLE -> ModBlocks.PURPLE_SHEETMETAL_BLOCK.get();
            case MAGENTA -> ModBlocks.MAGENTA_SHEETMETAL_BLOCK.get();
            case PINK -> ModBlocks.PINK_SHEETMETAL_BLOCK.get();
        };
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        if (context.getPlayer() instanceof ServerPlayer player)
        {
            ItemStack stack = new ItemStack(this.asItem());

            level.levelEvent(2001, pos, Block.getId(state));
            level.removeBlock(pos, false);

            if (!player.getInventory().add(stack))
                player.drop(stack, false);
        }

        return InteractionResult.SUCCESS;
    }
}
