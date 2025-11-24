package me.frogtato.dragoneggmod.neoforge.client.networking;

import me.frogtato.dragoneggmod.client.DragonEggModClient;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class CrownStateSyncClientPayloadHandler {
    public static void handleDataOnMain(final CrownStateSyncPayload data, @SuppressWarnings("unused") IPayloadContext context) {
        DragonEggModClient.handleStateSync(data.uuid(), data.state());
    }
}
