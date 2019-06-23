package jobicade.toolsdoneright.item;

import static jobicade.toolsdoneright.Identifier.Format.SNAKE;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import jobicade.toolsdoneright.Identifier;
import jobicade.toolsdoneright.Items;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;

public class ToolSet {
    protected final ToolMaterial material;
    protected final Identifier baseName;
    private final Map<ToolType, Item> itemMap = new EnumMap<>(ToolType.class);

    public ToolSet(ToolMaterial material, Identifier baseName) {
        this.material = material;
        this.baseName = baseName;

        for(ToolType type : ToolType.values()) {
            Item item = createItem(type);
            Items.setNames(item, getIdentifier(type));
            itemMap.put(type, item);
        }
    }

    public Collection<Item> getItems() {
        return itemMap.values();
    }

    protected final Identifier getIdentifier(ToolType type) {
        Stream<String> tokensStream = Stream.concat(
            baseName.getTokens().stream(),
            type.getExtension().getTokens().stream());

        return new Identifier(tokensStream);
    }

    protected Item createItem(ToolType type) {
        switch(type) {
            case SWORD: return new ItemSword(material);
            case PICKAXE: return new ItemPickaxe(material) {};
            case AXE: return new ItemAxe(material, 8.0f, -3.2f) {};
            case SHOVEL: return new ItemSpade(material);
            case HOE: return new ItemHoe(material);
            default: throw new IllegalArgumentException(type == null ? "null" : type.getExtension().format(SNAKE));
        }
    }

    protected void registerModels(ToolType type, Item item) {
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }

    public final void registerModels() {
        for(Entry<ToolType, Item> entry : itemMap.entrySet()) {
            registerModels(entry.getKey(), entry.getValue());
        }
    }

    public final Item getItem(ToolType type) {
        return itemMap.get(type);
    }

    public enum ToolType {
        SWORD(new Identifier("sword")),
        PICKAXE(new Identifier("pickaxe")),
        AXE(new Identifier("axe")),
        SHOVEL(new Identifier("shovel")),
        HOE(new Identifier("hoe"));

        private final Identifier extension;
        ToolType(Identifier extension) {
            this.extension = extension;
        }

        private Identifier getExtension() {
            return extension;
        }
    }
}
