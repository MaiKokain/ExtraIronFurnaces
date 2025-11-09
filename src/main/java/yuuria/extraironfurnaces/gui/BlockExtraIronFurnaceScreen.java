package yuuria.extraironfurnaces.gui;

import ironfurnaces.gui.furnaces.BlockIronFurnaceScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import yuuria.extraironfurnaces.container.BlockExtraIronFurnaceContainer;

public class BlockExtraIronFurnaceScreen extends BlockIronFurnaceScreenBase<BlockExtraIronFurnaceContainer> {
    public BlockExtraIronFurnaceScreen(BlockExtraIronFurnaceContainer blockExtraIronFurnaceContainer, Inventory inv, Component name) {
        super(blockExtraIronFurnaceContainer, inv, name);
    }
}
