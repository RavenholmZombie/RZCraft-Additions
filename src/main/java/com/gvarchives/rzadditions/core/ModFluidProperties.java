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

    public static final FluidData SIMPLE_SYRUP = FLUID_PROPERTIES.register("simple_syrup", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/simple_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/simple_flow"))
            .viscosity(2500)
            .density(2500)
            .canConvertToSource(false));

    public static final FluidData THC_OIL = FLUID_PROPERTIES.register("thc_oil", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/thc_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/thc_flow"))
            .viscosity(3000)
            .density(3500)
            .canConvertToSource(false));

    public static final FluidData GEL_BASE = FLUID_PROPERTIES.register("gel_base", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/base_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/base_flow"))
            .viscosity(3000)
            .density(3500)
            .canConvertToSource(false));

    public static final FluidData ADDERALL_GEL = FLUID_PROPERTIES.register("adderall", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/adderall_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/adderall_flow"))
            .viscosity(3000)
            .density(3500)
            .canConvertToSource(false));

    public static final FluidData MELATONIN_GEL = FLUID_PROPERTIES.register("melatonin", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/melatonin_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/melatonin_flow"))
            .viscosity(3000)
            .density(3500)
            .canConvertToSource(false));

    public static final FluidData PARACETAMOL_GEL = FLUID_PROPERTIES.register("paracetamol", FluidProperties.create()
            .still(fromNamespaceAndPath(Main.MOD_ID, "block/paracetamol_still"))
            .flowing(fromNamespaceAndPath(Main.MOD_ID, "block/paracetamol_flow"))
            .viscosity(3000)
            .density(3500)
            .canConvertToSource(false));
}
