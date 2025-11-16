package me.frogtato.dragoneggmod.neoforge.registry;

import me.frogtato.dragoneggmod.registry.RegistryProvider;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegistryProvider<R> implements RegistryProvider<R> {
    private final DeferredRegister<R> register;

    public NeoForgeRegistryProvider(DeferredRegister<R> register) {
        this.register = register;
    }

    @Override
    public <T extends R> Holder<? extends R> register(String id, Supplier<T> creator) {
        return register.register(id, creator); // DeferredHolder<R,T>
    }
}
