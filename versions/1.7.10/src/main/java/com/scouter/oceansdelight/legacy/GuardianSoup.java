package com.scouter.oceansdelight.legacy;
public final class GuardianSoup extends vectorwing.farmersdelight.legacy.ServingBlock {
 public GuardianSoup() { super("oceansdelight","guardian_soup",false,"oceansdelight:bowl_of_guardian_soup",true); }
 public void setBlockBoundsBasedOnState(net.minecraft.world.IBlockAccess world,int x,int y,int z) {
  boolean empty=portions(world.getTileEntity(x,y,z))==0;float inset=empty?1F/16:0;
  setBlockBounds(inset,0,inset,1-inset,empty?2F/16:15F/16,1-inset);
 }
}
