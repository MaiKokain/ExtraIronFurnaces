package yuuria.extraironfurnaces.blocks;

import ironfurnaces.blocks.furnaces.BlockIronFurnaceBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import yuuria.extraironfurnaces.init.Registration;
import yuuria.extraironfurnaces.tileentity.BlockExtraIronFurnaceTile;

public class BlockExtraIronFurnace extends BlockIronFurnaceBase {
    public static final String FURNACE_NAME = "copper_furnace";

    public BlockExtraIronFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockExtraIronFurnaceTile(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, Registration.EXTRA_IRONFURNACE_TILE.get());
    }
}
