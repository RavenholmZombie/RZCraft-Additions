package com.gvarchives.rzadditions.core;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.BotariumFlowingFluid;
import earth.terrarium.botarium.common.registry.fluid.BotariumSourceFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;

public class ModFluids
{
    public static final ResourcefulRegistry<Fluid> FLUIDS = ResourcefulRegistries.create(BuiltInRegistries.FLUID, ModReferences.MOD_ID);

    public static final RegistryEntry<Fluid> SOY_MILK = FLUIDS.register("soy_milk", () -> new BotariumSourceFluid(ModFluidProperties.SOY_MILK));
    public static final RegistryEntry<Fluid> FLOWING_SOY_MILK = FLUIDS.register("flowing_soy_milk", () -> new BotariumFlowingFluid(ModFluidProperties.SOY_MILK));

    public static final RegistryEntry<Fluid> SIMPLE_SYRUP = FLUIDS.register("simple_syrup", () -> new BotariumSourceFluid(ModFluidProperties.SIMPLE_SYRUP));
    public static final RegistryEntry<Fluid> FLOWING_SIMPLE_SYRUP = FLUIDS.register("flowing_simple_syrup", () -> new BotariumFlowingFluid(ModFluidProperties.SIMPLE_SYRUP));

    public static final RegistryEntry<Fluid> THC_OIL = FLUIDS.register("thc_oil", () -> new BotariumSourceFluid(ModFluidProperties.THC_OIL));
    public static final RegistryEntry<Fluid> FLOWING_THC_OIL = FLUIDS.register("flowing_thc_oil", () -> new BotariumFlowingFluid(ModFluidProperties.THC_OIL));

    public static final RegistryEntry<Fluid> ADDERALL_GEL = FLUIDS.register("adderall_gel", () -> new BotariumSourceFluid(ModFluidProperties.ADDERALL_GEL));
    public static final RegistryEntry<Fluid> FLOWING_ADDERALL_GEL = FLUIDS.register("flowing_adderall_gel", () -> new BotariumFlowingFluid(ModFluidProperties.ADDERALL_GEL));

    public static final RegistryEntry<Fluid> MELATONIN_GEL = FLUIDS.register("melatonin_gel", () -> new BotariumSourceFluid(ModFluidProperties.MELATONIN_GEL));
    public static final RegistryEntry<Fluid> FLOWING_MELATONIN_GEL = FLUIDS.register("flowing_melatonin_gel", () -> new BotariumFlowingFluid(ModFluidProperties.MELATONIN_GEL));

    public static final RegistryEntry<Fluid> PARACETAMOL_GEL = FLUIDS.register("paracetamol_gel", () -> new BotariumSourceFluid(ModFluidProperties.PARACETAMOL_GEL));
    public static final RegistryEntry<Fluid> FLOWING_PARACETAMOL_GEL = FLUIDS.register("flowing_paracetamol_gel", () -> new BotariumFlowingFluid(ModFluidProperties.PARACETAMOL_GEL));

    public static final RegistryEntry<Fluid> GEL_BASE = FLUIDS.register("gel_base", () -> new BotariumSourceFluid(ModFluidProperties.GEL_BASE));
    public static final RegistryEntry<Fluid> FLOWING_GEL_BASE = FLUIDS.register("flowing_gel_base", () -> new BotariumFlowingFluid(ModFluidProperties.GEL_BASE));
}
