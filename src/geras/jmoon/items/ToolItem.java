package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.world.Map;

public abstract class ToolItem extends UsableItem {

	protected int maxDurability;
	protected int durability; //rest durability
	
	public ToolItem(String name, int maxDurability, int durability, int sellingPrice) {
		super(name, 1, 1, sellingPrice);
		this.maxDurability = maxDurability;
		this.durability = durability;
	}
	
	@Override
	public int getSellingPrice(){
		return  (int)((float)durability / (float)maxDurability * (float)sellingPrice);
	}

	@Override
	public abstract void useWorld(int x, int y, Map map, PlayerEntity player);
	
	@Override
	public String toString(){
		return name + " " + durability;
	}
	
	/**
	 * 
	 * @return the rest durability of this item
	 */
	public int getDurability(){
		return durability;
	}
	
	/**
	 * 
	 * @param durability - new durability value
	 */
	public void setDurability(int durability){
		this.durability = durability;
	}

}
