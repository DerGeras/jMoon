package geras.jmoon.item;

import geras.jmoon.settings.Settings;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Hold your items!
 * @author Geras
 *
 */
public class Inventory {
	
	private ArrayList<Item> content = new ArrayList<Item>();
	
	private int money;
	
	public Inventory(){
		money = 0;
	}
	
	/**
	 * Add the item to the inventory, trying to fill any items already existing,
	 * or simply adds a new item to the inventory
	 * @param item - the item to add
	 */
	public void addItem(Item inputItem){
		addItem(inputItem.getName(), inputItem.getStackSize(), inputItem.getDurability());
	}
	
	/**
	 * add this specific item directly to the inventory
	 * @param inputItem - the item to add
	 */
	public void addStrictItem(Item inputItem){
		content.add(inputItem);
	}
	
	/**
	 * add an item with the specified name to the inventory, first trying to fill any existing stacks,
	 * or add a new item from the itemfactory
	 * @param itemName - name of the item (needs to be registered in the itemfactory)
	 * @param amount - amount to add
	 */
	public int addItem(String itemName, int amount){
		int tmpAmount = amount;
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == itemName){
				tmpAmount = invItem.addItems(tmpAmount); //fill existing Stacks
			}
		}
		while(tmpAmount > 0 && content.size() < Settings.maxInventorySize){
			Item item = ItemFactory.getItem(itemName, tmpAmount);
			content.add(item);//add an additional stack, if it hasn't been touched yet (no duplicates)
			tmpAmount = Math.max(0, tmpAmount - item.getMaxStackSize());
		}
		return tmpAmount;
	}
	
	/**
	 * add an item with the specified name to the inventory, first trying to fill any existing stacks,
	 * or add a new item from the itemfactory
	 * @param itemName - name of the item (needs to be registered in the itemfactory)
	 * @param durability - durability (for tools)
	 * @param amount - amount to add
	 */
	public int addItem(String itemName, int amount, int durability){
		int tmpAmount = amount;
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == itemName){
				tmpAmount = invItem.addItems(tmpAmount); //fill existing Stacks
			}
		}
		while(tmpAmount > 0 && content.size() < Settings.maxInventorySize){
			Item item = ItemFactory.getItem(itemName, tmpAmount, durability);
			content.add(item);//add an additional stack, if it hasn't been touched yet (no duplicates)
			tmpAmount = Math.max(0, tmpAmount - item.getMaxStackSize());
		}
		return tmpAmount;
	}
	
	/**
	 * remove a number of item from the inventory
	 * @param item - the item specifying the name and amount of items to remove
	 * @return - wheter the items could be removed (if not the item remainds unchanged)
	 */
	public boolean removeItem(Item item){
		Item currItem;
		for(int i = 0; i < content.size(); i++){
			currItem = content.get(i);
			if(currItem.getName() == item.getName()){
				return currItem.removeItems(item.getStackSize());
			}
		}
		clean();
		return false;
	}
	
	/**
	 * remove a number of item from the inventory
	 * @param itemName - name of the item
	 * @param amount - amount to remove
	 * @return whether the items could be removed
	 */
	public boolean removeItem(String itemName, int amount){
		Item currItem;
		for(int i = 0; i < content.size(); i++){
			currItem = content.get(i);
			if(currItem.getName() == itemName){
				return currItem.removeItems(amount);
			}
		}
		clean();
		return false;
	}

	/**
	 * get the item on the specified index
	 * @param index - index/location in the inventory
	 * @return the corresponding item or null if not existing
	 */
	public Item getItem(int index){
		return content.get(index);
	}
	
	/**
	 * get the item with the specified name (if it exists)
	 * @param name - name of the item to retrieve
	 * @return the item with the specified name or null
	 */
	public Item getItem(String name){
		clean();
		for(Item item : content){
			if(item.getName() == name){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return the size of the inventory (#Items)
	 */
	public int getSize(){
		clean();
		return content.size();
	}
	
	/**
	 * clear the inventory (irreversible btw)
	 */
	public void clear(){
		content.clear();
	}
	
	/**
	 * add money to the inventory
	 * @param amount
	 */
	public void addMoney(int amount){
		money += amount;
	}
	
	/**
	 * remove money from the inventory
	 * @param amount
	 */
	public void removeMoney(int amount){
		money -= amount;
	}
	
	/**
	 * remove all empty Itemstacks
	 */
	public void clean(){
		Iterator<Item> iter = content.iterator();
		Item currItem;
		while(iter.hasNext()){
			currItem = iter.next();
			if(currItem.getStackSize() == 0){
				content.remove(currItem);
				iter = content.iterator();
			}
		}
	}
	
	/**
	 * save to xml file
	 */
	public void saveToXML(BufferedWriter out){
		try {
			out.write("<inventory money=\"" + money + "\">");
			out.newLine();
			
			//write out items
			for(Item item : content){
				item.saveToXML(out);
				out.newLine();
			}
			
			out.write("</inventory>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public ArrayList<Item> getContent() {
		return content;
	}

	public void setContent(ArrayList<Item> content) {
		this.content = content;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
}
