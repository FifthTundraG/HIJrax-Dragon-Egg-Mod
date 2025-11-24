package me.frogtato.dragoneggmod.networking;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.UUID;

public interface CrownStateSyncHandler {
    /**
     * Called to sync crown state change to all clients
     * @param playerUUID UUID of the player
     * @param state true if added, false if removed
     * @param playerList we need the list of players so we can send the update packet to all of them
     */
    void syncCrownStateAll(UUID playerUUID, boolean state, PlayerList playerList);

    /**
     * Sends a crown state update packet to a specific player
     * @param player The player to send the packet to
     * @param uuid The UUID of the player whose crown state this is
     * @param state true if added, false if removed
     */
    void sendCrownStateToPlayer(ServerPlayer player, UUID uuid, boolean state);
}
