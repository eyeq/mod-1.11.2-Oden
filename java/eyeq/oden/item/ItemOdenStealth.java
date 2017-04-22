package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemOdenStealth extends UItemSwordFood {
    public ItemOdenStealth(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 90, 0), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);
        if(!isSelected) {
            return;
        }
        if(entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isHandActive()) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 200, 0));
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 4));
        }
    }
}
