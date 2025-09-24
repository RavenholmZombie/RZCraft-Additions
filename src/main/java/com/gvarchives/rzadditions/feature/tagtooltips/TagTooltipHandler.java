package com.gvarchives.rzadditions.feature.tagtooltips;

import com.gvarchives.rzadditions.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagTooltipHandler
{
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent e)
    {
        if (!TagTooltipConfig.ENABLED.get()) return;

        boolean advanced = Minecraft.getInstance().options.advancedItemTooltips;
        boolean requiresShift = TagTooltipConfig.REQUIRE_SHIFT.get();
        boolean showWhenAdvanced = TagTooltipConfig.SHOW_WHEN_ADVANCED.get();

        if (requiresShift && !Screen.hasShiftDown())
        {
            if (advanced && showWhenAdvanced)
            {

            }
            else
            {
                if (hasAnyTags(e.getItemStack())) {
                    e.getToolTip().add(Component.literal("Hold ")
                            .append(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW))
                            .append(Component.literal(" for tags"))
                            .withStyle(ChatFormatting.DARK_GRAY));
                }
                return;
            }
        }
        addTagLines(e.getItemStack(), e.getToolTip());
    }

    private static boolean hasAnyTags(ItemStack stack)
    {
        if (stack == null || stack.isEmpty()) return false;
        if (stack.getTags().findAny().isPresent()) return true;
        if (TagTooltipConfig.INCLUDE_BLOCK_TAGS.get() && stack.getItem() instanceof BlockItem bi)
        {
            return bi.getBlock().builtInRegistryHolder().tags().findAny().isPresent();
        }
        if (TagTooltipConfig.INCLUDE_FLUID_TAGS.get() && stack.getItem() instanceof BucketItem bucket)
        {
            return bucket.getFluid().builtInRegistryHolder().tags().findAny().isPresent();
        }
        return false;
    }

    private static void addTagLines(ItemStack stack, List<Component> tooltip) {
        boolean showNs = TagTooltipConfig.SHOW_NAMESPACE.get();

        // Item tags
        List<String> itemTags = stack.getTags()
                .map(TagTooltipHandler::format)
                .sorted(Comparator.naturalOrder())
                .toList();

        // Block tags (for BlockItem)
        List<String> blockTags = List.of();
        if (TagTooltipConfig.INCLUDE_BLOCK_TAGS.get() && stack.getItem() instanceof BlockItem bi) {
            blockTags = bi.getBlock().builtInRegistryHolder().tags()
                    .map(TagTooltipHandler::format)
                    .sorted()
                    .toList();
        }

        // Fluid tags (for buckets)
        List<String> fluidTags = List.of();
        if (TagTooltipConfig.INCLUDE_FLUID_TAGS.get() && stack.getItem() instanceof BucketItem bucket) {
            fluidTags = bucket.getFluid().builtInRegistryHolder().tags()
                    .map(TagTooltipHandler::format)
                    .sorted()
                    .toList();
        }

        // If nothing to show, bail
        if (itemTags.isEmpty() && blockTags.isEmpty() && fluidTags.isEmpty()) return;

        // Header
        tooltip.add(styled("Tags:", ChatFormatting.GRAY));

        // Items
        if (!itemTags.isEmpty()) {
            tooltip.add(styled("  Item:", ChatFormatting.DARK_GRAY));
            itemTags.forEach(t -> tooltip.add(styled("    " + maybeStripNs(t, showNs), ChatFormatting.DARK_GRAY)));
        }

        // Blocks
        if (!blockTags.isEmpty()) {
            tooltip.add(styled("  Block:", ChatFormatting.DARK_GRAY));
            blockTags.forEach(t -> tooltip.add(styled("    " + maybeStripNs(t, showNs), ChatFormatting.DARK_GRAY)));
        }

        // Fluids
        if (!fluidTags.isEmpty()) {
            tooltip.add(styled("  Fluid:", ChatFormatting.DARK_GRAY));
            fluidTags.forEach(t -> tooltip.add(styled("    " + maybeStripNs(t, showNs), ChatFormatting.DARK_GRAY)));
        }
    }

    private static String maybeStripNs(String id, boolean keep) {
        if (keep) return id;
        int i = id.indexOf(':');
        return i >= 0 ? id.substring(i + 1) : id;
    }

    private static MutableComponent styled(String s, ChatFormatting... fmt) {
        return Component.literal(s).withStyle(fmt);
    }

    private static String format(TagKey<?> key) {
        ResourceLocation rl = key.location();
        return rl.getNamespace() + ":" + rl.getPath();
    }

    // (Examples if you ever need raw registry-holder tags)
    @SuppressWarnings("unused")
    private static Stream<TagKey<Item>> itemTags(ItemStack stack) { return stack.getTags(); }

    @SuppressWarnings("unused")
    private static Stream<TagKey<Block>> blockTags(Block block) { return block.builtInRegistryHolder().tags(); }

    @SuppressWarnings("unused")
    private static Stream<TagKey<Fluid>> fluidTags(Fluid fluid) { return fluid.builtInRegistryHolder().tags(); }
}
