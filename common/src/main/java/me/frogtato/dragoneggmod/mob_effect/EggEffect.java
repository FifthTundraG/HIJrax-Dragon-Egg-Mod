package me.frogtato.dragoneggmod.mob_effect;

import me.frogtato.dragoneggmod.DragonEggMod;
import me.frogtato.dragoneggmod.networking.CrownStateSyncHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class EggEffect extends MobEffect {
    private int duration = 0;

    public EggEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player)) return false; // removes effect

        // doubles hearts in the ModMobEffects instance

        if (duration == 1) { // crude "effect expiry" system
            // send packet telling client to remove our uuid from crown state
            CrownStateSyncHandler handler = DragonEggMod.getCrownStateSyncHandler();
            if (handler != null) {
                handler.syncCrownStateAll(entity.getUUID(), false, Objects.requireNonNull(entity.getServer()).getPlayerList());
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int amplifier) {
        duration = pDuration;
        return true;
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        super.onEffectAdded(entity, amplifier);

        // send packet telling client to add our uuid to crown state
        CrownStateSyncHandler handler = DragonEggMod.getCrownStateSyncHandler();
        if (handler != null) {
            handler.syncCrownStateAll(entity.getUUID(), true, Objects.requireNonNull(entity.getServer()).getPlayerList());
        }
    }
}
