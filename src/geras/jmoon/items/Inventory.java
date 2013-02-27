package geras.jmoon.items;

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
		for(int i = 0; i < content.size(); i++){
			Item invItem = content.get(i);
			if(invItem.name == item.name){
				item.setStackSize(invItem.addItems(item.getStackSize())); //fill existing Stacks
				if(inventoryModel != null){
					inventoryModel.insertElement(i, invItem.toString());
				}
			}
		}
		if(item.getStackSize() > 0){
			content.add(item); //add an additional stack
			if(inventoryModel != null){
				inventoryModel.addElement(item.toString());
			}
		}
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
