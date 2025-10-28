package me.frogtato.dragoneggmod.neoforge;

import me.frogtato.dragoneggmod.DragonEggMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonEggMod.MOD_ID)
public final class DragonEggModNeoForge {
    public DragonEggModNeoForge() {
        // Run our common setup.
        DragonEggMod.init();
    }
}
