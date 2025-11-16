package me.frogtato.dragoneggmod.client;

import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
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
        @SuppressWarnings("unchecked")
        final Holder<MobEffect> eggEffect = (Holder<MobEffect>) ModMobEffects.EGG_EFFECT;

        crownState.put(player.getUUID(), player.hasEffect(eggEffect));
    }
}
