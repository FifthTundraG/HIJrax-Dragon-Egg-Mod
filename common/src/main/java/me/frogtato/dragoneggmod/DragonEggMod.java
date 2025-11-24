package me.frogtato.dragoneggmod;

import me.frogtato.dragoneggmod.networking.CrownStateSyncHandler;
import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DragonEggMod {
    public static final String MOD_ID = "dragoneggmod";

    private static CrownStateSyncHandler crownStateSyncHandler;

    public static void init() {
        ModMobEffects.init();
    }

    public static void setCrownStateSyncHandler(CrownStateSyncHandler handler) {
        crownStateSyncHandler = handler;
    }
    public static CrownStateSyncHandler getCrownStateSyncHandler() {
        return crownStateSyncHandler;
    }

    /** this should be called in each loader's stuff */
    public static void onPlayerJoin(ServerPlayer joiningPlayer) {
        @SuppressWarnings("unchecked")
        final Holder<MobEffect> eggEffect = (Holder<MobEffect>) ModMobEffects.EGG_EFFECT;

        // on fabric, the current player is not in `PlayerList#getPlayers()` when this is run. make sure they are
        List<ServerPlayer> players = new ArrayList<>((Objects.requireNonNull(joiningPlayer.getServer())).getPlayerList().getPlayers());
        if (!players.contains(joiningPlayer)) {
            players.add(joiningPlayer);
        }

        // send crown state for all players to our newly joining player
        for (ServerPlayer player : players) {
            getCrownStateSyncHandler().sendCrownStateToPlayer(joiningPlayer, player.getUUID(), player.hasEffect(eggEffect));
        }

        // sync crown state with all players that are already here (if new player joins with the egg)
        getCrownStateSyncHandler().syncCrownStateAll(joiningPlayer.getUUID(), joiningPlayer.hasEffect(eggEffect), joiningPlayer.getServer().getPlayerList());
    }
}
