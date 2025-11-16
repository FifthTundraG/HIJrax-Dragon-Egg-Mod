package me.frogtato.dragoneggmod.registry;

import net.minecraft.core.Holder;

import java.util.function.Supplier;

public interface RegistryProvider<R> {
    <T extends R> Holder<? extends R> register(String path, Supplier<T> creator);
}
