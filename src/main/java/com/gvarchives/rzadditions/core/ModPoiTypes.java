package com.gvarchives.rzadditions.core;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModPoiTypes
{
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ModReferences.MOD_ID);

    public static final RegistryObject<PoiType> HERBALIST_POI =
            POI_TYPES.register("herbalist_poi", () -> new PoiType(
                    getBlockStates(ModBlocks.HERBALIST_TABLE.get()),
                    1,
                    1
            ));

    private static Set<BlockState> getBlockStates(net.minecraft.world.level.block.Block block)
    {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
