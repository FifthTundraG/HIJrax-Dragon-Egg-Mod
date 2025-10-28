package me.frogtato.dragoneggmod;

import me.frogtato.dragoneggmod.registry.ModMobEffects;

public final class DragonEggMod {
    public static final String MOD_ID = "dragoneggmod";

    public static void init() {
        EggCheckHandler.init();
        ModMobEffects.init();
    }
}
