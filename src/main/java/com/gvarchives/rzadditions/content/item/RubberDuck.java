package com.gvarchives.rzadditions.content.item;

import com.gvarchives.rzadditions.core.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot slot, Entity entity)
    {
        return slot == EquipmentSlot.HEAD;
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack)
    {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(Component.translatable("tooltip.rzadditions.duck").withStyle(ChatFormatting.WHITE,  ChatFormatting.ITALIC));
        }
        else
        {
            tooltip.add(
                    Component.literal("Hold ")
                            .append(Component.literal("[SHIFT]").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD))
                            .append(Component.literal(" for more information"))
                            .withStyle(ChatFormatting.DARK_GRAY)
            );
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
