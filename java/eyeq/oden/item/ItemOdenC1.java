package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemOdenC1 extends UItemSwordFood {
    public ItemOdenC1(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 0), 1.0F);
        this.setAttackFire(8, 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }
}
