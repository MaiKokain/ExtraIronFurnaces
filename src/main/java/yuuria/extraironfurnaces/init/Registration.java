package yuuria.extraironfurnaces.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuria.extraironfurnaces.ExtraIronFurnaces;
import yuuria.extraironfurnaces.blocks.BlockExtraIronFurnace;
import yuuria.extraironfurnaces.container.BlockExtraIronFurnaceContainer;
import yuuria.extraironfurnaces.items.ItemExtraIronFurnace;
import yuuria.extraironfurnaces.tileentity.BlockExtraIronFurnaceTile;

import java.util.function.Supplier;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, ExtraIronFurnaces.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, ExtraIronFurnaces.MODID);
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExtraIronFurnaces.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(BuiltInRegistries.MENU, ExtraIronFurnaces.MODID);

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        TILES.register(modEventBus);
        CONTAINERS.register(modEventBus);
    }

    public static final DeferredHolder<Block, BlockExtraIronFurnace> EXTRA_IRONFURNACE = BLOCKS.register(BlockExtraIronFurnace.FURNACE_NAME, () -> new BlockExtraIronFurnace(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredHolder<Item, ItemExtraIronFurnace> EXTRA_IRONFURNACE_ITEM = ITEMS.register(BlockExtraIronFurnace.FURNACE_NAME, () -> new ItemExtraIronFurnace(EXTRA_IRONFURNACE.get(), new Item.Properties()));

    public static final Supplier<BlockEntityType<BlockExtraIronFurnaceTile>> EXTRA_IRONFURNACE_TILE = TILES.register(BlockExtraIronFurnace.FURNACE_NAME, () -> BlockEntityType.Builder.of(BlockExtraIronFurnaceTile::new, EXTRA_IRONFURNACE.get()).build(null));

    public static final Supplier<MenuType<BlockExtraIronFurnaceContainer>> EXTRA_IRONFURNACE_CONTAINER = CONTAINERS.register(BlockExtraIronFurnace.FURNACE_NAME, () -> IMenuTypeExtension.create(
            (winId, playinv, extrada) -> new BlockExtraIronFurnaceContainer(winId, playinv.player.level(), extrada.readBlockPos(), playinv, playinv.player)));
}
