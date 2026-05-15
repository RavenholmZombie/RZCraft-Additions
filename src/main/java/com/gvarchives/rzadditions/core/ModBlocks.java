package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.content.block.crops.SoybeanCropBlock;
import com.gvarchives.rzadditions.content.block.decor.BasicDecorativeBlock;
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
    public static final RegistryEntry<Block> SIMPLE_SYRUP = BLOCKS.register("simple_syrup", () -> new BotariumLiquidBlock(ModFluidProperties.SIMPLE_SYRUP, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.TERRACOTTA_WHITE)));

    public static final RegistryEntry<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop", () -> new SoybeanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    // Colored Sheetmetal Blocks
    public static final RegistryEntry<Block> WHITE_SHEETMETAL_BLOCK = BLOCKS.register("white_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> ORANGE_SHEETMETAL_BLOCK = BLOCKS.register("orange_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> MAGENTA_SHEETMETAL_BLOCK = BLOCKS.register("magenta_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> PINK_SHEETMETAL_BLOCK = BLOCKS.register("pink_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> YELLOW_SHEETMETAL_BLOCK = BLOCKS.register("yellow_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> LIME_SHEETMETAL_BLOCK = BLOCKS.register("lime_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> LIGHT_BLUE_SHEETMETAL_BLOCK = BLOCKS.register("light_blue_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> SILVER_SHEETMETAL_BLOCK = BLOCKS.register("light_gray_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> GRAY_SHEETMETAL_BLOCK = BLOCKS.register("gray_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> CYAN_SHEETMETAL_BLOCK = BLOCKS.register("cyan_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> PURPLE_SHEETMETAL_BLOCK = BLOCKS.register("purple_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> BROWN_SHEETMETAL_BLOCK = BLOCKS.register("brown_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> GREEN_SHEETMETAL_BLOCK = BLOCKS.register("green_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> RED_SHEETMETAL_BLOCK = BLOCKS.register("red_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> BLACK_SHEETMETAL_BLOCK = BLOCKS.register("black_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryEntry<Block> BLUE_SHEETMETAL_BLOCK = BLOCKS.register("blue_sheetmetal", () -> new BasicDecorativeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
}
