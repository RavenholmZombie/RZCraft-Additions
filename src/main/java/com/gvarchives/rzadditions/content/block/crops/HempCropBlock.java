package com.gvarchives.rzadditions.content.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HempCropBlock extends SugarCaneBlock implements BonemealableBlock
{
    private static final int MAX_HEIGHT = 2;

    public HempCropBlock(Properties properties)
    {
        super(properties);
    }

    private int getColumnHeight(LevelReader level, BlockPos pos)
    {
        BlockPos bottom = pos;

        while (level.getBlockState(bottom.below()).is(this))
            bottom = bottom.below();

        int height = 1;
        BlockPos cursor = bottom.above();

        while (level.getBlockState(cursor).is(this))
        {
            height++;
            cursor = cursor.above();
        }

        return height;
    }

    private BlockPos getTopBlock(LevelReader level, BlockPos pos)
    {
        BlockPos top = pos;

        while (level.getBlockState(top.above()).is(this))
            top = top.above();

        return top;
    }

    private int ageForHeight(int height)
    {
        return switch (height)
        {
            case 1 -> 0;
            case 2 -> 3;
            case 3 -> 6;
            case 4 -> 9;
            case 5 -> 12;
            default -> 15;
        };
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        BlockPos top = getTopBlock(level, pos);

        if (!level.isEmptyBlock(top.above()))
            return;

        if (getColumnHeight(level, pos) >= MAX_HEIGHT)
            return;

        int age = level.getBlockState(top).getValue(AGE);

        if (age >= 15)
        {
            int currentHeight = getColumnHeight(level, pos);
            int newHeight = currentHeight + 1;

            level.setBlockAndUpdate(
                    top.above(),
                    defaultBlockState().setValue(AGE, ageForHeight(newHeight))
            );

            level.setBlock(top, level.getBlockState(top).setValue(AGE, ageForHeight(currentHeight)), 4);
        }
        else
        {
            level.setBlock(top, level.getBlockState(top).setValue(AGE, age + 1), 4);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient)
    {
        BlockPos top = getTopBlock(level, pos);
        return getColumnHeight(level, pos) < MAX_HEIGHT && level.isEmptyBlock(top.above());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
    {
        BlockPos top = getTopBlock(level, pos);

        if (getColumnHeight(level, pos) < MAX_HEIGHT && level.isEmptyBlock(top.above()))
        {
            int newHeight = getColumnHeight(level, pos) + 1;

            level.setBlockAndUpdate(
                    top.above(),
                    defaultBlockState().setValue(AGE, ageForHeight(newHeight))
            );
        }
    }
}