package org.bukkit.craftbukkit.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hanging;

import net.minecraft.server.EntityHanging;
import net.minecraft.server.EnumDirection;

public class CraftHanging extends CraftEntity implements Hanging
{
	public CraftHanging(CraftServer server, EntityHanging entity)
	{
		super(server, entity);
	}

	@Override
	public BlockFace getAttachedFace()
	{
		return getFacing().getOppositeFace();
	}

	@Override
	public void setFacingDirection(BlockFace face)
	{
		setFacingDirection(face, false);
	}

	@Override
	public boolean setFacingDirection(BlockFace face, boolean force)
	{
		EntityHanging hanging = getHandle();
		EnumDirection dir = hanging.direction;
		switch (face)
		{
		case SOUTH:
		default:
			getHandle().setDirection(EnumDirection.SOUTH);
			break;
		case WEST:
			getHandle().setDirection(EnumDirection.WEST);
			break;
		case NORTH:
			getHandle().setDirection(EnumDirection.NORTH);
			break;
		case EAST:
			getHandle().setDirection(EnumDirection.EAST);
			break;
		}
		if (!force && !hanging.survives())
		{
			// Revert since it doesn't fit
			hanging.setDirection(dir);
			return false;
		}
		return true;
	}

	@Override
	public BlockFace getFacing()
	{
		EnumDirection direction = this.getHandle().direction;
		if (direction == null)
			return BlockFace.SELF;
		switch (direction)
		{
		case SOUTH:
		default:
			return BlockFace.SOUTH;
		case WEST:
			return BlockFace.WEST;
		case NORTH:
			return BlockFace.NORTH;
		case EAST:
			return BlockFace.EAST;
		}
	}

	@Override
	public EntityHanging getHandle()
	{
		return (EntityHanging) entity;
	}

	@Override
	public String toString()
	{
		return "CraftHanging";
	}

	@Override
	public EntityType getType()
	{
		return EntityType.UNKNOWN;
	}
}
