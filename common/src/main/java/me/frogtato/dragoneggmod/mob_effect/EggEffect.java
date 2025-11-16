package me.frogtato.dragoneggmod.mob_effect;

import me.frogtato.dragoneggmod.DragonEggMod;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

public class EggEffect extends MobEffect {
    private int duration = 0;

    public EggEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (!(entity instanceof Player player)) return false; // removes effect

        // doubles hearts in the ModMobEffects instance

        // see #1
        PlayerTeam newTeam = getOrCreateTeam(level, DragonEggMod.TEAM_NAME);
        newTeam.setColor(ChatFormatting.DARK_PURPLE);

        level.getScoreboard().addPlayerToTeam(player.getGameProfile().getName(), newTeam);

        if (duration == 1) { // crude "effect expiry" system
            level.getScoreboard().removePlayerFromTeam(player.getGameProfile().getName(), newTeam);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int amplifier) {
        duration = pDuration;
        return true;
    }

    @SuppressWarnings("SameParameterValue")
    private PlayerTeam getOrCreateTeam(Level level, String teamName) {
        Scoreboard scoreboard = level.getScoreboard();

        PlayerTeam team = scoreboard.getPlayerTeam(teamName);

        if (team == null) {
            team = scoreboard.addPlayerTeam(teamName);
        }

        return team;
    }
}
