package yuuria.extraironfurnaces.tileentity;

import ironfurnaces.tileentity.furnaces.BlockIronFurnaceTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.nullness.qual.Nullable;
import yuuria.extraironfurnaces.ExtraIronFurnaces;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class ExtraIronFurnacesTileBase extends BlockIronFurnaceTileBase {
    public ExtraIronFurnacesTileBase(BlockEntityType<?> tileentitytypeIn, BlockPos pos, BlockState state) {
        super(tileentitytypeIn, pos, state);
    }

    protected Integer getMultSmeltCount() {
        return null;
    }

    protected Integer getOutputMult() {
        return null;
    }

    @Override
    protected void smelt(@Nullable RecipeHolder<?> recipeHolder) {
        ExtraIronFurnaces.LOGGER.info("CAlled SMELT FROM SPECIAL");
        if (recipeHolder == null) {
            ExtraIronFurnaces.LOGGER.info("FAILED SMELT");
            return;
        };
        ItemStack itemStack = this.getItem(INPUT);
        ItemStack itemStack1 = recipeHolder.value().getResultItem(RegistryAccess.EMPTY);
//        if ((itemStack1.getCount() * getOutputMult()) < itemStack1.getMaxStackSize()) {
//            smeltItem(recipeHolder);
//        } else {
//            smeltItemMult(recipeHolder, getMultSmeltCount());
//        }

        if ((itemStack.getCount() + itemStack1.getCount() * getOutputMult()) < itemStack1.getMaxStackSize()) {
            smeltItemMult(recipeHolder, getMultSmeltCount());
        } else {
            smeltItem(recipeHolder);
        }

    }

    @Override
    protected void factorySmelt(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe, int slot) {
        ExtraIronFurnaces.LOGGER.info("CALLED FACTSMELT FROM SPECIAL");
        if (recipe == null) return;
        ItemStack itemStack = this.getItem(INPUT);
        ItemStack itemStack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);

//        if ((itemStack1.getCount() * getOutputMult()) > itemStack1.getMaxStackSize()) {
//            smeltFactoryItem(recipe, slot);
//        } else {
//            smeltFactoryItemMult(recipe, slot, getMultSmeltCount());
//        }

        if ((itemStack.getCount() + itemStack1.getCount() * getMultSmeltCount()) < itemStack1.getMaxStackSize()) {
            smeltFactoryItemMult(recipe, slot, getMultSmeltCount());
        } else {
            smeltFactoryItem(recipe,slot);
        }
    }

    @Override
    protected void smeltItem(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe) {
        ExtraIronFurnaces.LOGGER.info("CALLED SMELTITEM FROM SPECIAL");
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.getItem(INPUT);
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
            ItemStack itemstack2 = this.getItem(OUTPUT);
            if (itemstack2.isEmpty()) {
//                this.setItem(OUTPUT, itemstack1.copyWithCount(itemstack1.getCount() * getOutputMult()));
                this.setItem(
                        OUTPUT,
                        new ItemStack(itemstack1.getItem(), itemstack1.getCount() * getOutputMult())
                );
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount() * getOutputMult());
            }
            if (!this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.getItem(FUEL).isEmpty() && this.getItem(FUEL).getItem() == Items.BUCKET) {
                this.setItem(FUEL, new ItemStack(Items.WATER_BUCKET));
            }
            itemstack.shrink(1);
        }
    }

    @Override
    protected void smeltFactoryItem(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe, int slot) {
        ExtraIronFurnaces.LOGGER.info("CALLED SMELTFACTITEM FROM SPECIAL");
        int outputSlot = slot + 6;
        if (recipe != null && this.canFactorySmelt(recipe, slot)) {
            ItemStack itemstack = this.getItem(slot);
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
            ItemStack itemstack2 = this.getItem(outputSlot);
            if (itemstack2.isEmpty()) {
//                this.setItem(outputSlot, itemstack1.copyWithCount(itemstack1.getCount() * getOutputMult()));
                this.setItem(
                        outputSlot,
                        new ItemStack(itemstack1.getItem(), itemstack1.getCount() * getOutputMult())
                );
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount() * getOutputMult());
            }
            if (!this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }
            itemstack.shrink(1);
        }
    }

    @Override
    protected void smeltItemMult(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe, int div) {
        ExtraIronFurnaces.LOGGER.info("CALLED SMELTITEMMULT FROM SPECIAL");
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.getItem(INPUT);
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
            ItemStack itemstack2 = this.getItem(OUTPUT);
            int maxCanSmelt = (64 - itemstack2.getCount()) / itemstack1.getCount() * getOutputMult();
            int wantToSmeltCount = Math.min(Math.min(div, maxCanSmelt), itemstack.getCount());
            int whenSmelted = itemstack1.getCount() * wantToSmeltCount;
            int decrement = whenSmelted / itemstack1.getCount();
            if (itemstack2.isEmpty()) {
//                this.setItem(OUTPUT, new ItemStack(itemstack1.copyWithCount(itemstack1.getCount() * getOutputMult()).getItem(), whenSmelted));
                this.setItem(
                        OUTPUT,
                        new ItemStack(itemstack1.copy().getItem(), whenSmelted * getOutputMult())
                );
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(whenSmelted * getOutputMult());
            }
            if (!this.level.isClientSide) {
                for (int i = 0; i < decrement; i++) {
                    this.setRecipeUsed(recipe);
                }
            }

            if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.getItem(FUEL).isEmpty() && this.getItem(FUEL).getItem() == Items.BUCKET) {
                this.setItem(FUEL, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(decrement);
        }
    }

    @Override
    protected void smeltFactoryItemMult(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe, int slot, int div) {
        ExtraIronFurnaces.LOGGER.info("CALLED SMELTFACTITEMMULT FROM SPECIAL");
        int outputSlot = slot + 6;
        if (recipe != null && this.canFactorySmelt(recipe, slot)) {
            ItemStack itemstack = this.getItem(slot);
            ItemStack itemstack1 = recipe.value().getResultItem(RegistryAccess.EMPTY);
            ItemStack itemstack2 = this.getItem(outputSlot);
            int maxCanSmelt = (64 - itemstack2.getCount()) / itemstack1.getCount();
            int wantToSmeltCount = Math.min(Math.min(div, maxCanSmelt), itemstack.getCount());
            int whenSmelted = itemstack1.getCount() * wantToSmeltCount;
            int decrement = whenSmelted / itemstack1.getCount();
            if (itemstack2.isEmpty()) {
//                this.setItem(outputSlot, new ItemStack(itemstack1.copyWithCount(itemstack1.getCount() * getOutputMult()).getItem()));
                this.setItem(
                        outputSlot,
                        new ItemStack(itemstack1.getItem(), itemstack1.getCount() * getOutputMult())
                );
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(whenSmelted * getOutputMult());
            }
            if (!this.level.isClientSide) {
                for (int i = 0; i < decrement; i++) {
                    this.setRecipeUsed(recipe);
                }
            }
            itemstack.shrink(decrement);
        }
    }

    @Override
    protected boolean canSmelt(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe) {
        if (!this.getItem(0).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.value().getResultItem(RegistryAccess.EMPTY);
            if (!recipeOutput.isEmpty()) {
                ItemStack output = this.getItem(OUTPUT);
                if (output.isEmpty()) return true;
                else if (!ItemStack.isSameItemSameComponents(output, recipeOutput)) return false;
                else return output.getCount() + recipeOutput.getCount() <= Math.min(output.getMaxStackSize(), 64);
            }
        }
        return false;
    }

    @Override
    protected boolean canFactorySmelt(@org.jetbrains.annotations.Nullable RecipeHolder<?> recipe, int slot) {
        int outputSlot = slot + 6;
        if (!this.getItem(slot).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.value().getResultItem(RegistryAccess.EMPTY);
            if (!recipeOutput.isEmpty()) {
                ItemStack output = this.getItem(outputSlot);
                if (output.isEmpty()) return true;
                else if (!ItemStack.isSameItemSameComponents(output, recipeOutput)) return false;
                else return output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize();
            }
        }
        return false;
    }
}
