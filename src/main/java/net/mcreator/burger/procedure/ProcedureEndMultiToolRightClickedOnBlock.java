package net.mcreator.burger.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import net.mcreator.burger.ElementsBurger;

@ElementsBurger.ModElement.Tag
public class ProcedureEndMultiToolRightClickedOnBlock extends ElementsBurger.ModElement {
	public ProcedureEndMultiToolRightClickedOnBlock(ElementsBurger instance) {
		super(instance, 47);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure EndMultiToolRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure EndMultiToolRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityPlayer) {
			ItemStack _setstack = (new ItemStack(
					(world.getBlockState(new BlockPos(
							(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
									entity.getPositionEyes(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
											entity.getLook(1f).z * 5),
									false, false, true).getBlockPos().getX()),
							(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
									entity.getPositionEyes(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
											entity.getLook(1f).z * 5),
									false, false, true).getBlockPos().getY()),
							(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f), entity.getPositionEyes(1f)
									.add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5), false, false, true)
									.getBlockPos().getZ())))).getBlock()));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
		}
		world.destroyBlock(
				new BlockPos(
						(int) (entity.world
								.rayTraceBlocks(entity.getPositionEyes(1f),
										entity.getPositionEyes(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
												entity.getLook(1f).z * 5),
										false, false, true)
								.getBlockPos().getX()),
						(int) (entity.world
								.rayTraceBlocks(entity.getPositionEyes(1f),
										entity.getPositionEyes(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
												entity.getLook(1f).z * 5),
										false, false, true)
								.getBlockPos().getY()),
						(int) (entity.world.rayTraceBlocks(entity.getPositionEyes(1f),
								entity.getPositionEyes(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
								false, false, true).getBlockPos().getZ())),
				false);
	}
}
