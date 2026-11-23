package com.scouter.oceansdelight.legacy.compat;
import com.wdcftgg.farmersdelightlegacy.api.food.AddonPlaceableFoodBlock;
import com.wdcftgg.farmersdelightlegacy.common.registry.ModSounds;
import com.wdcftgg.farmersdelightlegacy.common.tile.TileEntityFeast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/** The API's constructor assumes its own item namespace; supply the addon serving explicitly. */
public final class AlternativeGuardianSoup extends AddonPlaceableFoodBlock {
 public AlternativeGuardianSoup() { super(4,"guardian_soup","minecraft:bowl",true);setHardness(0.5F); }
 protected ItemStack getServingStackForServings(int servings) {
  return servings>0 ? RecipeCatalog.stack("oceansdelight:bowl_of_guardian_soup") : ItemStack.EMPTY;
 }
 @Override
 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                 EnumHand hand, EnumFacing face, float x, float y, float z) {
  TileEntity tile = world.getTileEntity(pos);
  if (!(tile instanceof TileEntityFeast)) return false;
  TileEntityFeast feast = (TileEntityFeast) tile;
  int servings = clampServings(feast.getServings());
  if (servings == 0) return super.onBlockActivated(world, pos, state, player, hand, face, x, y, z);

  ItemStack bowl = player.getHeldItem(hand);
  if (bowl.getItem() != Items.BOWL) {
   // The base implementation consumes the main-hand interaction even if a bowl is in the offhand.
   if (hand == EnumHand.OFF_HAND || player.getHeldItemOffhand().getItem() == Items.BOWL) return false;
   return super.onBlockActivated(world, pos, state, player, hand, face, x, y, z);
  }
  if (world.isRemote) return true;
  ItemStack portion = getServingStackForServings(servings);
  if (portion.isEmpty()) return false;

  if (!player.capabilities.isCreativeMode) {
   bowl.shrink(1);
   if (bowl.isEmpty()) player.setHeldItem(hand, ItemStack.EMPTY);
  }
  boolean creativeOverflow = player.capabilities.isCreativeMode
          && player.inventory.getFirstEmptyStack() < 0 && player.inventory.storeItemStack(portion) < 0;
  if (creativeOverflow || !player.addItemStackToInventory(portion)) player.dropItem(portion, false);
  player.inventory.markDirty();
  player.openContainer.detectAndSendChanges();
  net.minecraft.stats.StatBase bowlUse = net.minecraft.stats.StatList.getObjectUseStats(Items.BOWL);
  if (bowlUse != null) player.addStat(bowlUse);

  int remaining = servings - 1;
  feast.setServings(remaining);
  world.setBlockState(pos, state.withProperty(getServingsProperty(), remaining), 3);
  world.playSound(null, pos, ModSounds.foodTakePortion, SoundCategory.BLOCKS, 1F, 1F);
  return true;
 }
 public AxisAlignedBB getBoundingBox(IBlockState state,IBlockAccess world,BlockPos pos) {
  boolean empty=getActualState(state,world,pos).getValue(getServingsProperty())==0;
  double inset=empty ? 1D/16 : 0;
  return new AxisAlignedBB(inset,0,inset,1-inset,empty ? 2D/16 : 15D/16,1-inset);
 }
}
