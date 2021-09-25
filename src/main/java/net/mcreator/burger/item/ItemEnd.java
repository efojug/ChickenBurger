
package net.mcreator.burger.item;

import net.mcreator.burger.Burger;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.mcreator.burger.procedure.ProcedureEndArmorTickEvent;
import net.mcreator.burger.creativetab.TabBurgerCteativeTab;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ItemEnd extends ElementsBurger.ModElement {
	@GameRegistry.ObjectHolder("burger:endhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("burger:endbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("burger:endlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("burger:endboots")
	public static final Item boots = null;
	public ItemEnd(ElementsBurger instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("END", "burger:nd", 1024, new int[]{1024, 1024, 1024, 1024}, 0,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.player.death")), 5f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				super.onArmorTick(world, entity, itemstack);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureEndArmorTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setTranslationKey(Burger.MODID + ".endhelmet").setRegistryName("endhelmet").setCreativeTab(TabBurgerCteativeTab.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureEndArmorTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setTranslationKey(Burger.MODID + ".endbody").setRegistryName("endbody").setCreativeTab(TabBurgerCteativeTab.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureEndArmorTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setTranslationKey(Burger.MODID + ".endlegs").setRegistryName("endlegs").setCreativeTab(TabBurgerCteativeTab.tab));
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureEndArmorTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setTranslationKey(Burger.MODID + ".endboots").setRegistryName("endboots").setCreativeTab(TabBurgerCteativeTab.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("burger:endhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("burger:endbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("burger:endlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("burger:endboots", "inventory"));
	}
}
