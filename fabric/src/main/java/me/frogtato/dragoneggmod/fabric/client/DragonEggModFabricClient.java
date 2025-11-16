package me.frogtato.dragoneggmod.fabric.client;

import me.frogtato.dragoneggmod.client.DragonEggModClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.entity.player.Player;

public final class DragonEggModFabricClient implements ClientModInitializer {
    public static void registerClientTickListener() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level == null) return;
            for (Player player : client.level.players()) {
                DragonEggModClient.update(player);
            }
        });
    }

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        registerClientTickListener();
    }
}
