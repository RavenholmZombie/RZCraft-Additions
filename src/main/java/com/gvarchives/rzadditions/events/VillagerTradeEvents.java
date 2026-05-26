package com.gvarchives.rzadditions.events;

import com.gvarchives.rzadditions.Main;
import com.gvarchives.rzadditions.core.ModItems;
import com.gvarchives.rzadditions.core.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VillagerTradeEvents
{
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event)
    {
        if (event.getType() != ModVillagers.HERBALIST.get()) return;

        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        trades.get(1).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(ModItems.HEMP.get(), 4),
                16,
                2,
                0.05F
        ));

        trades.get(2).add((trader, random) -> new MerchantOffer(
                new ItemStack(ModItems.HEMP.get(), 8),
                new ItemStack(Items.EMERALD, 1),
                12,
                5,
                0.05F
        ));

        trades.get(3).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(ModItems.BLUNT.get(), 1),
                8,
                10,
                0.08F
        ));

        trades.get(4).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(ModItems.EMPTY_CAPSULE.get(), 3),
                10,
                12,
                0.08F
        ));

        trades.get(5).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModItems.PARACETAMOL_PILL.get(), 1),
                4,
                20,
                0.1F
        ));
    }
}
