package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers
{
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Main.MOD_ID);

    public static final RegistryObject<VillagerProfession> HERBALIST =
            VILLAGER_PROFESSIONS.register("herbalist", () -> new VillagerProfession(
                    "herbalist",
                    holder -> holder.value() == ModPoiTypes.HERBALIST_POI.get(),
                    holder -> holder.value() == ModPoiTypes.HERBALIST_POI.get(),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC
            ));
}
