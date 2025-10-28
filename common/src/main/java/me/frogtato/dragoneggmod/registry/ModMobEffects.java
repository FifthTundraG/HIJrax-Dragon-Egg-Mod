package me.frogtato.dragoneggmod.registry;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.mob_effect.EggEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffects {
    public static final Holder<MobEffect> EGG_EFFECT = register("egg_effect", new EggEffect(MobEffectCategory.BENEFICIAL, 7561558));

    private static Holder<MobEffect> register(String path, MobEffect mobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(DragonEggMod.MOD_ID, path), mobEffect);
    }

    public static void init() {}
}
