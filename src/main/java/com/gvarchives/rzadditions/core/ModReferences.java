package com.gvarchives.rzadditions.core;

import net.minecraftforge.fml.ModList;

public class ModReferences
{
    public static final String MOD_ID = "rzadditions";

    public static final String VERSION = ModList.get()
        .getModContainerById(MOD_ID)
        .orElseThrow()
        .getModInfo()
        .getVersion()
        .toString();
}
