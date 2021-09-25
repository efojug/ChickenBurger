
package net.mcreator.burger.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import net.mcreator.burger.item.ItemEnd;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class RecipeEndLeggingsRecipe extends ElementsBurger.ModElement {
	public RecipeEndLeggingsRecipe(ElementsBurger instance) {
		super(instance, 16);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(Items.DIAMOND_LEGGINGS, (int) (1)), new ItemStack(ItemEnd.legs, (int) (1)), 0F);
	}
}
