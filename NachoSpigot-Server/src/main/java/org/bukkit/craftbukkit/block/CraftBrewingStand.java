package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftInventoryBrewer;
import org.bukkit.inventory.BrewerInventory;

import net.minecraft.server.TileEntityBrewingStand;

public class CraftBrewingStand extends CraftBlockState implements BrewingStand
{
	private final TileEntityBrewingStand brewingStand;

	public CraftBrewingStand(Block block)
	{
		super(block);

		brewingStand = (TileEntityBrewingStand) ((CraftWorld) block.getWorld()).getTileEntityAt(getX(), getY(), getZ());
	}

	public CraftBrewingStand(final Material material, final TileEntityBrewingStand te)
	{
		super(material);
		brewingStand = te;
	}

	@Override
	public BrewerInventory getInventory()
	{
		return new CraftInventoryBrewer(brewingStand);
	}

	@Override
	public boolean update(boolean force, boolean applyPhysics)
	{
		boolean result = super.update(force, applyPhysics);

		if (result)
		{
			brewingStand.update();
		}

		return result;
	}

	@Override
	public int getBrewingTime()
	{
		return brewingStand.brewTime;
	}

	@Override
	public void setBrewingTime(int brewTime)
	{
		brewingStand.brewTime = brewTime;
	}

	@Override
	public TileEntityBrewingStand getTileEntity()
	{
		return brewingStand;
	}
}
