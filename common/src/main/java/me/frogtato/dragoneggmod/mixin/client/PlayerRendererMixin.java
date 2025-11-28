package me.frogtato.dragoneggmod.mixin.client;

import me.frogtato.dragoneggmod.client.DragonEggModClient;
import me.frogtato.dragoneggmod.client.EggCrownHeadLayer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectCustomLayer(EntityRendererProvider.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerRenderer self = (PlayerRenderer)(Object)this;
        ((LivingEntityRendererAccessor<PlayerRenderState, PlayerModel>) self).callAddLayer(new EggCrownHeadLayer<>(self) {
        });
    }

    /** Changes player nametag */
    @ModifyArgs(
            method = "renderNameTag(Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",  // the method you are targeting
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;renderNameTag(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
                    ordinal = 1
            )
    )
    private void modifyComponent(Args args) {
        PlayerRenderState renderState = args.get(0); // we only need this, not modify it
        Component component = args.get(1);

        if (DragonEggModClient.hasCrown(renderState.name)) {
            args.set(1, component.copy().withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
}
