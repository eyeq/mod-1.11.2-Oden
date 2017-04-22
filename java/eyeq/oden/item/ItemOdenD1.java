package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemOdenD1 extends UItemSwordFood {
    public ItemOdenD1(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0), 1.0F);
        this.setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }
}
