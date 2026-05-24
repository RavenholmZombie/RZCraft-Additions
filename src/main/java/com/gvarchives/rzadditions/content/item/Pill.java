package com.gvarchives.rzadditions.content.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Pill extends Item
{
    private final List<Supplier<MobEffectInstance>> effects;
    private final Consumer<ServerPlayer> extraAction;

    public Pill(Properties properties, List<Supplier<MobEffectInstance>> effects, Consumer<ServerPlayer> extraAction)
    {
        super(properties);
        this.effects = effects;
        this.extraAction = extraAction;
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof ServerPlayer player)
        {
            for (Supplier<MobEffectInstance> effect : effects)
                player.addEffect(effect.get());

            if (extraAction != null)
                extraAction.accept(player);
        }

        return result;
    }
}
