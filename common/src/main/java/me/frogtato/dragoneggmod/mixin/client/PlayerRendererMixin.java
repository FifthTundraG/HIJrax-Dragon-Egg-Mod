package me.frogtato.dragoneggmod.mixin.client;

import me.frogtato.dragoneggmod.client.EggCrownHeadLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectCustomLayer(EntityRendererProvider.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerRenderer self = (PlayerRenderer)(Object)this;
        ((LivingEntityRendererAccessor<PlayerRenderState, PlayerModel>) self).callAddLayer(new EggCrownHeadLayer<>(self) {
        });
    }
}
