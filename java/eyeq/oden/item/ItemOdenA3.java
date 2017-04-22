package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemOdenA3 extends UItemSwordFood {
    public ItemOdenA3(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 0), 0.3F);
        this.setAttackFire(2, 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }
}
