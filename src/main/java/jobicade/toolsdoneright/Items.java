package jobicade.toolsdoneright;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static jobicade.toolsdoneright.Identifier.Format.*;

public class Items {
    public static final ToolMaterial GEM      = EnumHelper.addToolMaterial("gem", 2, 200, 10.0f, 2.5f, 30);
    public static final ToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("obsidian", 3, 4000, 3.5f, 2.0f, 5);
    public static final ToolMaterial END      = EnumHelper.addToolMaterial("end", 3, 1000, 8.0f, 3.0f, 20);

    public static final Item emeraldPickaxe = new ItemEmeraldPickaxe();

    public static void init() {
        registerItem(emeraldPickaxe, new Identifier("emeraldPickaxe", HEADLESS));
    }

    private static void registerItem(Item item, Identifier name) {
        Identifier snake = name.withFormat(SNAKE);

        item.setRegistryName(snake);
        item.setUnlocalizedName(name.withFormat(HEADLESS).getResourcePath());
        GameRegistry.registerItem(item, snake.getResourcePath());
    }
}
