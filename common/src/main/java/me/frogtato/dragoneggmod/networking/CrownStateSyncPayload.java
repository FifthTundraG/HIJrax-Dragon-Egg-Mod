package me.frogtato.dragoneggmod.networking;

import me.frogtato.dragoneggmod.DragonEggMod;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record CrownStateSyncPayload(UUID uuid, boolean state) implements CustomPacketPayload {
    public static final ResourceLocation PAYLOAD_RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(DragonEggMod.MOD_ID, "crown_state_sync");
    public static final CustomPacketPayload.Type<CrownStateSyncPayload> TYPE = new CustomPacketPayload.Type<>(PAYLOAD_RESOURCE_LOCATION);
    public static final StreamCodec<RegistryFriendlyByteBuf, CrownStateSyncPayload> CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, CrownStateSyncPayload::uuid,
            ByteBufCodecs.BOOL, CrownStateSyncPayload::state,
            CrownStateSyncPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}