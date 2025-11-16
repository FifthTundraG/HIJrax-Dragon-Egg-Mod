package me.frogtato.dragoneggmod.neoforge.client;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.client.DragonEggModClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = DragonEggMod.MOD_ID, dist = Dist.CLIENT)
public class DragonEggModNeoForgeClient {
    public DragonEggModNeoForgeClient() {
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