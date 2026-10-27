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
  for(java.util.Map.Entry<String,JsonObject> entry:new java.util.LinkedHashMap<String,JsonObject>(SourceRecipes.all()).entrySet()) {
   if(entry.getKey().startsWith("oceansdelight:") && entry.getValue().toString().contains("\"oceansdelight:stuffed_cod\""))
    SourceRecipes.defer(entry.getKey(),"Unavailable upstream acquisition: oceansdelight:stuffed_cod");
  }
 }
 public void postInit() {}
 public Block guardianSoup() { return new com.scouter.oceansdelight.legacy.GuardianSoup(); }
}
