package com.gvarchives.rzadditions;

import com.gvarchives.rzadditions.core.ModBlocks;
import com.gvarchives.rzadditions.core.ModItems;
import com.gvarchives.rzadditions.feature.tagtooltips.TagTooltipConfig;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RZAdditions.MOD_ID)
public class RZAdditions
{
    public static final String MOD_ID = "rzadditions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public RZAdditions()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.CLIENT,
                TagTooltipConfig.CLIENT_SPEC
        );
    }
}
