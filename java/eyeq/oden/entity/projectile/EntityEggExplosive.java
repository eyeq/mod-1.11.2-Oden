package eyeq.oden.entity.projectile;

import eyeq.oden.Oden;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEggExplosive extends EntityThrowable {
    public EntityEggExplosive(World world) {
        super(world);
    }

    public EntityEggExplosive(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityEggExplosive(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }
        for(int i = 0; i < 8; i++) {
            double xSpeed = (this.rand.nextFloat() - 0.5) * 0.08;
            double ySpeed = (this.rand.nextFloat() - 0.5) * 0.08;
            double zSpeed = (this.rand.nextFloat() - 0.5) * 0.08;
            this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, xSpeed, ySpeed, zSpeed, Item.getIdFromItem(Oden.eggExplosive));
        }
        if(this.world.isRemote) {
            return;
        }
        if(this.rand.nextInt(8) == 0) {
            Entity entity;
            if(this.rand.nextInt(32) == 0) {
                entity = new EntityCreeper(this.world);
            } else {
                entity = new EntityPig(this.world);
                ((EntityPig) entity).setGrowingAge(-24000);
            }
            entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.world.spawnEntity(entity);
        }
        this.world.setEntityState(this, (byte) 3);
        this.setDead();
    }
}
