package eyeq.oden.block;

import eyeq.oden.Oden;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKonjacLava extends BlockKonjac {
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Oden.konjacLava;
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        super.onEntityWalk(world, pos, entity);
        entity.attackEntityFrom(DamageSource.LAVA, 2.0F);
        entity.setFire(6);
    }
}
