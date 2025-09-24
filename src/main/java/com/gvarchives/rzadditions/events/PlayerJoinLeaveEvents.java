package com.gvarchives.rzadditions.events;

import com.gvarchives.rzadditions.RZAdditions;
import com.gvarchives.rzadditions.core.ModGamerules;
import com.gvarchives.rzadditions.core.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RZAdditions.MOD_ID)
public class PlayerJoinLeaveEvents
{
    private static void playGlobalSound(ServerPlayer sourcePlayer, boolean joining)
    {
        if (!sourcePlayer.level().getGameRules().getBoolean(ModGamerules.JOIN_LEAVE_SOUNDS))
        {
            return;
        }

        for (ServerPlayer player : sourcePlayer.server.getPlayerList().getPlayers())
        {
            player.playNotifySound(
                    joining ? ModSounds.PLAYER_JOIN.get() : ModSounds.PLAYER_LEAVE.get(),
                    SoundSource.PLAYERS,
                    0.5F,
                    1.0F
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            playGlobalSound(player, true);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            playGlobalSound(player, false);
        }
    }
}
