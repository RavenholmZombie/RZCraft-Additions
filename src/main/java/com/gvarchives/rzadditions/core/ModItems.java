package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.RZAdditions;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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

//    public static RegistryObject<Item> registerBlockItem(String name, RegistryObject<? extends Item> block)
//    {
//        return ITEMS.register(name, () -> new BlockItem((Block) block.get(), new Item.Properties()));
//    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
