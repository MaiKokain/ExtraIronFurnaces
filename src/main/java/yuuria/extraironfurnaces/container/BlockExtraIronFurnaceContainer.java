package yuuria.extraironfurnaces.container;

import ironfurnaces.container.furnaces.BlockIronFurnaceContainerBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import yuuria.extraironfurnaces.init.Registration;
import yuuria.extraironfurnaces.tileentity.BlockExtraIronFurnaceTile;

public class BlockExtraIronFurnaceContainer extends BlockIronFurnaceContainerBase {
    public BlockExtraIronFurnaceContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.EXTRA_IRONFURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
        this.te = (BlockExtraIronFurnaceTile) world.getBlockEntity(pos);
    }
}
