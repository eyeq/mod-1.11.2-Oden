package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemOdenGold extends UItemSwordFood {
    public ItemOdenGold(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 1), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 90, 0), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 90, 0), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.SPEED, 90, 0), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public boolean hasEffect(ItemStack item) {
        return true;
    }
}
