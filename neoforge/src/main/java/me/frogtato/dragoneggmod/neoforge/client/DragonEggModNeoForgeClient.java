package me.frogtato.dragoneggmod.neoforge.client;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.neoforge.client.networking.CrownStateSyncClientPayloadHandler;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;

@Mod(value = DragonEggMod.MOD_ID, dist = Dist.CLIENT)
public class DragonEggModNeoForgeClient {
    public DragonEggModNeoForgeClient(IEventBus bus) {
        bus.addListener(DragonEggModNeoForgeClient::registerClientPayloads);
    }

    private static void registerClientPayloads(RegisterClientPayloadHandlersEvent event) {
        event.register(
                CrownStateSyncPayload.TYPE,
                CrownStateSyncClientPayloadHandler::handleDataOnMain
        );
    }
}