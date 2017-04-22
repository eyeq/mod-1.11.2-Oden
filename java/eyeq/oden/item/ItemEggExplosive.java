package eyeq.oden.item;

import eyeq.oden.entity.projectile.EntityEggExplosive;
import eyeq.util.item.ItemThrow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemEggExplosive extends ItemThrow {
    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ENTITY_EGG_THROW;
    }

    @Override
    public EntityThrowable createEntityThrowable(World world, EntityPlayer player) {
        return new EntityEggExplosive(world, player);
    }
}
