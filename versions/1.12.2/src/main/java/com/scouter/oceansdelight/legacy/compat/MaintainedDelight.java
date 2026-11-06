package com.scouter.oceansdelight.legacy.compat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vectorwing.farmersdelight.legacy.*;
import vectorwing.farmersdelight.legacy.api.FoodDefinition;
import vectorwing.farmersdelight.legacy.api.SourceRecipes;
import com.scouter.oceansdelight.legacy.OceansDelight;
public final class MaintainedDelight implements DelightIntegration {
 public CreativeTabs tab() { return Content.TAB; }
 public Item food(JsonObject food) { return new LegacyFood(new Gson().fromJson(food,FoodDefinition.class)); }
 public boolean knife(ItemStack stack) { return Ingredients.isKnife(stack); }
 public Block richFarmland() { return Content.richFarmland; }
 public void init() {
  SourceRecipes.addCatalog("oceansdelight",OceansDelight.class,"/oceansdelight/source_recipes.json");
  SourceTags.addCatalog(OceansDelight.class,"/oceansdelight/source_tags.json");
  java.util.List<String> roots=new java.util.ArrayList<String>();
  if(OceansDelight.drops) roots.addAll(java.util.Arrays.asList("oceansdelight:tentacles","oceansdelight:guardian","oceansdelight:elder_guardian_slab"));
  AddonRecipes.register("oceansdelight",roots.toArray(new String[roots.size()]));
 }
 public void postInit() {}
 public Block guardianSoup() { return new com.scouter.oceansdelight.legacy.GuardianSoup(); }
}
