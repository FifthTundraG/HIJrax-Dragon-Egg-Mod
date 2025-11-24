package me.frogtato.dragoneggmod.neoforge;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.neoforge.networking.NeoForgeCrownStateSyncHandler;
import me.frogtato.dragoneggmod.neoforge.registry.NeoForgeRegistryProvider;
import me.frogtato.dragoneggmod.networking.CrownStateSyncPayload;
import me.frogtato.dragoneggmod.registry.ModRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(DragonEggMod.MOD_ID)
public final class DragonEggModNeoForge {
    private static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(Registries.MOB_EFFECT, DragonEggMod.MOD_ID);

    public DragonEggModNeoForge(IEventBus bus) {
        ModRegistries.init(
                new NeoForgeRegistryProvider<>(MOB_EFFECT)
        );
        DragonEggMod.setCrownStateSyncHandler(new NeoForgeCrownStateSyncHandler());

        // Run our common setup.
        DragonEggMod.init();

        NeoForge.EVENT_BUS.register(new EggCheckHandlerNeoForge());
        bus.addListener(DragonEggModNeoForge::onRegisterPayloads);
        NeoForge.EVENT_BUS.addListener(DragonEggModNeoForge::onPlayerJoin);

        MOB_EFFECT.register(bus);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                CrownStateSyncPayload.TYPE,
                CrownStateSyncPayload.CODEC
        );
    }

    private static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        DragonEggMod.onPlayerJoin(serverPlayer);
    }
}
