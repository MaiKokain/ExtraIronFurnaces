package yuuria.extraironfurnaces.items;

import ironfurnaces.items.ItemFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemExtraIronFurnace extends ItemFurnace {
    public ItemExtraIronFurnace(Block block, Properties properties) {
        super(block, properties);
    }

    protected static int getCookTime(ItemStack stack) {
        return 1;
    }
}
