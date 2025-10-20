package me.frogtato.dragoneggmod;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class EnderChestSlot extends Slot {
    public EnderChestSlot(Container container, int i, int j, int k) {
        super(container, i, j, k);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return !(itemStack.is(Blocks.DRAGON_EGG.asItem()));
    }
}
