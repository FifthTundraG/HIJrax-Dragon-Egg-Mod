package me.frogtato.dragoneggmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EggCrownHeadLayer<S extends PlayerRenderState, M extends PlayerModel> extends RenderLayer<S, M> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("dragoneggmod", "textures/misc/crown.png");
    private final ModelPart part;

    public EggCrownHeadLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);

        // todo: not all faces need to be technically visible
        Set<Direction> faces = EnumSet.allOf(Direction.class);

        // Create a 1x1x1 cube centered around origin
        ModelPart.Cube box = new ModelPart.Cube(
                0, 0,                     // texture U/V
                -0.5F, -0.5F, -0.5F,      // origin (centered)
                1.0F, 1.0F, 1.0F,         // dimensions
                4.5F, 4.5F, 4.5F, // grow; this is set to exactly align with head equipment
                false,                    // mirror
                4.0F, 2.0F,               // texScaleU/V
                faces                     // visible faces
        );

        this.part = new ModelPart(List.of(box), Map.of());
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S renderState, float yRot, float xRot) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        Player foundPlayer = null;
        for (var player : mc.level.players()) { // todo: can we make this cleaner?
            if (player.getId() == renderState.id) {
                foundPlayer = player;
                break;
            }
        }

        if (foundPlayer == null) return; // player not present in client world right now (probably not possible?)
        if (!DragonEggModClient.hasCrown(foundPlayer)) return;

        poseStack.pushPose();

        // Move and rotate relative to the head
        this.getParentModel().head.translateAndRotate(poseStack);

        // align with head
        poseStack.translate(0.0F, -0.25, 0.0F);
        if (!renderState.headItem.isEmpty() | !renderState.headEquipment.is(Items.AIR)) { // carved pumpkin makes headItem not empty but headEquipment AIR, any helmet makes headItem empty but headEquipment be the item. this works with both
            poseStack.scale(1.12f, 1.12f, 1.12f); // sets grow for cube to  5, renders crown atop head armor
        }

        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.part.render(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }
}

