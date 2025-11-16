package me.frogtato.dragoneggmod.neoforge;

import me.frogtato.dragoneggmod.EggCheckHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class EggCheckHandlerNeoForge {
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Pre event) {
        EggCheckHandler.onPlayerTick(event.getEntity());
    }
}