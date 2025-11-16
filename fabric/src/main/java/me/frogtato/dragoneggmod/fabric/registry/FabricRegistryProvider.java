package me.frogtato.dragoneggmod.fabric.registry;

import me.frogtato.dragoneggmod.registry.RegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public class FabricRegistryProvider<R> implements RegistryProvider<R> {
    private final Registry<R> registry;
    private final String modid;

    public FabricRegistryProvider(String modid, Registry<R> registry) {
        this.modid = modid;
        this.registry = registry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends R> Holder<? extends R> register(String id, Supplier<T> creator) {
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(modid, id);
        ResourceKey<T> key = ResourceKey.create((ResourceKey<? extends Registry<T>>) registry.key(), rl);

        Registry<T> cast = (Registry<T>) registry;

        return Registry.registerForHolder(cast, key, creator.get());
    }
}
