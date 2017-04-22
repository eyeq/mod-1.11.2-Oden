package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOdenC2 extends UItemSwordFood {
    public ItemOdenC2(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setAttackFire(8, 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        if(!world.isRemote) {
            world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, 6.0F, false);
        }
        return super.onItemUseFinish(stack, world, entity);
    }
}
