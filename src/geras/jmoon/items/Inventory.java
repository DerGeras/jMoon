package geras.jmoon.items;

import java.util.ArrayList;

/**
 * Hold your items!
 * @author Geras
 *
 */
public class Inventory {
	
	private ArrayList<Item> content = new ArrayList<Item>();
	
	public Inventory(){
	}
	
	/**
	 * Add the item to the inventory, trying to fill any items already existing,
	 * or simply adds the item to the inventory
	 * @param item - the item to add
	 */
	public void addItem(Item inputItem){
		boolean removed = false;
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == inputItem.name){
				inputItem.setStackSize(invItem.addItems(inputItem.getStackSize())); //fill existing Stacks
				removed = true;
			}
		}
		if(inputItem.getStackSize() > 0 && !removed){
			Item item = ItemFactory.getItem(inputItem.getName(), inputItem.getStackSize());
			content.add(item); //add an additional stack, if it hasn't been touched yet (no duplicates)
		}
	}
	
	/**
	 * add an item with the specified name to the inventory, first trying to fill any existing stacks,
	 * or add a new item from the itemfactory
	 * @param itemName - name of the item (needs to be registered in the itemfactory)
	 * @param amount - amount to add
	 */
	public void addItem(String itemName, int amount){
		boolean removed = false;
		int tmpAmount = amount;
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == itemName){
				tmpAmount = invItem.addItems(tmpAmount); //fill existing Stacks
				removed = true;
			}
		}
		if(tmpAmount > 0 && !removed){
			Item item = ItemFactory.getItem(itemName, amount);
			content.add(item); //add an additional stack, if it hasn't been touched yet (no duplicates)
		}
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
		return content.size();
	}
	
	/**
	 * clear the inventory (irreversible btw)
	 */
	public void clear(){
		content.clear();
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
	
	
	
}
