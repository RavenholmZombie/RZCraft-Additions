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
}
