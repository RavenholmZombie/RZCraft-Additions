package com.gvarchives.rzadditions.content.other;

import com.gvarchives.rzadditions.core.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class AddHempLootModifier extends LootModifier
{
    public static final Codec<AddHempLootModifier> CODEC = RecordCodecBuilder.create(instance ->
            codecStart(instance).apply(instance, AddHempLootModifier::new)
    );

    public AddHempLootModifier(LootItemCondition[] conditions)
    {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        ResourceLocation table = context.getQueriedLootTableId();

        if (
                table.equals(BuiltInLootTables.ABANDONED_MINESHAFT) ||
                        table.equals(BuiltInLootTables.SIMPLE_DUNGEON) ||
                        table.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE) ||
                        table.equals(BuiltInLootTables.SHIPWRECK_SUPPLY)
        )
        {
            RandomSource random = context.getRandom();

            if (random.nextFloat() < 0.35F)
            {
                generatedLoot.add(new ItemStack(ModItems.HEMP.get(), 1 + random.nextInt(3)));
            }
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec()
    {
        return CODEC;
    }
}
