package eyeq.oden.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemKitchenKnife extends ItemSword {
    public ItemKitchenKnife(ToolMaterial material) {
        super(material);
        this.setNoRepair();
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public boolean hasContainerItem(ItemStack itemStack) {
        return hasContainerItem();
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if(itemStack.getItem() == this) {
            return new ItemStack(this, itemStack.getCount(), itemStack.getMetadata() + 1);
        }
        return ItemStack.EMPTY;
    }
}
