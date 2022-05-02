
package net.mcreator.burger.item;

import net.mcreator.burger.Burger;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;

import net.mcreator.burger.creativetab.TabBurgerCteativeTab;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ItemParcel extends ElementsBurger.ModElement {
	@GameRegistry.ObjectHolder("burger:parcel")
	public static final Item block = null;
	public ItemParcel(ElementsBurger instance) {
		super(instance, 63);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("burger:parcel", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 1;
			setTranslationKey(Burger.MODID + ".parcel");
			//setUnlocalizedName("parcel");
			setRegistryName("parcel");
			setCreativeTab(TabBurgerCteativeTab.tab);
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}
	}
}
