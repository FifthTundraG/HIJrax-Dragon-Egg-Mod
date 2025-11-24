package me.frogtato.dragoneggmod.fabric.networking;

import me.frogtato.dragoneggmod.networking.CrownStateSyncHandler;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.UUID;

public class FabricCrownStateSyncHandler implements CrownStateSyncHandler {
    @Override
    public void syncCrownState(UUID playerUUID, boolean state, PlayerList playerList) {
        CrownStateSyncPayload payload = new CrownStateSyncPayload(playerUUID, state);

        for (ServerPlayer player : playerList.getPlayers()) {
            ServerPlayNetworking.send(player, payload);
        }
    }
}
