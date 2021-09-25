
package net.mcreator.burger.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import net.mcreator.burger.item.ItemEnd;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class RecipeEndBodyRecipe extends ElementsBurger.ModElement {
	public RecipeEndBodyRecipe(ElementsBurger instance) {
		super(instance, 15);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(Items.DIAMOND_CHESTPLATE, (int) (1)), new ItemStack(ItemEnd.body, (int) (1)), 0F);
	}
}
