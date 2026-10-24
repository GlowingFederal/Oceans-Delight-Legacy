package com.scouter.oceansdelight.legacy;
public final class GuardianSoup extends vectorwing.farmersdelight.legacy.ServingBlock {
 public GuardianSoup() { super("oceansdelight","guardian_soup",false,"oceansdelight:bowl_of_guardian_soup",true); }
 public net.minecraft.util.math.AxisAlignedBB getBoundingBox(net.minecraft.block.state.IBlockState state,net.minecraft.world.IBlockAccess world,net.minecraft.util.math.BlockPos pos) {
  boolean empty=portions(world.getTileEntity(pos))==0;double inset=empty?1D/16:0;
  return new net.minecraft.util.math.AxisAlignedBB(inset,0,inset,1-inset,empty?2D/16:15D/16,1-inset);
 }
}
