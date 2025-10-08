package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    // Sounds
    public static final RegistryObject<SoundEvent> PLAYER_JOIN =
            SOUNDS.register("player_join", () -> SoundEvent.createVariableRangeEvent(fromNamespaceAndPath(Main.MOD_ID, "player_join")));
    public static final RegistryObject<SoundEvent> PLAYER_LEAVE =
            SOUNDS.register("player_leave", () -> SoundEvent.createVariableRangeEvent(fromNamespaceAndPath(Main.MOD_ID, "player_leave")));
    public static final RegistryObject<SoundEvent> DUCK =
            SOUNDS.register("duck", () -> SoundEvent.createVariableRangeEvent(fromNamespaceAndPath(Main.MOD_ID, "duck")));
}
