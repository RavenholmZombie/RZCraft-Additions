package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTabs
{
    private static ItemStack tabPlayerHead()
    {
        ItemStack stack = new ItemStack(Items.PLAYER_HEAD);

        CompoundTag tag = new CompoundTag();
        tag.putString("SkullOwner", "RavenholmZombie");
        stack.setTag(tag);

        return stack;
    }

    public static final ResourcefulRegistry<CreativeModeTab> CREATIVE_TABS =
            ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final RegistryEntry<CreativeModeTab> RZADDITIONS_TAB =
            CREATIVE_TABS.register("rzadditions_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.rzadditions"))
                            .icon(ModCreativeTabs::tabPlayerHead)
                            .displayItems((parameters, output) ->
                            {
                                // Foods
                                output.accept(ModItems.TOFU.get());
                                output.accept(ModItems.SOY_MASH.get());
                                output.accept(ModItems.SOYBEANS.get());

                                // Tools
                                output.accept(ModItems.PAINT_SCRAPER.get());

                                // Fun
                                output.accept(ModItems.RUBBER_DUCK.get());

                                // Fluids
                                output.accept(ModItems.SOY_MILK_BUCKET.get());
                                output.accept(ModItems.SIMPLE_SYRUP_BUCKET.get());

                                // Pills
                                output.accept(ModItems.ADDERALL_PILL.get());
                                output.accept(ModItems.MELATONIN_PILL.get());
                                output.accept(ModItems.PARACETAMOL_PILL.get());

                                // Sheetmetal
                                output.accept(ModItems.WHITE_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.ORANGE_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.MAGENTA_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.PINK_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.YELLOW_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.LIME_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.LIGHT_BLUE_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.SILVER_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.GRAY_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.CYAN_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.PURPLE_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.BROWN_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.GREEN_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.RED_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.BLACK_SHEETMETAL_BLOCK.get());
                                output.accept(ModItems.BLUE_SHEETMETAL_BLOCK.get());
                            })
                            .build()
            );
}
