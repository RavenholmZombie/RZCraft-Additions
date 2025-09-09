package com.gvarchives.rzadditions.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import com.gvarchives.rzadditions.core.ModItems;

public class SoybeanCropBlock extends CropBlock
{
    public SoybeanCropBlock()
    {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
    }

    @Override
    protected Item getBaseSeedId()
    {
        return ModItems.SOYBEANS.get();
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.CROP; // behaves like wheat
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        super.randomTick(state, world, pos, random);
    }

    @Override
    public int getMaxAge()
    {
        return MAX_AGE;
    }
}
