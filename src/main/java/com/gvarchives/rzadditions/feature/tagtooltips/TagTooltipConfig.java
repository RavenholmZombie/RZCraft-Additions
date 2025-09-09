package com.gvarchives.rzadditions.feature.tagtooltips;

import net.minecraftforge.common.ForgeConfigSpec;

public final class TagTooltipConfig
{
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.BooleanValue SHOW_WHEN_ADVANCED; // F3+H
    public static final ForgeConfigSpec.BooleanValue REQUIRE_SHIFT;
    public static final ForgeConfigSpec.BooleanValue SHOW_NAMESPACE;     // show "minecraft:", "forge:", etc.
    public static final ForgeConfigSpec.BooleanValue INCLUDE_BLOCK_TAGS; // for BlockItems
    public static final ForgeConfigSpec.BooleanValue INCLUDE_FLUID_TAGS; // for buckets

    static
    {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        b.push("tag_tooltips");
        ENABLED = b.comment("Enable tag tooltips feature")
                .define("enabled", true);
        SHOW_WHEN_ADVANCED = b.comment("Also show when Advanced Tooltips (F3+H) are on")
                .define("showWhenAdvanced", true);
        REQUIRE_SHIFT = b.comment("Require holding SHIFT to show")
                .define("requireShift", true);
        SHOW_NAMESPACE = b.comment("Include namespace (e.g., forge:, minecraft:)")
                .define("showNamespace", false);
        INCLUDE_BLOCK_TAGS = b.comment("Add block tags for BlockItems")
                .define("includeBlockTags", true);
        INCLUDE_FLUID_TAGS = b.comment("Add fluid tags for buckets")
                .define("includeFluidTags", true);
        b.pop();
        CLIENT_SPEC = b.build();
    }

    private TagTooltipConfig() {}
}
