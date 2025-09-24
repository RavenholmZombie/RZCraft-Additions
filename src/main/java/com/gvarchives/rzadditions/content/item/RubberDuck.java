package com.gvarchives.rzadditions.content.item;

import com.gvarchives.rzadditions.core.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RubberDuck extends Item
{
    public RubberDuck(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if (!level.isClientSide)
        {
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.DUCK.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
