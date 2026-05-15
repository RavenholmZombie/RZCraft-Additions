package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.content.item.RubberDuck;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.FluidBucketItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Main.MOD_ID);

    public static final RegistryEntry<Item> TOFU = ITEMS.register("tofu",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)       // hunger restored
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryEntry<Item> SOY_MASH = ITEMS.register("soy_mash",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)       // hunger restored
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryEntry<Item> SOYBEANS = ITEMS.register("soybeans",
            () -> new ItemNameBlockItem(ModBlocks.SOYBEAN_CROP.get(), new Item.Properties()));

    public static final RegistryEntry<Item> RUBBER_DUCK = ITEMS.register("rubber_duck",
            () -> new RubberDuck(new Item.Properties().stacksTo(1)));

    public static final RegistryEntry<Item> SOY_MILK_BUCKET = ITEMS.register("soy_milk_bucket", () -> new FluidBucketItem(
            ModFluidProperties.SOY_MILK,
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
    );

    public static final RegistryEntry<Item> SIMPLE_SYRUP_BUCKET = ITEMS.register("simple_syrup_bucket", () -> new FluidBucketItem(
            ModFluidProperties.SIMPLE_SYRUP,
            new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
    );

    // Colored Sheetmetal BlockItems
    public static final RegistryEntry<Item> WHITE_SHEETMETAL_BLOCK = ITEMS.register("white_sheetmetal", () -> new BlockItem(ModBlocks.WHITE_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> ORANGE_SHEETMETAL_BLOCK = ITEMS.register("orange_sheetmetal", () -> new BlockItem(ModBlocks.ORANGE_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> LIME_SHEETMETAL_BLOCK = ITEMS.register("lime_sheetmetal", () -> new BlockItem(ModBlocks.LIME_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> MAGENTA_SHEETMETAL_BLOCK = ITEMS.register("magenta_sheetmetal", () -> new BlockItem(ModBlocks.MAGENTA_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> PINK_SHEETMETAL_BLOCK = ITEMS.register("pink_sheetmetal", () -> new BlockItem(ModBlocks.PINK_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> YELLOW_SHEETMETAL_BLOCK = ITEMS.register("yellow_sheetmetal", () -> new BlockItem(ModBlocks.YELLOW_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> LIGHT_BLUE_SHEETMETAL_BLOCK = ITEMS.register("light_blue_sheetmetal", () -> new BlockItem(ModBlocks.LIGHT_BLUE_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> SILVER_SHEETMETAL_BLOCK = ITEMS.register("light_gray_sheetmetal", () -> new BlockItem(ModBlocks.SILVER_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> GRAY_SHEETMETAL_BLOCK = ITEMS.register("gray_sheetmetal", () -> new BlockItem(ModBlocks.GRAY_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> CYAN_SHEETMETAL_BLOCK = ITEMS.register("cyan_sheetmetal", () -> new BlockItem(ModBlocks.CYAN_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> PURPLE_SHEETMETAL_BLOCK = ITEMS.register("purple_sheetmetal", () -> new BlockItem(ModBlocks.PURPLE_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> BROWN_SHEETMETAL_BLOCK = ITEMS.register("brown_sheetmetal", () -> new BlockItem(ModBlocks.BROWN_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> GREEN_SHEETMETAL_BLOCK = ITEMS.register("green_sheetmetal", () -> new BlockItem(ModBlocks.GREEN_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> RED_SHEETMETAL_BLOCK = ITEMS.register("red_sheetmetal", () -> new BlockItem(ModBlocks.RED_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> BLACK_SHEETMETAL_BLOCK = ITEMS.register("black_sheetmetal", () -> new BlockItem(ModBlocks.BLACK_SHEETMETAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryEntry<Item> BLUE_SHEETMETAL_BLOCK = ITEMS.register("blue_sheetmetal", () -> new BlockItem(ModBlocks.BLUE_SHEETMETAL_BLOCK.get(), new Item.Properties()));
}
