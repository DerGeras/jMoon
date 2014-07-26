package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.world.World;


public abstract class Item {
	
	protected int id;
	protected String name; //name of the item
	protected String displayName; //displayed name of the item
	
	protected int maxStackSize; 
	protected int maxDurability; 
	
	public Item(int id, String name, String displayName, int maxDurability, int maxStackSize){
		this.name = name;
		this.displayName = displayName;
		this.maxDurability = maxDurability;
		this.maxStackSize = maxStackSize;
	}
	
	public void useInWorld(PlayerEntity player, World world, int posX, int posY){
		//default is nothing
	}
	
	
	/**
	 * 
	 * GETTERS
	 * 
	 */
	
	public int getMaxDurability(){
		return maxDurability;
	}



	public String getName() {
		return name;
	}



	public String getDisplayName() {
		return displayName;
	}



	public int getMaxStackSize() {
		return maxStackSize;
	}
	
	public int getID(){
		return id;
	}
	
	
	
}
