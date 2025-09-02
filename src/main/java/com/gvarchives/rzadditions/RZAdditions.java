package com.gvarchives.rzadditions;

import com.gvarchives.rzadditions.core.ModBlocks;
import com.gvarchives.rzadditions.core.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
    }
}
