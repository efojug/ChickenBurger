
package net.mcreator.burger.item;

import net.mcreator.burger.Burger;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import net.minecraft.item.Item;
import net.minecraft.item.EnumAction;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.mcreator.burger.creativetab.TabBurgerCteativeTab;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ItemCookedegg extends ElementsBurger.ModElement {
	@GameRegistry.ObjectHolder("burger:cookedegg")
	public static final Item block = null;
	public ItemCookedegg(ElementsBurger instance) {
		super(instance, 59);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemFoodCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("burger:cookedegg", "inventory"));
	}
	public static class ItemFoodCustom extends ItemFood {
		public ItemFoodCustom() {
			super(3, 1f, false);
			setTranslationKey(Burger.MODID + ".cookedegg");
			//setUnlocalizedName("cookedegg");
			setRegistryName("cookedegg");
			setCreativeTab(TabBurgerCteativeTab.tab);
			setMaxStackSize(64);
		}

		@Override
		public int getMaxItemUseDuration(ItemStack stack) {
			return 24;
		}

		@Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack) {
			return EnumAction.EAT;
		}
	}
}
