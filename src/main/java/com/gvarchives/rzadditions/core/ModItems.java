package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.content.item.LevitationWandItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RZAdditions.MOD_ID);

    public static final RegistryObject<Item> TOFU = ITEMS.register("tofu",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)       // hunger restored
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryObject<Item> SOY_MASH = ITEMS.register("soy_mash",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)       // hunger restored
                    .saturationMod(0.3f)
                    .build())));

    public static final RegistryObject<Item> SOYBEANS = ITEMS.register("soybeans",
            () -> new ItemNameBlockItem(ModBlocks.SOYBEAN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> LEVITATION_WAND = ITEMS.register("levitation_wand",
            () -> new LevitationWandItem(new Item.Properties().stacksTo(1).durability(250)));


    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
