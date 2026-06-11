package com.gvarchives.rzadditions.content.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipBlockItem extends BlockItem
{
    private final String tooltipKey;
    public TooltipBlockItem(Block block, Properties properties, String tooltipKey)
    {
        super(block, properties);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        if (Screen.hasShiftDown())
        {
            tooltip.add(Component.translatable(tooltipKey).withStyle(ChatFormatting.WHITE,  ChatFormatting.ITALIC));
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
