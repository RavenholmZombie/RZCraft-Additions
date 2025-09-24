package com.gvarchives.rzadditions.events;

import com.gvarchives.rzadditions.Main;
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
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(ModItems.SOYBEANS);
        }
    }
}
