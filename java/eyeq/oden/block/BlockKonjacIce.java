package eyeq.oden.block;

import eyeq.oden.Oden;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKonjacIce extends BlockKonjac {
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Oden.konjacIce;
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        if(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ < 100) {
            entity.motionX *= 1.8;
            entity.motionZ *= 1.8;
        }
    }
}
