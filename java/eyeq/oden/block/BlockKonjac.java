package eyeq.oden.block;

import eyeq.oden.Oden;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKonjac extends BlockSlime {
    public static final AxisAlignedBB KONJAC_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0);

    public BlockKonjac() {
        this.setHardness(0.2F);
        this.setResistance(0.0F);
        this.setLightOpacity(3);
        this.setSoundType(SoundType.SLIME);
        this.setCreativeTab(Oden.TAB_ODEN);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Oden.konjac;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 4;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
        return KONJAC_AABB;
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        entity.motionX *= 0.8;
        entity.motionZ *= 0.8;
    }
}
