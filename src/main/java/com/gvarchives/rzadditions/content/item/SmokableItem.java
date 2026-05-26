package com.gvarchives.rzadditions.content.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SmokableItem extends EffectItem
{
    private final List<Supplier<MobEffectInstance>> effects;
    private final Consumer<ServerPlayer> extraAction;

    public SmokableItem(Properties properties, List<Supplier<MobEffectInstance>> effects, Consumer<ServerPlayer> extraAction)
    {
        super(properties, effects, extraAction);
        this.effects = effects;
        this.extraAction = extraAction;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 20;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration)
    {
        if (level instanceof ServerLevel serverLevel && entity instanceof ServerPlayer player)
        {
            if (remainingUseDuration % 5 == 0)
            {
                serverLevel.sendParticles(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        player.getX(),
                        player.getEyeY() - 0.2D,
                        player.getZ(),
                        3,
                        0.15D,
                        0.10D,
                        0.15D,
                        0.01D
                );
                serverLevel.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.PLAYER_BREATH,
                        SoundSource.PLAYERS,
                        0.15F,
                        1.8F
                );
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, net.minecraft.world.entity.player.Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public SoundEvent getEatingSound()
    {
        return SoundEvents.FIRE_EXTINGUISH;
    }

    @Override
    public SoundEvent getDrinkingSound()
    {
        return SoundEvents.EMPTY;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (!level.isClientSide && entity instanceof ServerPlayer player)
        {
            for (Supplier<MobEffectInstance> effect : effects)
                player.addEffect(effect.get());

            if (extraAction != null)
                extraAction.accept(player);

            if (!player.getAbilities().instabuild)
            {
                stack.hurtAndBreak(
                        1,
                        player,
                        p -> p.broadcastBreakEvent(player.getUsedItemHand())
                );
            }
            player.getCooldowns().addCooldown(this, 20 * 10);
        }

        return stack;
    }
}
