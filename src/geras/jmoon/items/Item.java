package geras.jmoon.items;

import geras.jmoon.entites.Entity;
import geras.jmoon.entites.LivingEntity;

import java.io.BufferedWriter;
import java.io.IOException;

import org.xml.sax.Attributes;

public abstract class Item {
	
	//only used for tools
	protected int maxDurability;
	protected int durability; //rest durability
	
	
	protected String name; //name of the item
	
	protected int maxStackSize; //maximum stacksize
	protected int stackSize; //current stackSize
	
	protected int sellingPrice;

	/**
	 * 
	 * @param name - Name of the item
	 * @param maxStackSize - maximum stack size
	 * @param stackSize - stack size
	 */
	public Item(String name, int maxStackSize, int stackSize, int sellingPrice){
		this.name = name;
		this.maxStackSize = maxStackSize;
		this.stackSize = Math.min(maxStackSize, stackSize);
		this.setSellingPrice(sellingPrice);
	}
	
	/**
	 * 
	 * @param amount - amount of items to add to the stack
	 * @return any rest items that couldn't be stacked
	 */
	public int addItems(int amount){
		int newSize = stackSize + amount;
		stackSize = Math.min(maxStackSize, newSize);
		return Math.max(0, newSize - maxStackSize);
	}
	
	/**
	 * remove amount items from the stack
	 * @param amount amount of items to remove
	 * @return true if it could remove amount many items, false if not
	 */
	public boolean removeItems(int amount){
		if(amount > stackSize){
			return false;
		}
		stackSize -= amount;
		return true;
	}
	
	/**
	 * this function sounds stupid, but is way better than some crappy "instanceof" calls
	 * @return
	 */
	public boolean isUsable(){
		return false;
	}
	
	@Override
	public String toString(){
		return name + " N:" + stackSize;
	}
	
	/**
	 * Use this item from the inventory
	 */
	public void useInventory(Entity entity){
		//Standart: do nothing
	}
	
	/**
	 * eat this item, if you can
	 */
	public void eat(LivingEntity entity){
		//standart: do nothing
	}
	
	/**
	 * save to xml file
	 */
	public void saveToXML(BufferedWriter out){
		try {
			out.append("<item name=\"" + name + "\" maxStackSize=\"" + maxStackSize + "\" stackSize=\"" + stackSize + "\">");
			out.append("</item>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read attributes from given XML attributes
	 * @param attributes
	 */
	public void readFromAttributes(Attributes attributes) {
		String maxStackSizeS = attributes.getValue("maxStackSize");
		String stackSizeS = attributes.getValue("stackSize");
		
		if(maxStackSizeS != null){
			maxStackSize = Integer.parseInt(maxStackSizeS);
		}
		if(stackSizeS != null){
			stackSize = Integer.parseInt(stackSizeS);
		}
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	
	/**
	 * 
	 * @return the name of this item
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return maximum stack size
	 */
	public int getMaxStackSize(){
		return maxStackSize;
	}
	
	/**
	 * 
	 * @return current stack size
	 */
	public int getStackSize(){
		return stackSize;
	}
	
	/**
	 * 
	 * @param size - new stack size
	 * @return the rest (size - maxStackSize) if size > maxStackSize
	 */
	public int setStackSize(int size){
		this.stackSize = Math.min(size, maxStackSize);
		return Math.max(0, size - maxStackSize);
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
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
