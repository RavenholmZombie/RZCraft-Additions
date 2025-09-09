package com.gvarchives.rzadditions;

import com.gvarchives.rzadditions.core.ModItems;
import com.gvarchives.rzadditions.core.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RZAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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

        RZAdditions.LOGGER.info("=== RZCraft Additions Patent-Pending Tag Debuginator! ===");
        RZAdditions.LOGGER.info("Is Tofu in forge:raw_meat? -> " + isRawMeat);
        RZAdditions.LOGGER.info("Are Soybeans in forge:seeds? -> " + isSeed);

        var tagMeats = server.registryAccess().registryOrThrow(Registries.ITEM)
                .getTagOrEmpty(ModTags.Items.RAW_MEAT);
        var tagSeeds = server.registryAccess().registryOrThrow(Registries.ITEM)
                .getTagOrEmpty(ModTags.Items.SEEDS);

        RZAdditions.LOGGER.info("Items in #forge:raw_meat:");
        tagMeats.forEach(holder -> RZAdditions.LOGGER.info(" - " + holder.value().toString()));

        RZAdditions.LOGGER.info("Items in #forge:seeds:");
        tagSeeds.forEach(holder -> RZAdditions.LOGGER.info(" - " + holder.value().toString()));

        RZAdditions.LOGGER.info("==================");
    }
}
