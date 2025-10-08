package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import earth.terrarium.botarium.common.registry.fluid.FluidData;
import earth.terrarium.botarium.common.registry.fluid.FluidProperties;
import earth.terrarium.botarium.common.registry.fluid.FluidRegistry;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModFluidProperties
{
    public static final FluidRegistry FLUID_PROPERTIES = new FluidRegistry(Main.MOD_ID);

    public static final FluidData SOY_MILK = FLUID_PROPERTIES.register("soy_milk", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/soy_milk_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/soy_milk_flow"))
            .viscosity(2000)
            .density(2000)
            .canConvertToSource(false));
}
