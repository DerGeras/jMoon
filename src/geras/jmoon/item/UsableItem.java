package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.world.World;

public abstract class UsableItem extends Item {
	
	
	public UsableItem(String name, int maxStackSize, int stackSize, int sellingPrice) {
		super(name, maxStackSize, stackSize, sellingPrice);
	}

	/**
	 * Use this item
	 * @param x - the x coordinate to use on
	 * @param y - the y coordinate to use on
	 * @param map - the current map
	 */
	public abstract void useWorld(int x, int y, World map, PlayerEntity player);
	
	public boolean isUsable(){
		return true;
	}
	
}
