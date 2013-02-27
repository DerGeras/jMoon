package geras.jmoon.items;

import geras.jmoon.settings.Settings;

import java.util.ArrayList;

import de.matthiasmann.twl.model.SimpleChangableListModel;

/**
 * Hold your items!
 * @author Geras
 *
 */
public class Inventory {
	
	private ArrayList<Item> content = new ArrayList<Item>();
	
	SimpleChangableListModel<String> inventoryModel;
	
	public Inventory(SimpleChangableListModel<String> inventoryModel){
		this.inventoryModel = inventoryModel;
	}
	
	/**
	 * Add the item to the inventory, trying to fill any items already existing,
	 * or simply adds the item to the inventory
	 * @param item - the item to add
	 * @return either the input item (if there is no such item) or the corresponding
	 * 	item in the inventory
	 */
	public void addItem(Item item){
		boolean removed = false;
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == item.name){
				item.setStackSize(invItem.addItems(item.getStackSize())); //fill existing Stacks
				if(inventoryModel != null){
					inventoryModel.insertElement(i, invItem.toString());
				}
				removed = true;
			}
		}
		if(item.getStackSize() > 0 && !removed){
			content.add(item); //add an additional stack, if it hasn't been touched yet (no duplicates)
			if(inventoryModel != null){
				inventoryModel.addElement(item.toString());
			}
		}
	}
	
	/**
	 * Add the item to the inventory, trying to fill any items already existing,
	 * or simply adds the item to the inventory
	 * 
	 * @return either the input item (if there is no such item) or the corresponding
	 * 	item in the inventory
	 */
	public void addItem(String itemName, int amount, int durability){
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
			Item item = ItemFactory.getItem(itemName, Settings.maxStackSize, amount, durability);
			content.add(item); //add an additional stack, if it hasn't been touched yet (no duplicates)
			if(inventoryModel != null){
				inventoryModel.addElement(item.toString());
			}
		}
		updateModel();
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
	 * update the inventoryModel
	 */
	public void updateModel(){
		if(inventoryModel != null){
			for(int i = 0; i < content.size(); i++){
				Item invItem = content.get(i);
				inventoryModel.setElement(i, invItem.toString());
			}
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
	
	public SimpleChangableListModel<String> getInventoryModel(){
		return inventoryModel;
	}
	
	
	
}
