package com.scouter.oceansdelight.legacy.compat;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonPlaceableFoodBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/** The API's constructor assumes its own item namespace; supply the addon serving explicitly. */
public final class AlternativeGuardianSoup extends AddonPlaceableFoodBlock {
 public AlternativeGuardianSoup() { super(4,"guardian_soup","minecraft:bowl",true);setHardness(0.5F); }
 protected ItemStack getServingStackForServings(int servings) {
  return servings>0 ? RecipeCatalog.stack("oceansdelight:bowl_of_guardian_soup") : ItemStack.EMPTY;
 }
 public AxisAlignedBB getBoundingBox(IBlockState state,IBlockAccess world,BlockPos pos) {
  boolean empty=getActualState(state,world,pos).getValue(getServingsProperty())==0;
  double inset=empty ? 1D/16 : 0;
  return new AxisAlignedBB(inset,0,inset,1-inset,empty ? 2D/16 : 15D/16,1-inset);
 }
}
