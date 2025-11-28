package me.frogtato.dragoneggmod.mixin;

import com.mojang.authlib.GameProfile;
import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Changes name in chat */
@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    public abstract GameProfile getGameProfile();

    @Inject(at = @At("HEAD"), method = "getName", cancellable = true)
    void getName(CallbackInfoReturnable<Component> cir) {
        @SuppressWarnings("unchecked")
        final Holder<MobEffect> eggEffect = (Holder<MobEffect>) ModMobEffects.EGG_EFFECT;
        if (((LivingEntity)(Object)this).hasEffect(eggEffect)) {
            String playerName = this.getGameProfile().getName();
            cir.setReturnValue(Component.literal(playerName).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
}
