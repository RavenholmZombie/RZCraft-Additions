package com.gvarchives.rzadditions.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags
{
    public static class Items
    {
        public static final TagKey<Item> MEATS =
                ItemTags.create(new ResourceLocation("forge", "meats"));
    }
}
