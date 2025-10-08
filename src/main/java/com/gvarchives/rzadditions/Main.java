package com.gvarchives.rzadditions;

import com.gvarchives.rzadditions.core.*;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MOD_ID)
public class Main
{
    public static final String MOD_ID = "rzadditions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Main()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSounds.SOUNDS.register(modEventBus);
        ModBlocks.BLOCKS.init();
        ModItems.ITEMS.init();

        ModFluidProperties.FLUID_PROPERTIES.initialize();
        ModFluids.FLUIDS.init();

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ModGamerules.JOIN_LEAVE_SOUNDS = GameRules.register(
                    "playJoinAndLeaveSounds",
                    GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(true)
            );
            LOGGER.info("Registered gamerule: playJoinAndLeaveSounds");
        });
    }
}
