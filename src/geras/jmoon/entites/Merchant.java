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
	 * @param amount - amount to sell
	 */
	public void sellTo(PlayerEntity player, Item item, int amount);
	
	/**
	 * try to buy the given item from the Entity
	 * @param item - the item to buy
	 * @param amount - amount to buy
	 */
	public void buyFrom(PlayerEntity player, Item item, int amount);
	
	/**
	 * get the sell sale in percent (for example 70% of the selling price)
	 * @return 
	 */
	public float getSellSale();
	
	/**
	 * get the buy sale in percent  (for example 70% of the selling price)
	 * @return
	 */
	public float getBuySale();
	
	/**
	 * can you buy from this one?
	 * @return
	 */
	public boolean canBuyFrom();
	
	/**
	 * can you sell to this one?
	 * @return
	 */
	public boolean canSellTo();
}
