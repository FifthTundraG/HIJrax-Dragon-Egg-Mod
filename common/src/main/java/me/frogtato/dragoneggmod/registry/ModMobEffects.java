package me.frogtato.dragoneggmod.registry;

import me.frogtato.dragoneggmod.mob_effect.EggEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import java.util.function.Supplier;

public class ModMobEffects {
    public static final Holder<? extends MobEffect> EGG_EFFECT = register("egg_effect", () -> new EggEffect(MobEffectCategory.BENEFICIAL, 0x3e005c));

    @SuppressWarnings("SameParameterValue")
    private static <T extends MobEffect> Holder<? extends MobEffect> register(String path, Supplier<T> mobEffect) {
        return ModRegistries.MOB_EFFECT.register(path, mobEffect);
    }

    public static void init() {}
}
