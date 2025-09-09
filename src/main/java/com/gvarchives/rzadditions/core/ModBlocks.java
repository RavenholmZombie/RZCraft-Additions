package com.gvarchives.rzadditions.core;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.content.block.SoybeanCropBlock;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RZAdditions.MOD_ID);

    public static final RegistryObject<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop",
            () -> new SoybeanCropBlock());


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);

        // Future resting place for BlockItem registrations.
        // E.g.: ModItems.registerBlockItem("id", OBJECT);
    }
}
