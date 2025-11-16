package me.frogtato.dragoneggmod;

import me.frogtato.dragoneggmod.registry.ModMobEffects;

public final class DragonEggMod {
    public static final String MOD_ID = "dragoneggmod";
    // see #1 and EggEffect
    public static final String TEAM_NAME = "dragoneggmod.egg_team";

    public static void init() {
        ModMobEffects.init();
    }
}
