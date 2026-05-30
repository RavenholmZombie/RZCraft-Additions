package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.content.other.AddHempLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers
{
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModReferences.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_HEMP =
            LOOT_MODIFIERS.register("add_hemp", () -> AddHempLootModifier.CODEC);
}