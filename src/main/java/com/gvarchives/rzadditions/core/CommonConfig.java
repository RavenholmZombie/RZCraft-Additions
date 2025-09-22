package com.gvarchives.rzadditions.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLE_JOIN_LEAVE_SOUNDS;
    public static final ForgeConfigSpec.ConfigValue<String> JOIN_SOUND;
    public static final ForgeConfigSpec.ConfigValue<String> LEAVE_SOUND;

    static {
        BUILDER.push("Player Join/Leave Sounds");

        ENABLE_JOIN_LEAVE_SOUNDS = BUILDER
                .comment("Enable or disable join/leave sounds")
                .define("enableJoinLeaveSounds", true);

        JOIN_SOUND = BUILDER
                .comment("Sound ID to play when a player joins")
                .define("joinSound", "minecraft:entity.player.levelup");

        LEAVE_SOUND = BUILDER
                .comment("Sound ID to play when a player leaves")
                .define("leaveSound", "minecraft:entity.villager.no");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
