package geras.jmoon.items;

/**
 * I tried to avoid it, but here it is
 * @author Geras
 *
 */
public class ItemFactory {
	
	/**
	 * return an item with specified paramets
	 * @param name - name of the item
	 * @param maxStackSize - maximum stack size
	 * @param stackSize - stackSize that the item should have
	 * @param durability - durability that the item should have
	 * @return the specified item
	 */
	public static Item getItem(String name, int maxStackSize, int stackSize, int durability){
		switch(name){
		case "Seeds": return getSeedItem(stackSize);
		case "Wheat": return getWheatItem(stackSize);
		default: return null;
		}
	}
	
	/**
	 * get a SeedItem
	 * @param stackSize - size of the stack
	 * @return the Seed Item
	 */
	public static SeedItem getSeedItem(int stackSize){
		return new SeedItem(stackSize);
	}
	
	/**
	 * get a WheatItem
	 * @param stackSize - size of the stack
	 * @return the wheat item
	 */
	public static WheatItem getWheatItem(int stackSize){
		return new WheatItem(stackSize);
	}
	
}
