package com.gvarchives.rzadditions;

import com.gvarchives.rzadditions.core.ModItems;
import com.gvarchives.rzadditions.core.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = RZAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagDebugLogger
{
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event)
    {
        var server = event.getServer();

        Item tofu = ModItems.TOFU.get();
        boolean isMeat = tofu.builtInRegistryHolder().is(ModTags.Items.MEATS);

        RZAdditions.LOGGER.info("=== Tag Debug ===");
        RZAdditions.LOGGER.info("Is Tofu in forge:meats? -> " + isMeat);

        var tag = server.registryAccess().registryOrThrow(Registries.ITEM)
                .getTagOrEmpty(ModTags.Items.MEATS);

        RZAdditions.LOGGER.info("Items in #forge:meats:");
        tag.forEach(holder -> RZAdditions.LOGGER.info(" - " + holder.value().toString()));
        RZAdditions.LOGGER.info("==================");
    }
}
