package eyeq.oden.block;

import eyeq.oden.Oden;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKonjacPoisonous extends BlockKonjac {
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Oden.konjacPoisonous;
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        entity.motionX *= 0.7;
        entity.motionZ *= 0.7;
    }
}
