package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.content.block.crops.HempCropBlock;
import com.gvarchives.rzadditions.content.block.crops.SoybeanCropBlock;
import com.gvarchives.rzadditions.content.block.decor.ColoredSheetmetalBlock;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.BotariumLiquidBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks
{
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, ModReferences.MOD_ID);

    // Fluids
    public static final RegistryEntry<Block> SOY_MILK = BLOCKS.register("soy_milk", () -> new BotariumLiquidBlock(ModFluidProperties.SOY_MILK, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final RegistryEntry<Block> SIMPLE_SYRUP = BLOCKS.register("simple_syrup", () -> new BotariumLiquidBlock(ModFluidProperties.SIMPLE_SYRUP, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final RegistryEntry<Block> THC_OIL = BLOCKS.register("thc_oil", () -> new BotariumLiquidBlock(ModFluidProperties.THC_OIL, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryEntry<Block> GEL_BASE = BLOCKS.register("gel_base", () -> new BotariumLiquidBlock(ModFluidProperties.GEL_BASE, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryEntry<Block> ADDERALL_GEL = BLOCKS.register("adderall_gel", () -> new BotariumLiquidBlock(ModFluidProperties.ADDERALL_GEL, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_BLUE)));
    public static final RegistryEntry<Block> MELATONIN_GEL = BLOCKS.register("melatonin_gel", () -> new BotariumLiquidBlock(ModFluidProperties.MELATONIN_GEL, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_PURPLE)));
    public static final RegistryEntry<Block> PARACETAMOL_GEL = BLOCKS.register("paracetamol_gel", () -> new BotariumLiquidBlock(ModFluidProperties.PARACETAMOL_GEL, BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_ORANGE)));

    // Crops
    public static final RegistryEntry<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop", () -> new SoybeanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryEntry<Block> HEMP_CROP = BLOCKS.register("hemp_crop", () -> new HempCropBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)));

    // POI Blocks
    public static final RegistryEntry<Block> HERBALIST_TABLE = BLOCKS.register("herbalist_table", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE).noOcclusion().isViewBlocking((state, level, pos) -> false)));

    // Colored Sheetmetal Blocks
    public static final RegistryEntry<Block> WHITE_SHEETMETAL_BLOCK = BLOCKS.register("white_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.WHITE));
    public static final RegistryEntry<Block> ORANGE_SHEETMETAL_BLOCK = BLOCKS.register("orange_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.ORANGE));
    public static final RegistryEntry<Block> MAGENTA_SHEETMETAL_BLOCK = BLOCKS.register("magenta_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.MAGENTA));
    public static final RegistryEntry<Block> PINK_SHEETMETAL_BLOCK = BLOCKS.register("pink_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.PINK));
    public static final RegistryEntry<Block> YELLOW_SHEETMETAL_BLOCK = BLOCKS.register("yellow_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.YELLOW));
    public static final RegistryEntry<Block> LIME_SHEETMETAL_BLOCK = BLOCKS.register("lime_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.LIME));
    public static final RegistryEntry<Block> LIGHT_BLUE_SHEETMETAL_BLOCK = BLOCKS.register("light_blue_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.LIGHT_BLUE));
    public static final RegistryEntry<Block> SILVER_SHEETMETAL_BLOCK = BLOCKS.register("light_gray_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.LIGHT_GRAY));
    public static final RegistryEntry<Block> GRAY_SHEETMETAL_BLOCK = BLOCKS.register("gray_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.GRAY));
    public static final RegistryEntry<Block> CYAN_SHEETMETAL_BLOCK = BLOCKS.register("cyan_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.CYAN));
    public static final RegistryEntry<Block> PURPLE_SHEETMETAL_BLOCK = BLOCKS.register("purple_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.PURPLE));
    public static final RegistryEntry<Block> BROWN_SHEETMETAL_BLOCK = BLOCKS.register("brown_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.BROWN));
    public static final RegistryEntry<Block> GREEN_SHEETMETAL_BLOCK = BLOCKS.register("green_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.GREEN));
    public static final RegistryEntry<Block> RED_SHEETMETAL_BLOCK = BLOCKS.register("red_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.RED));
    public static final RegistryEntry<Block> BLACK_SHEETMETAL_BLOCK = BLOCKS.register("black_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.BLACK));
    public static final RegistryEntry<Block> BLUE_SHEETMETAL_BLOCK = BLOCKS.register("blue_sheetmetal", () -> new ColoredSheetmetalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), DyeColor.BLUE));
}
