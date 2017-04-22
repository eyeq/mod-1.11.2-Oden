package eyeq.oden.item;

import eyeq.oden.Oden;
import eyeq.util.item.UItemSwordFood;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemOdenWithered extends UItemSwordFood {
    public ItemOdenWithered(ToolMaterial material, int amount, float saturation) {
        super(material, amount, saturation);
        this.setNoRepair();
        this.setEatPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 0), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1800, 0), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.WITHER, 1800, 2), 1.0F);
        this.addEatPotionEffect(new PotionEffect(MobEffects.HUNGER, 1800, 0), 1.0F);
        this.setPotionEffect(new PotionEffect(MobEffects.WITHER, 400, 1), 1.0F);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);
        if(!isSelected) {
            return;
        }
        if(entity instanceof EntityLivingBase) {
            if(itemRand.nextInt(20) == 0) {
                ((EntityLivingBase) entity).heal(1.0F);
            }
            if(itemRand.nextInt(20) == 0) {
                stack.damageItem(-1, (EntityLivingBase) entity);
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        EntityWitherSkull entityWitherSkull = new EntityWitherSkull(attacker.world, attacker, target.posX - attacker.posX, target.posY - attacker.posY - 3.0F, target.posZ - attacker.posZ);
        entityWitherSkull.posX = attacker.posX;
        entityWitherSkull.posY = attacker.posY + 3.0F;
        entityWitherSkull.posZ = attacker.posZ;
        attacker.world.spawnEntity(entityWitherSkull);
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        boolean flag = false;
        for(int x = -1; x < 2; x++) {
            for(int y = 0; y < 4; y++) {
                for(int z = -1; z < 2; z++) {
                    BlockPos pos = new BlockPos(player.posX + x, player.posY + y, player.posZ + z);
                    flag |= world.destroyBlock(pos, true);
                }
            }
        }
        if(flag) {
            ItemStack itemStack = player.getHeldItem(hand);
            itemStack.damageItem(10, player);
            world.playEvent(player, 1012, player.getPosition(), 0);
        }
        return super.onItemRightClick(world, player, hand);
    }
}
