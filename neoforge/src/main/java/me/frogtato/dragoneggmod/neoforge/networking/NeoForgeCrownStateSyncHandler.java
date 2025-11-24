package me.frogtato.dragoneggmod.neoforge.networking;

import me.frogtato.dragoneggmod.networking.CrownStateSyncHandler;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import net.minecraft.server.players.PlayerList;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.UUID;

public class NeoForgeCrownStateSyncHandler implements CrownStateSyncHandler {
    @Override
    public void syncCrownState(UUID playerUUID, boolean state, PlayerList playerList) {
        PacketDistributor.sendToAllPlayers(new CrownStateSyncPayload(playerUUID, state));
    }
}
