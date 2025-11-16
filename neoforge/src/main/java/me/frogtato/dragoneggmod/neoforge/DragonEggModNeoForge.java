package me.frogtato.dragoneggmod.neoforge;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.neoforge.registry.NeoForgeRegistryProvider;
import me.frogtato.dragoneggmod.registry.ModRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(DragonEggMod.MOD_ID)
public final class DragonEggModNeoForge {
    private static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(Registries.MOB_EFFECT, DragonEggMod.MOD_ID);

    public DragonEggModNeoForge(IEventBus bus) {
        ModRegistries.init(
                new NeoForgeRegistryProvider<>(MOB_EFFECT)
        );

        // Run our common setup.
        DragonEggMod.init();

        NeoForge.EVENT_BUS.register(new EggCheckHandlerNeoForge());

        MOB_EFFECT.register(bus);
    }
}
