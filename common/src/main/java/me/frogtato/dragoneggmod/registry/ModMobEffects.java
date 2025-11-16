package me.frogtato.dragoneggmod.registry;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.mob_effect.EggEffect;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public class ModMobEffects {
    public static final Holder<? extends MobEffect> EGG_EFFECT = register("egg_effect", () -> new EggEffect(MobEffectCategory.BENEFICIAL, 0x3e005c)
            .addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(DragonEggMod.MOD_ID, "effect.egg_effect"), 20.0, AttributeModifier.Operation.ADD_VALUE));

    @SuppressWarnings("SameParameterValue")
    private static <T extends MobEffect> Holder<? extends MobEffect> register(String path, Supplier<T> mobEffect) {
        return ModRegistries.MOB_EFFECT.register(path, mobEffect);
    }

    public static void init() {}
}
