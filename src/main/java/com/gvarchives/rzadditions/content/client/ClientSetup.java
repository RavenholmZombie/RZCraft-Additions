package com.gvarchives.rzadditions.content.client;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.core.ModBlocks;

import com.gvarchives.rzadditions.core.ModReferences;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ModReferences.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup
{
    private static boolean ponderRegistered = false;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOYBEAN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEMP_CROP.get(), RenderType.cutout());

        RZAdditions.LOGGER.info("Client Setup Completed.");

        if (!ponderRegistered)
        {
            PonderIndex.addPlugin(new RZPonderPlugin());
            ponderRegistered = true;
            RZAdditions.LOGGER.info("Registered RZAdditions Ponder plugin through PonderIndex.");
        }
    }
}
