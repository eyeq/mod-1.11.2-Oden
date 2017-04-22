package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemOdenB3 extends UItemSwordFood {
    public ItemOdenB3(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 0), 0.15F);
        this.setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 200, 4), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }
}
