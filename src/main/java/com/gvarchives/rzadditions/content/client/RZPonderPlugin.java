package com.gvarchives.rzadditions.content.client;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.content.client.ponders.SheetmetalPonderScenes;
import com.gvarchives.rzadditions.core.ModBlocks;
import com.gvarchives.rzadditions.core.ModReferences;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class RZPonderPlugin implements net.createmod.ponder.api.registration.PonderPlugin
{
    public RZPonderPlugin()
    {
        RZAdditions.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        RZAdditions.LOGGER.info("=");
        RZAdditions.LOGGER.info("= RZ's Additions " + ModReferences.VERSION);
        RZAdditions.LOGGER.info("= RZPonderPlugin() Ponder Plugin Registered. ");
        RZAdditions.LOGGER.info("=");
        RZAdditions.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    @Override
    public String getModId()
    {
        return ModReferences.MOD_ID;
    }



    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper)
    {
        RZAdditions.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        RZAdditions.LOGGER.info("=");
        RZAdditions.LOGGER.info("= RZ's Additions " + ModReferences.VERSION);
        RZAdditions.LOGGER.info("= Registering Ponder Scenes");
        RZAdditions.LOGGER.info("=");
        RZAdditions.LOGGER.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
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
                fromNamespaceAndPath(ModReferences.MOD_ID, "sheetmetal_dyeing"),
                SheetmetalPonderScenes::dyeing
        );
    }
}
