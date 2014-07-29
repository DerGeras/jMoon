package geras.jmoon.registry;

import geras.jmoon.item.Item;

public class GameRegistry {
	
	private static int nextFreeItemID = 0;
	public static Item[] allItems = new Item[1024];
	
	public static void registerItem(Item item){
		if(allItems[item.getID()] != null){
			System.err.println("Trying to register new item" + item.getName()
					+ "with id" + item.getID() + "already taken by"
					+ allItems[item.getID()].getName());
			return;
		}
		allItems[item.getID()] = item;
	}
	
	public static int getNewItemID(){
		while(allItems[nextFreeItemID] != null){
			nextFreeItemID++;
		}
		return nextFreeItemID;
	}

}
