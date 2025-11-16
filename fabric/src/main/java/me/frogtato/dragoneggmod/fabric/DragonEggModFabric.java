package me.frogtato.dragoneggmod.fabric;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.fabric.registry.FabricRegistryProvider;
import me.frogtato.dragoneggmod.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;

public final class DragonEggModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModRegistries.init(
                new FabricRegistryProvider<>(DragonEggMod.MOD_ID, BuiltInRegistries.MOB_EFFECT)
        );
        EggCheckHandlerFabric.init();

        // Run our common setup.
        DragonEggMod.init();
    }
}
