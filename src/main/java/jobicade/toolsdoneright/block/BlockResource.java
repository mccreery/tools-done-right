package jobicade.toolsdoneright.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockResource extends Block {
    public BlockResource(Material material, MapColor mapColor) {
        super(material, mapColor);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
}
