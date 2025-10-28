package me.frogtato.dragoneggmod;

import dev.architectury.event.events.common.TickEvent;
import me.frogtato.dragoneggmod.registry.ModMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EggCheckHandler {
    private static final int CHECK_INTERVAL = 20; // 20 ticks

    public static void init() {
        TickEvent.Player.PLAYER_POST.register((player) -> {
            if (player.level().isClientSide()) return; // Only run on server

            if (player.tickCount % CHECK_INTERVAL == 0) {
                checkPlayerInventory(player);
            }
        });
    }

    private static void checkPlayerInventory(Player player) {
        for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
            if (stack.is(Items.DRAGON_EGG)) {
                player.displayClientMessage(Component.literal("egg found"), false);
                player.addEffect(new MobEffectInstance(ModMobEffects.EGG_EFFECT, 35, 0, false, false)); // todo: why does 35 duration work? what is ambient and do we want it?
                break;
            }
        }
    }
}
