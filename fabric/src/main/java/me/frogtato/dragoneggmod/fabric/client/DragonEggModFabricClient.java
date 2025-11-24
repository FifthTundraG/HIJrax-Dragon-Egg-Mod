package me.frogtato.dragoneggmod.fabric.client;

import me.frogtato.dragoneggmod.client.DragonEggModClient;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class DragonEggModFabricClient implements ClientModInitializer {
    private static void onCrownStateSyncPacketReceived(CrownStateSyncPayload payload, ClientPlayNetworking.Context context) {
        DragonEggModClient.handleStateSync(payload.uuid(), payload.state());
    }

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(CrownStateSyncPayload.TYPE, DragonEggModFabricClient::onCrownStateSyncPacketReceived);
    }
}
