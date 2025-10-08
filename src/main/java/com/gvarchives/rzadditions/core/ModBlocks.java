package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.content.block.crops.SoybeanCropBlock;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.BotariumLiquidBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks
{
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, Main.MOD_ID);

    public static final RegistryEntry<Block> SOY_MILK = BLOCKS.register("soy_milk", () -> new BotariumLiquidBlock(ModFluidProperties.SOY_MILK, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryEntry<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop", () -> new SoybeanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
}
