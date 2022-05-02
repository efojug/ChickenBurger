package net.mcreator.burger.procedure;

import net.minecraft.world.GameType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ProcedureSurvivalCommandExecuted extends ElementsBurger.ModElement {
	public ProcedureSurvivalCommandExecuted(ElementsBurger instance) {
		super(instance, 19);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SurvivalCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).setGameType(GameType.SURVIVAL);
	}
}
