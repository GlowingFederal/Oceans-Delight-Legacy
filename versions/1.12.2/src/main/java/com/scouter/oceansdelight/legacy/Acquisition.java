package com.scouter.oceansdelight.legacy;
public final class Acquisition {
 @net.minecraftforge.fml.common.eventhandler.SubscribeEvent public void drops(net.minecraftforge.event.entity.living.LivingDropsEvent event) {
  net.minecraft.entity.EntityLivingBase entity=event.getEntityLiving();
  if(entity.world.isRemote || !OceansDelight.drops) return;
  net.minecraft.entity.Entity killer=event.getSource().getTrueSource();
  boolean knife=killer instanceof net.minecraft.entity.EntityLivingBase && com.scouter.oceansdelight.legacy.compat.Delight.integration.knife(((net.minecraft.entity.EntityLivingBase)killer).getHeldItemMainhand());
  String id=null;
  if(entity instanceof net.minecraft.entity.passive.EntitySquid && knife) id="tentacles";
  if(entity instanceof net.minecraft.entity.monster.EntityElderGuardian) { if(knife) id="elder_guardian_slab"; } else if(entity instanceof net.minecraft.entity.monster.EntityGuardian) id="guardian";
  if(id!=null) event.getDrops().add(new net.minecraft.entity.item.EntityItem(entity.world,entity.posX,entity.posY,entity.posZ,new net.minecraft.item.ItemStack(OceansDelight.ITEMS.get(id))));
 }
}

