package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemOdenPoison3 extends UItemSwordFood {
    public ItemOdenPoison3(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.POISON, 20, 0), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.HUNGER, 900, 1), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 1), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1200, 0), 1.0F);
        this.setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);
        if(!isSelected) {
            return;
        }
        if(entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200, 0));
        }
    }
}