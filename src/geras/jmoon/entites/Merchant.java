package geras.jmoon.entites;

import geras.jmoon.items.Inventory;
import geras.jmoon.items.Item;

public interface Merchant {
	
	/**
	 * get the inventory of this Entity
	 * @return - the inventory
	 */
	public Inventory getInventory();
	
	/**
	 * try to sell the item to the Entity
	 * @param item - the item to sell
	 */
	public void sellTo(PlayerEntity player, Item item);
	
	/**
	 * try to buy the given item from the Entity
	 * @param item - the item to buy
	 */
	public void buyFrom(PlayerEntity player, Item item);
	
	/**
	 * get the sale in percent (for example 70% of the selling price)
	 * @return 
	 */
	public float getSale();
}
