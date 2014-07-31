package geras.jmoon.item;

import geras.jmoon.entity.LivingEntity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.world.Region;

import java.awt.Image;


public abstract class Item {
	
	
	protected int id;
	protected String name; //name of the item
	protected String displayName; //displayed name of the item
	protected int sellingPrice;
	
	protected int maxStackSize; 
	protected int maxDurability; 
	
	protected Image image;
	
	public Item(int id, String name, String displayName, int maxDurability, int maxStackSize, int sellingPrice){
		this.name = name;
		this.displayName = displayName;
		this.maxDurability = maxDurability;
		this.maxStackSize = maxStackSize;
		this.sellingPrice = sellingPrice;
	}
	
	public void use(PlayerEntity player, Region region, ItemStack stack, int posX, int posY){
		//default is nothing
	}
	
	/**
	 * register images at startup
	 */
	public void registerImages(){
		//TODO
	}
	
	/**
	 * try to consume this item (i.e. eating)
	 * @param Entity
	 * @param stack
	 */
	public void consume(LivingEntity Entity, ItemStack stack){
		//doing nothing is standart behaviour
	}
	
	/**
	 * get the image to be drawn, depending on the state of the stack
	 * containing the item
	 * @param stack
	 * @return
	 */
	public Image getImage(ItemStack stack){
		return image;
	}
	
	
	public void setSellingPrice(int newPrice){
		sellingPrice = newPrice;
	}
	
	public int getSellingPrice(ItemStack stack){
		return sellingPrice;
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
