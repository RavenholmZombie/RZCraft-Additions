package com.gvarchives.rzadditions.content.client;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.content.client.ponders.SheetmetalPonderScenes;
import com.gvarchives.rzadditions.core.ModBlocks;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class RZPonderPlugin implements net.createmod.ponder.api.registration.PonderPlugin
{
    public RZPonderPlugin()
    {
        Main.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        Main.LOGGER.info("=");
        Main.LOGGER.info("= RZ's Additions Create Ponder Plugin Registered!");
        Main.LOGGER.info("=");
        Main.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    @Override
    public String getModId()
    {
        return Main.MOD_ID;
    }



    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper)
    {
        Main.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        Main.LOGGER.info("=");
        Main.LOGGER.info("= Registering RZ Additions' Ponder Scenes");
        Main.LOGGER.info("=");
        Main.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        helper.forComponents(
                ModBlocks.WHITE_SHEETMETAL_BLOCK.getId(),
                ModBlocks.SILVER_SHEETMETAL_BLOCK.getId(),
                ModBlocks.GRAY_SHEETMETAL_BLOCK.getId(),
                ModBlocks.BLACK_SHEETMETAL_BLOCK.getId(),
                ModBlocks.BROWN_SHEETMETAL_BLOCK.getId(),
                ModBlocks.RED_SHEETMETAL_BLOCK.getId(),
                ModBlocks.ORANGE_SHEETMETAL_BLOCK.getId(),
                ModBlocks.YELLOW_SHEETMETAL_BLOCK.getId(),
                ModBlocks.LIME_SHEETMETAL_BLOCK.getId(),
                ModBlocks.GREEN_SHEETMETAL_BLOCK.getId(),
                ModBlocks.CYAN_SHEETMETAL_BLOCK.getId(),
                ModBlocks.LIGHT_BLUE_SHEETMETAL_BLOCK.getId(),
                ModBlocks.BLUE_SHEETMETAL_BLOCK.getId(),
                ModBlocks.PURPLE_SHEETMETAL_BLOCK.getId(),
                ModBlocks.MAGENTA_SHEETMETAL_BLOCK.getId(),
                ModBlocks.PINK_SHEETMETAL_BLOCK.getId()
        ).addStoryBoard(
                new ResourceLocation(Main.MOD_ID, "sheetmetal_dyeing"),
                SheetmetalPonderScenes::dyeing
        );
    }
}
