package com.gvarchives.rzadditions.content.client;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.core.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RZAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOYBEAN_CROP.get(), RenderType.cutout());
    }
}
