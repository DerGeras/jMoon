package geras.jmoon.entites;

import geras.jmoon.items.Inventory;
import geras.jmoon.items.Item;

public interface Trading {
	
	/**
	 * get the inventory of this Entity
	 * @return - the inventory
	 */
	public Inventory getInventory();
	
	/**
	 * try to sell the item to the Entity
	 * @param item - the item to sell
	 */
	public void sellTo(Item item);
}
