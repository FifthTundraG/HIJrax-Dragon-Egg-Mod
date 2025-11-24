package me.frogtato.dragoneggmod.fabric;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import me.frogtato.dragoneggmod.fabric.networking.FabricCrownStateSyncHandler;
import me.frogtato.dragoneggmod.fabric.registry.FabricRegistryProvider;
import me.frogtato.dragoneggmod.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class DragonEggModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModRegistries.init(
                new FabricRegistryProvider<>(DragonEggMod.MOD_ID, BuiltInRegistries.MOB_EFFECT)
        );
        DragonEggMod.setCrownStateSyncHandler(new FabricCrownStateSyncHandler());
        EggCheckHandlerFabric.init();

        PayloadTypeRegistry.playS2C().register(CrownStateSyncPayload.TYPE, CrownStateSyncPayload.CODEC);

        // Run our common setup.
        DragonEggMod.init();
    }
}
