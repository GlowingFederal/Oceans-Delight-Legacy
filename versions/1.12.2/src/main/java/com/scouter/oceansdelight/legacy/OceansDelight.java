package com.scouter.oceansdelight.legacy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import vectorwing.farmersdelight.legacy.Content;
import vectorwing.farmersdelight.legacy.LegacyFood;
import vectorwing.farmersdelight.legacy.api.FoodDefinition;
import vectorwing.farmersdelight.legacy.api.SourceRecipes;
@Mod(modid="oceansdelight",name="Ocean's Delight Legacy",version=BuildVersion.VERSION,acceptedMinecraftVersions="[1.12.2]",dependencies="required-after:farmersdelight@[1.0.2,)")
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
  add("guardian",new Item());soup=new GuardianSoup();soup.setRegistryName("oceansdelight","guardian_soup");GameRegistry.findRegistry(Block.class).register(soup);add("guardian_soup",new ItemBlock(soup));
  net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new Acquisition());proxy.preInit();
 }
 @Mod.EventHandler public void init(FMLInitializationEvent event) {
  SourceRecipes.addCatalog("oceansdelight",OceansDelight.class,"/oceansdelight/source_recipes.json");
  vectorwing.farmersdelight.legacy.SourceTags.addCatalog(OceansDelight.class,"/oceansdelight/source_tags.json");
  java.util.Set<String> unavailable=new java.util.HashSet<String>();
  unavailable.add("oceansdelight:stuffed_cod");
  
  for(java.util.Map.Entry<String,com.google.gson.JsonObject> entry:new java.util.LinkedHashMap<String,com.google.gson.JsonObject>(SourceRecipes.all()).entrySet()) {
   if(!entry.getKey().startsWith("oceansdelight:")) continue;
   String json=entry.getValue().toString();
   for(String item:unavailable) if(json.contains("\""+item+"\"")) { SourceRecipes.defer(entry.getKey(),"Unavailable upstream acquisition: "+item+"; await original content/provider such as UAB");break; }
  }
 }
 private static void add(String id,Item item) { item.setTranslationKey("oceansdelight."+id).setCreativeTab(Content.TAB).setRegistryName("oceansdelight",id);GameRegistry.findRegistry(Item.class).register(item);ITEMS.put(id,item); }
}
