package me.frogtato.dragoneggmod.mixin;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DragonEggBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/* Stops the egg from being placed in shulker boxes and bundles */
@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "canFitInsideContainerItems", at = @At("HEAD"), cancellable = true)
    void canFitInsideContainerItems(CallbackInfoReturnable<Boolean> cir) {
        if (this.getBlock() instanceof DragonEggBlock) {
            cir.setReturnValue(false);
        }
    }
}
