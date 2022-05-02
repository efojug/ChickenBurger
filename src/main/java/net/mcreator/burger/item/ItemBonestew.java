
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
public class ItemBonestew extends ElementsBurger.ModElement {
	@GameRegistry.ObjectHolder("burger:bonestew")
	public static final Item block = null;
	public ItemBonestew(ElementsBurger instance) {
		super(instance, 58);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemFoodCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("burger:bonestew", "inventory"));
	}
	public static class ItemFoodCustom extends ItemFood {
		public ItemFoodCustom() {
			super(12, 3f, false);
			setTranslationKey(Burger.MODID + ".bonestew");
			//setUnlocalizedName("bonestew");
			setRegistryName("bonestew");
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
