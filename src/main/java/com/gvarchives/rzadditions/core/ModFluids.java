package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.Main;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.botarium.common.registry.fluid.BotariumFlowingFluid;
import earth.terrarium.botarium.common.registry.fluid.BotariumSourceFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;

public class ModFluids
{
    public static final ResourcefulRegistry<Fluid> FLUIDS = ResourcefulRegistries.create(BuiltInRegistries.FLUID, Main.MOD_ID);

    public static final RegistryEntry<Fluid> SOY_MILK = FLUIDS.register("soy_milk", () -> new BotariumSourceFluid(ModFluidProperties.SOY_MILK));
    public static final RegistryEntry<Fluid> FLOWING_SOY_MILK = FLUIDS.register("flowing_soy_milk", () -> new BotariumFlowingFluid(ModFluidProperties.SOY_MILK));
}
