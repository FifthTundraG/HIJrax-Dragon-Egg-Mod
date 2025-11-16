package me.frogtato.dragoneggmod.fabric;

import me.frogtato.dragoneggmod.EggCheckHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class EggCheckHandlerFabric {
    public static void init() {
        ServerTickEvents.START_WORLD_TICK.register(EggCheckHandlerFabric::onWorldTick);
    }

    private static void onWorldTick(ServerLevel world) {
        // Iterate through all players in this world
        for (Player player : world.players()) {
            EggCheckHandler.onPlayerTick(player);
        }
    }
}
