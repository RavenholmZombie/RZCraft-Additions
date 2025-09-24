package com.gvarchives.rzadditions.feature.tagtooltips;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.core.ModItems;
import com.gvarchives.rzadditions.core.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagDebugLogger
{
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event)
    {
        var server = event.getServer();

        Item tofu = ModItems.TOFU.get();
        Item soybeans = ModItems.SOYBEANS.get();
        boolean isRawMeat = tofu.builtInRegistryHolder().is(ModTags.Items.RAW_MEAT);
        boolean isSeed = soybeans.builtInRegistryHolder().is(ModTags.Items.SEEDS);

        Main.LOGGER.info("=== RZCraft Additions Patent-Pending Tag Debuginator! ===");
        Main.LOGGER.info("Is Tofu in forge:raw_meat? -> " + isRawMeat);
        Main.LOGGER.info("Are Soybeans in forge:seeds? -> " + isSeed);

        var tagMeats = server.registryAccess().registryOrThrow(Registries.ITEM)
                .getTagOrEmpty(ModTags.Items.RAW_MEAT);
        var tagSeeds = server.registryAccess().registryOrThrow(Registries.ITEM)
                .getTagOrEmpty(ModTags.Items.SEEDS);

        Main.LOGGER.info("Items in #forge:raw_meat:");
        tagMeats.forEach(holder -> Main.LOGGER.info(" - " + holder.value().toString()));

        Main.LOGGER.info("Items in #forge:seeds:");
        tagSeeds.forEach(holder -> Main.LOGGER.info(" - " + holder.value().toString()));

        Main.LOGGER.info("==================");
    }
}
