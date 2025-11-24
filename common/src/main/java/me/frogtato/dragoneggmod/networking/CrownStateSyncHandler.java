package me.frogtato.dragoneggmod.networking;

import net.minecraft.server.players.PlayerList;

import java.util.UUID;

public interface CrownStateSyncHandler {
    /**
     * Called to sync crown state change to clients
     * @param playerUUID UUID of the player
     * @param state true if added, false if removed
     * @param playerList we need the list of players so we can send the update packet to all of them
     */
    void syncCrownState(UUID playerUUID, boolean state, PlayerList playerList);
}
