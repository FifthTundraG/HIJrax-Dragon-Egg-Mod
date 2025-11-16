package me.frogtato.dragoneggmod.registry;

import net.minecraft.world.effect.MobEffect;

public class ModRegistries {
    public static RegistryProvider<MobEffect> MOB_EFFECT;

    public static void init(RegistryProvider<MobEffect> mob_effect) {
        MOB_EFFECT = mob_effect;
    }
}
