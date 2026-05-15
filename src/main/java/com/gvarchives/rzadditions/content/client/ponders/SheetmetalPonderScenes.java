package com.gvarchives.rzadditions.content.client.ponders;

import com.gvarchives.rzadditions.core.ModBlocks;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;

public class SheetmetalPonderScenes
{
    public static void dyeing(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("sheetmetal_dyeing", "Dyeing Sheetmetal Blocks");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        BlockPos sheetmetal = util.grid().at(2, 1, 2);

        scene.world().setBlock(sheetmetal, ModBlocks.WHITE_SHEETMETAL_BLOCK.get().defaultBlockState(), false);
        scene.world().showSection(util.select().position(sheetmetal), net.minecraft.core.Direction.DOWN);

        scene.idle(20);

        scene.overlay().showText(70)
                .text("Sheetmetal blocks can be recolored directly in-world.")
                .pointAt(util.vector().centerOf(sheetmetal))
                .placeNearTarget();

        scene.idle(80);

        scene.overlay().showControls(util.vector().topOf(sheetmetal), Pointing.DOWN, 45)
                .rightClick()
                .withItem(Items.RED_DYE.getDefaultInstance());

        scene.idle(15);

        scene.world().setBlock(sheetmetal, ModBlocks.RED_SHEETMETAL_BLOCK.get().defaultBlockState(), true);

        scene.effects().indicateSuccess(sheetmetal);

        scene.overlay().showText(70)
                .text("Right-click with a dye to lather on a new color.")
                .pointAt(util.vector().centerOf(sheetmetal))
                .placeNearTarget();

        scene.idle(80);

        scene.overlay().showControls(util.vector().topOf(sheetmetal), Pointing.DOWN, 45)
                .rightClick()
                .withItem(Items.GREEN_DYE.getDefaultInstance());

        scene.idle(15);

        scene.world().setBlock(sheetmetal, ModBlocks.GREEN_SHEETMETAL_BLOCK.get().defaultBlockState(), true);

        scene.effects().indicateSuccess(sheetmetal);

        scene.overlay().showText(70)
                .text("The block is swapped to the matching sheetmetal color.")
                .pointAt(util.vector().centerOf(sheetmetal))
                .placeNearTarget();

        scene.idle(80);

        scene.markAsFinished();
    }
}
