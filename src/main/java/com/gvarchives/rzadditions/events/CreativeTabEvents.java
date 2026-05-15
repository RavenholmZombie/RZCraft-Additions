package com.gvarchives.rzadditions.events;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.core.ModBlocks;
import com.gvarchives.rzadditions.core.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabEvents
{
    @SubscribeEvent
    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
        {
            event.accept(ModItems.TOFU);
            event.accept(ModItems.SOY_MASH);
            event.accept(ModItems.SOY_MILK_BUCKET);
            event.accept(ModItems.SIMPLE_SYRUP_BUCKET);
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(ModItems.SOYBEANS);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(ModItems.RUBBER_DUCK);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(ModItems.PAINT_SCRAPER);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(ModItems.WHITE_SHEETMETAL_BLOCK);
            event.accept(ModItems.BLACK_SHEETMETAL_BLOCK);
            event.accept(ModItems.RED_SHEETMETAL_BLOCK);
            event.accept(ModItems.GREEN_SHEETMETAL_BLOCK);
            event.accept(ModItems.GRAY_SHEETMETAL_BLOCK);
            event.accept(ModItems.SILVER_SHEETMETAL_BLOCK);
            event.accept(ModItems.LIME_SHEETMETAL_BLOCK);
            event.accept(ModItems.ORANGE_SHEETMETAL_BLOCK);
            event.accept(ModItems.BLUE_SHEETMETAL_BLOCK);
            event.accept(ModItems.MAGENTA_SHEETMETAL_BLOCK);
            event.accept(ModItems.PINK_SHEETMETAL_BLOCK);
            event.accept(ModItems.YELLOW_SHEETMETAL_BLOCK);
            event.accept(ModItems.LIGHT_BLUE_SHEETMETAL_BLOCK);
            event.accept(ModItems.CYAN_SHEETMETAL_BLOCK);
            event.accept(ModItems.PURPLE_SHEETMETAL_BLOCK);
            event.accept(ModItems.BROWN_SHEETMETAL_BLOCK);
        }
    }
}
