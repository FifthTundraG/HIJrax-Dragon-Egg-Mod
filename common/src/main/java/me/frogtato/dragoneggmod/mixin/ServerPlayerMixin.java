package me.frogtato.dragoneggmod.mixin;

import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Changes name in tablist */
@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(method = "getTabListDisplayName", at = @At("HEAD"), cancellable = true)
    void getTabListDisplayName(CallbackInfoReturnable<Component> cir) {
        final ServerPlayer self = ((ServerPlayer)(Object)this);
        if (dragoneggmod$checkHasEffect(self)) {
            cir.setReturnValue(Component.literal(self.getDisplayName().getString()).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
    
    /** we consider the EggEffect gone at 1 tick remaining for our silly effect expiry system (see {@link me.frogtato.dragoneggmod.mob_effect.EggEffect#applyEffectTick(net.minecraft.server.level.ServerLevel, net.minecraft.world.entity.LivingEntity, int)}, so check that here instead of using {@link net.minecraft.world.entity.LivingEntity#hasEffect(net.minecraft.core.Holder)} */
    @Unique
    private boolean dragoneggmod$checkHasEffect(ServerPlayer player) {
        @SuppressWarnings("unchecked")
        final Holder<MobEffect> eggEffect = (Holder<MobEffect>) ModMobEffects.EGG_EFFECT;
        for (MobEffectInstance effectInstance : player.getActiveEffects()) {
            if (effectInstance.is(eggEffect) && effectInstance.getDuration() > 1) {
                return true;
            }
        }
        return false;
    }
}
