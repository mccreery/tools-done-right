package jobicade.toolsdoneright.block;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOreInject extends BlockOre {
    private final Item itemDropped;
    private final int minExp, maxExp;

    public BlockOreInject(Item itemDropped, int minExp, int maxExp) {
        this.itemDropped = itemDropped;
        this.minExp = minExp;
        this.maxExp = maxExp;

        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemDropped;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random random = getRandom(world);

        // Disable exp drops for silk touch
        if(getItemDropped(state, random, fortune) == Item.getItemFromBlock(this)) {
            return 0;
        } else {
            return MathHelper.getInt(random, minExp, maxExp);
        }
    }

    protected final Random getRandom(IBlockAccess blockAccess) {
        return blockAccess instanceof World ? ((World)blockAccess).rand : new Random();
    }
}
