package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.RZAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RZAdditions.MOD_ID);

    // Sounds
    public static final RegistryObject<SoundEvent> PLAYER_JOIN =
            SOUNDS.register("player_join", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RZAdditions.MOD_ID, "player_join")));
    public static final RegistryObject<SoundEvent> PLAYER_LEAVE =
            SOUNDS.register("player_leave", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RZAdditions.MOD_ID, "player_leave")));
}
