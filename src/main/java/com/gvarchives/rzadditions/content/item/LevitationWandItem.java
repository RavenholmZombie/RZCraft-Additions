package com.gvarchives.rzadditions.content.item;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;

public class LevitationWandItem extends Item
{
    private static final int COOLDOWN_TICKS = 200; // 10s
    private static LivingEntity controlledEntity;

    public LevitationWandItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) return InteractionResultHolder.pass(stack);

        if (player.getCooldowns().isOnCooldown(this))
        {
            player.displayClientMessage(Component.literal("Wand is cooling down!"), true);
            return InteractionResultHolder.pass(stack);
        }

        HitResult hit = Minecraft.getInstance().hitResult;
        if (hit instanceof EntityHitResult ehr && ehr.getEntity() instanceof LivingEntity target)
        {
            controlledEntity = target;
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected)
    {
        if (level.isClientSide && controlledEntity != null && controlledEntity.isAlive() && entity instanceof Player player)
        {
            double distance = 4.0;
            double px = player.getX() + player.getLookAngle().x * distance;
            double py = player.getEyeY() + player.getLookAngle().y * distance;
            double pz = player.getZ() + player.getLookAngle().z * distance;
            controlledEntity.setPos(px, py, pz);

            level.addParticle(net.minecraft.core.particles.ParticleTypes.PORTAL,
                    controlledEntity.getRandomX(0.5),
                    controlledEntity.getRandomY(),
                    controlledEntity.getRandomZ(0.5),
                    (Math.random() - 0.5) * 2.0,
                    -Math.random(),
                    (Math.random() - 0.5) * 2.0);
        }
        else
        {
            controlledEntity = null;
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientEvents
    {
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void renderBeamWorld(RenderLevelStageEvent event)
        {
            if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;
            if (controlledEntity == null || !controlledEntity.isAlive()) return;

            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;
            if (player == null) return;

            double px = event.getCamera().getPosition().x;
            double py = event.getCamera().getPosition().y;
            double pz = event.getCamera().getPosition().z;

            double x1 = player.getX() - px;
            double y1 = player.getEyeY() - py;
            double z1 = player.getZ() - pz;

            double x2 = controlledEntity.getX() - px;
            double y2 = controlledEntity.getEyeY() - py;
            double z2 = controlledEntity.getZ() - pz;

            float pulse = (Mth.sin((player.level().getGameTime() % 40) / 40f * (float)Math.PI * 2) + 1f) * 0.5f;
            float r = 0.6f, g = 0.0f, b = 0.9f;
            float alpha = 0.5f + 0.5f * pulse;

            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();

            BufferBuilder buffer = Tesselator.getInstance().getBuilder();
            buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            addBeam(buffer, x1, y1, z1, x2, y2, z2, 0.06, r, g, b, alpha);
            Tesselator.getInstance().end();

            RenderSystem.disableBlend();
            RenderSystem.enableCull();
        }

        private static void addBeam(BufferBuilder buffer, double x1, double y1, double z1,
                                    double x2, double y2, double z2,
                                    double thickness, float r, float g, float b, float a)
        {
            double dx = x2 - x1;
            double dz = z2 - z1;
            double len = Math.sqrt(dx * dx + dz * dz);
            if (len == 0) len = 1;
            double ox = -dz / len * thickness;
            double oz = dx / len * thickness;

            buffer.vertex(x1 - ox, y1, z1 - oz).color(r, g, b, a).endVertex();
            buffer.vertex(x1 + ox, y1, z1 + oz).color(r, g, b, a).endVertex();
            buffer.vertex(x2 + ox, y2, z2 + oz).color(r, g, b, a).endVertex();
            buffer.vertex(x2 - ox, y2, z2 - oz).color(r, g, b, a).endVertex();
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void renderHud(RenderGuiOverlayEvent.Post event)
        {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player == null) return;

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof LevitationWandItem)) return;

            int cooldown = player.getCooldowns().getCooldownPercent(stack.getItem(), 0) > 0
                    ? (int) (player.getCooldowns().getCooldownPercent(stack.getItem(), 0) * 100)
                    : 0;

            if (cooldown > 0)
            {
                int w = event.getWindow().getGuiScaledWidth();
                int h = event.getWindow().getGuiScaledHeight();
                int barWidth = 100;
                int filled = (int) (barWidth * (cooldown / 100.0));

                GuiGraphics gg = event.getGuiGraphics();
                int x = (w - barWidth) / 2;
                int y = h - 40;

                gg.fill(x, y, x + barWidth, y + 5, 0x66000000);
                gg.fill(x, y, x + filled, y + 5, 0xAA9900CC);
            }
        }
    }
}
