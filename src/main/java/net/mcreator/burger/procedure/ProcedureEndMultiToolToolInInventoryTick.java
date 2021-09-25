package net.mcreator.burger.procedure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.PotionEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.mcreator.burger.potion.PotionNice;
import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ProcedureEndMultiToolToolInInventoryTick extends ElementsBurger.ModElement {
	public ProcedureEndMultiToolToolInInventoryTick(ElementsBurger instance) {
		super(instance, 39);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure EndMultiToolToolInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionNice.potion, (int) 600, (int) 0, (false), (false)));
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).capabilities.allowFlying = (true);
			((EntityPlayer) entity).sendPlayerAbilities();
		}
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).getFoodStats().setFoodLevel((int) 20);
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).setHealth((float) 20);
		entity.extinguish();
	}
}
