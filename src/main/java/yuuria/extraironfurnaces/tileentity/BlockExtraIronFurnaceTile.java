package yuuria.extraironfurnaces.tileentity;

import ironfurnaces.container.furnaces.BlockCopperFurnaceContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ModConfigSpec;
import yuuria.extraironfurnaces.ExtraIronFurnacesConfig;
import yuuria.extraironfurnaces.container.BlockExtraIronFurnaceContainer;
import yuuria.extraironfurnaces.init.Registration;

import java.util.Optional;

public class BlockExtraIronFurnaceTile extends ExtraIronFurnacesTileBase {
    public BlockExtraIronFurnaceTile(BlockPos pos, BlockState state)
    {
        super(Registration.EXTRA_IRONFURNACE_TILE.get(), pos, state);
    }

    @Override
    public String IgetName() {
        return "container.ironfurnaces.copper_furnace";
    }

    @Override
    public ModConfigSpec.IntValue getCookTimeConfig() {
        return ExtraIronFurnacesConfig.extrafurnaceSpeed;
    }

    @Override
    protected Integer getMultSmeltCount() {
        return ExtraIronFurnacesConfig.extrafurnaceMultiSmeltCount.get();
    }

    @Override
    protected Integer getOutputMult() {
        return ExtraIronFurnacesConfig.extrafurnaceOutputMultiplier.get();
    }

    @Override
    public AbstractContainerMenu IcreateMenu(int i, Inventory inventory, Player player) {
        return new BlockExtraIronFurnaceContainer(i, level, worldPosition, inventory, player);
//        return new BlockCopperFurnaceContainer(i, level, worldPosition, inventory, player);
    }

}
