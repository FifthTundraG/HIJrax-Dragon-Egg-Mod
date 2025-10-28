package me.frogtato.dragoneggmod.neoforge.client;

import me.frogtato.dragoneggmod.client.DragonEggModClient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.common.NeoForge;

public class DragonEggModNeoForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Register listener to NeoForge’s main event bus
        NeoForge.EVENT_BUS.addListener(DragonEggModNeoForgeClient::onClientTick);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        var mc = Minecraft.getInstance();
        if (mc.level == null) return;

        for (var player : mc.level.players()) {
            DragonEggModClient.update(player);
        }
    }
}