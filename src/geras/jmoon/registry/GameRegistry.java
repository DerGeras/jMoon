package geras.jmoon.registry;

import geras.jmoon.entity.Entity;
import geras.jmoon.item.Item;
import geras.jmoon.tileentity.TileEntity;

import java.util.ArrayList;

public class GameRegistry {
	
	private static int nextFreeItemID = 0;
	public static Item[] allItems = new Item[1024];
	
	public static ArrayList<Class<? extends Entity>> entityClasses
									= new ArrayList<Class<? extends Entity>>(128);
	
	public static ArrayList<Class<? extends TileEntity>> tileEntityClasses
									= new ArrayList<Class<? extends TileEntity>>(128);
	
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
	
	public static void registerEntityClass(int id, Class<? extends Entity> c){
		if(entityClasses.get(id) != null){
			System.err.println("Trying to register new entity class" + c.getSimpleName()
					+ "with id" + id + "already taken by"
					+ entityClasses.get(id).getSimpleName());
			return;
		}
		entityClasses.set(id, c);
	}
	
	public static void registerTileEntityClass(int id, Class<? extends TileEntity> c){
		if(entityClasses.get(id) != null){
			System.err.println("Trying to register new tile-entity class" + c.getSimpleName()
					+ "with id" + id + "already taken by"
					+ entityClasses.get(id).getSimpleName());
			return;
		}
		tileEntityClasses.set(id, c);
	}

}
