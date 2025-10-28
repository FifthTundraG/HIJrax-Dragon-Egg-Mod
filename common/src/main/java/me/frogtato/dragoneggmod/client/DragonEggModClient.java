package me.frogtato.dragoneggmod.client;

import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DragonEggModClient {
    /** (Player UUID): (has crown?) */
    private static final Map<UUID, Boolean> crownState = new HashMap<>();

    public static boolean hasCrown(Player player) {
        return crownState.getOrDefault(player.getUUID(), false);
    }

    public static void update(Player player) {
        crownState.put(player.getUUID(), player.hasEffect(ModMobEffects.EGG_EFFECT));
    }
}
