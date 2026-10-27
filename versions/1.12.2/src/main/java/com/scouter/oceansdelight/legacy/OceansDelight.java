package com.scouter.oceansdelight.legacy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import com.scouter.oceansdelight.legacy.compat.Delight;
import com.google.gson.JsonObject;
@Mod(modid="oceansdelight",name="Ocean's Delight Legacy",version=BuildVersion.VERSION,acceptedMinecraftVersions="[1.12.2]",dependencies="required-after:farmersdelight@[1.0.2,)")
public final class OceansDelight {
 public static final java.util.Map<String,Item> ITEMS=new java.util.LinkedHashMap<String,Item>();
 public static Block soup;
 public static boolean drops;
 @SidedProxy(clientSide="com.scouter.oceansdelight.legacy.ClientProxy",serverSide="com.scouter.oceansdelight.legacy.CommonProxy") public static CommonProxy proxy;
 @Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
  net.minecraftforge.common.config.Configuration config=new net.minecraftforge.common.config.Configuration(event.getSuggestedConfigurationFile());config.load();
  drops=config.getBoolean("mobDrops","general",true,"Enable the original knife squid/elder drops and guardian drop.");if(config.hasChanged()) config.save();
  Delight.select();
  JsonObject[] foods;
  try(java.io.InputStreamReader reader=new java.io.InputStreamReader(OceansDelight.class.getResourceAsStream("/oceansdelight/foods.json"),java.nio.charset.StandardCharsets.UTF_8)) { foods=new com.google.gson.Gson().fromJson(reader,JsonObject[].class); }
  catch(java.io.IOException e) { throw new IllegalStateException("Cannot load Ocean food contract",e); }
  for(JsonObject food:foods) add(food.get("id").getAsString(),Delight.integration.food(food));
  add("guardian",new Item());soup=Delight.integration.guardianSoup();soup.setRegistryName("oceansdelight","guardian_soup");GameRegistry.findRegistry(Block.class).register(soup);add("guardian_soup",new ItemBlock(soup));
  net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new Acquisition());proxy.preInit();
 }
 @Mod.EventHandler public void init(FMLInitializationEvent event) { Delight.integration.init(); }
 @Mod.EventHandler public void postInit(net.minecraftforge.fml.common.event.FMLPostInitializationEvent event) { Delight.integration.postInit(); }
 private static void add(String id,Item item) { item.setTranslationKey("oceansdelight."+id).setCreativeTab(Delight.integration.tab()).setRegistryName("oceansdelight",id);GameRegistry.findRegistry(Item.class).register(item);ITEMS.put(id,item); }
}
