
package net.mcreator.burger.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.mcreator.burger.item.ItemChickenBurger;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class TabBurgerCteativeTab extends ElementsBurger.ModElement {
	public TabBurgerCteativeTab(ElementsBurger instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabburgercteativetab") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ItemChickenBurger.block, 1);
			}
			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static CreativeTabs tab;
}
