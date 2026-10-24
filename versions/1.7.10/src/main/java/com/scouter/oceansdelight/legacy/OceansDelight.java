package com.scouter.oceansdelight.legacy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import vectorwing.farmersdelight.legacy.Content;
import vectorwing.farmersdelight.legacy.LegacyFood;
import vectorwing.farmersdelight.legacy.api.FoodDefinition;
import vectorwing.farmersdelight.legacy.api.SourceRecipes;
@Mod(modid="oceansdelight",name="Ocean's Delight Legacy",version=BuildVersion.VERSION,acceptedMinecraftVersions="[1.7.10]",dependencies="required-after:farmersdelight@[1.0.2,)")
public final class OceansDelight {
 public static final java.util.Map<String,Item> ITEMS=new java.util.LinkedHashMap<String,Item>();
 public static GuardianSoup soup;
 public static boolean drops;
 @SidedProxy(clientSide="com.scouter.oceansdelight.legacy.ClientProxy",serverSide="com.scouter.oceansdelight.legacy.CommonProxy") public static CommonProxy proxy;
 @Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
  net.minecraftforge.common.config.Configuration config=new net.minecraftforge.common.config.Configuration(event.getSuggestedConfigurationFile());config.load();
  drops=config.getBoolean("mobDrops","general",true,"Enable the original knife squid/elder drops and guardian drop.");if(config.hasChanged()) config.save();
  FoodDefinition[] foods;
  try(java.io.InputStreamReader reader=new java.io.InputStreamReader(OceansDelight.class.getResourceAsStream("/oceansdelight/foods.json"),java.nio.charset.StandardCharsets.UTF_8)) { foods=new com.google.gson.Gson().fromJson(reader,FoodDefinition[].class); }
  catch(java.io.IOException e) { throw new IllegalStateException("Cannot load Ocean food contract",e); }
  for(FoodDefinition food:foods) add(food.id,new LegacyFood(food));
  add("guardian",new Item());soup=new GuardianSoup();soup.setBlockName("oceansdelight.guardian_soup").setBlockTextureName("oceansdelight:guardian_soup");GameRegistry.registerBlock(soup,ItemBlock.class,"guardian_soup");ITEMS.put("guardian_soup",Item.getItemFromBlock(soup));
  net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new Acquisition());proxy.preInit();
 }
 @Mod.EventHandler public void init(FMLInitializationEvent event) {
  SourceRecipes.addCatalog("oceansdelight",OceansDelight.class,"/oceansdelight/source_recipes.json");
  vectorwing.farmersdelight.legacy.SourceTags.addCatalog(OceansDelight.class,"/oceansdelight/source_tags.json");
  java.util.List<String> roots=new java.util.ArrayList<String>();
  roots.add("oceansdelight:tentacles");
  if(drops && vectorwing.farmersdelight.legacy.UABCompatibility.mob("Guardian")) {
   roots.add("oceansdelight:guardian");roots.add("oceansdelight:elder_guardian_slab");
  }
  vectorwing.farmersdelight.legacy.AddonRecipes.register("oceansdelight",roots.toArray(new String[roots.size()]));
 }
 private static void add(String id,Item item) { item.setUnlocalizedName("oceansdelight."+id).setTextureName("oceansdelight:"+id).setCreativeTab(Content.TAB);GameRegistry.registerItem(item,id);ITEMS.put(id,item); }
}
