package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.content.item.PaintScraper;
import com.gvarchives.rzadditions.content.item.Pill;
import com.gvarchives.rzadditions.content.item.RubberDuck;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.FluidBucketItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;

import java.util.List;

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

    public static final RegistryEntry<Item> PAINT_SCRAPER = ITEMS.register("paint_scraper",
            () -> new PaintScraper(new Item.Properties().stacksTo(1).durability(128)));

    public static final RegistryEntry<Item> TAB_ICON = ITEMS.register("icon",
            () -> new Item(new Item.Properties()));

    // Pills
    public static final RegistryEntry<Item> ADDERALL_PILL = ITEMS.register("adderall",
            () -> new Pill(
                    new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(0)
                            .saturationMod(0.0F)
                            .alwaysEat()
                            .build()),
                    List.of(
                            () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 60, 3),
                            () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60, 1),
                            () -> new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 60, 3)
                    ),
                    null
            ));
    public static final RegistryEntry<Item> MELATONIN_PILL = ITEMS.register("melatonin",
            () -> new Pill(
                    new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(0)
                            .saturationMod(0.0F)
                            .alwaysEat()
                            .build()),
                    List.of(
                            () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 60, 4),
                            () -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 60, 5),
                            () -> new MobEffectInstance(MobEffects.DARKNESS, 20 * 60, 3)
                    ),
                    player -> {
                        player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

                        player.displayClientMessage(
                                Component.literal("You feel well-rested."),
                                true
                        );
                    }
            ));
    public static final RegistryEntry<Item> PARACETAMOL_PILL = ITEMS.register("paracetamol",
            () -> new Pill(
                    new Item.Properties().food(new FoodProperties.Builder()
                            .nutrition(0)
                            .saturationMod(0.0F)
                            .alwaysEat()
                            .build()),
                    List.of(
                            () -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 60, 2),
                            () -> new MobEffectInstance(MobEffects.JUMP, 20 * 60, 3),
                            () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 60, 3)
                    ),
                    player -> {
                        player.getActiveEffects().stream()
                                .filter(effect -> !effect.getEffect().isBeneficial())
                                .map(effect -> effect.getEffect())
                                .toList()
                                .forEach(player::removeEffect);
                        player.displayClientMessage(Component.literal("The aching is starting to dull."), true);
                    }
            ));

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
