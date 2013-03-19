package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.world.Map;

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
	public abstract void useWorld(int x, int y, Map map, PlayerEntity player);
	
	public boolean isUsable(){
		return true;
	}
	
}
