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
	public static Item getItem(String name, int stackSize){
		switch(name){
		case "Bucket": return getBucketItem(stackSize);
		case "Fence": return getFenceItem(stackSize);
		case "Hoe" : return getHoeItem(stackSize);
		case "Milk Bucket": return getMilkBucketItem(stackSize);
		case "Seeds": return getSeedItem(stackSize);
		case "Shovel": return getShovelItem(stackSize);
		case "Sickel": return getSickelItem(stackSize);
		case "Water Bucket": return getWaterBucketItem(stackSize);
		case "Watering Can": return getWateringCanItem(stackSize);
		case "Wheat": return getWheatItem(stackSize);
		default: return null;
		}
	}
	
	/**
	 * return a BucketItem
	 * @param stackSize - size of the stack
	 * @return the new bucket item
	 */
	public static BucketItem getBucketItem(int stackSize){
		return new BucketItem(stackSize);
	}
	
	/**
	 * return a FenceItem
	 * @param stackSize - size of the stack
	 * @returnthe the new fence item
	 */
	public static FenceItem getFenceItem(int stackSize){
		return new FenceItem(stackSize);
	}
	
	/**
	 * return a HoeItem
	 * @param stackSize - size of the stack
	 * @return the new hoe item
	 */
	public static HoeItem getHoeItem(int stackSize){
		return new HoeItem(stackSize);
	}
	
	/**
	 * return a MilkBucketItem
	 * @param stackSize - size of the stack
	 * @return the new milk bucket item
	 */
	public static MilkBucketItem getMilkBucketItem(int stackSize){
		return new MilkBucketItem(stackSize);
	}
	
	/**
	 * return a ShovelItem
	 * @param stackSize - size of the stack
	 * @return the new shovel item
	 */
	public static ShovelItem getShovelItem(int stackSize){
		return new ShovelItem(stackSize);
	}
	
	/**
	 * return a SickelItem
	 * @param stackSize - size of the stack
	 * @return sickel item
	 */
	public static SickelItem getSickelItem(int stackSize){
		return new SickelItem(stackSize);
	}
	
	/**
	 * get a SeedItem
	 * @param stackSize - size of the stack
	 * @return the new Seed Item
	 */
	public static SeedItem getSeedItem(int stackSize){
		return new SeedItem(stackSize);
	}
	
	/**
	 * return a WaterBucketItem
	 * @param stackSize - size of the stack
	 * @return the new water bucket item
	 */
	public static WaterBucketItem getWaterBucketItem(int stackSize){
		return new WaterBucketItem(stackSize);
	}
	
	/**
	 * return a WateringCanItem
	 * @param stackSize - size of the stack
	 * @return the new watering can item
	 */
	public static WateringCanItem getWateringCanItem(int stackSize){
		return new WateringCanItem(stackSize);
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
