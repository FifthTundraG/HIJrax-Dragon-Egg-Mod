package me.frogtato.dragoneggmod.mixin;

import me.frogtato.dragoneggmod.EnderChestSlot;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestMenu.class)
public abstract class ChestMenuMixin extends AbstractContainerMenu {
    @Shadow
    @Final
    private int containerRows;

    protected ChestMenuMixin(@Nullable MenuType<?> menuType, int i) {
        super(menuType, i);
    }

    @Inject(method = "addChestGrid", at = @At("HEAD"), cancellable = true)
    void addChestGrid(Container container, int i, int j, CallbackInfo ci) {
        if (container instanceof PlayerEnderChestContainer) {
            // do the exact same thing this method usually does, but instead use ShulkerBoxSlot instead of Slot
            // see ShulkerBoxSlot for why we use it
            for (int k = 0; k < this.containerRows; k++) {
                for (int l = 0; l < 9; l++) {
                    this.addSlot(new EnderChestSlot(container, l + k * 9, i + l * 18, j + k * 18));
                }
            }
            ci.cancel();
        }
    }
}
