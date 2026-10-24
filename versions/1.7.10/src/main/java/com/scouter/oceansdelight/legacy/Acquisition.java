package com.scouter.oceansdelight.legacy;
public final class Acquisition {
 @cpw.mods.fml.common.eventhandler.SubscribeEvent public void drops(net.minecraftforge.event.entity.living.LivingDropsEvent event) {
  net.minecraft.entity.EntityLivingBase entity=event.entityLiving;
  if(entity.worldObj.isRemote || !OceansDelight.drops) return;
  net.minecraft.entity.Entity killer=event.source.getEntity();
  boolean knife=killer instanceof net.minecraft.entity.EntityLivingBase && vectorwing.farmersdelight.legacy.Ingredients.isKnife(((net.minecraft.entity.EntityLivingBase)killer).getHeldItem());
  String id=null;
  // Glow squid inherits EntitySquid in UAB; apply the compatibility and species toggles.
  boolean glow=entity.getClass().getName().equals("ganymedes01.etfuturum.entities.EntityGlowSquid");
  if(entity instanceof net.minecraft.entity.passive.EntitySquid && knife && (!glow || vectorwing.farmersdelight.legacy.UABCompatibility.isMob(entity,"GlowSquid"))) id="tentacles";
  if(vectorwing.farmersdelight.legacy.UABCompatibility.isMob(entity,"Guardian")) {
   // Verified UAB elder flag: data watcher 16 bit zero, persisted as Elder NBT.
   boolean elder=(entity.getDataWatcher().getWatchableObjectByte(16)&1)!=0;
   if(elder) { if(knife) id="elder_guardian_slab"; } else id="guardian";
  }
  
  if(id!=null) for(net.minecraft.entity.item.EntityItem drop:event.drops) if(drop.getEntityItem().getItem()==OceansDelight.ITEMS.get(id)) return;
  if(id!=null) event.drops.add(new net.minecraft.entity.item.EntityItem(entity.worldObj,entity.posX,entity.posY,entity.posZ,new net.minecraft.item.ItemStack(OceansDelight.ITEMS.get(id))));
 }
}
