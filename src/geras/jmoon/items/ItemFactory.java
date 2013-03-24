package geras.jmoon.items;

import geras.jmoon.settings.Settings;

/**
 * I tried to avoid it, but here it is
 * @author Geras
 *
 */
public class ItemFactory {
	
	/**
	 * return an item with specified paramets
	 * @param name - name of the item
	 * @param value - stackSize or durability that the item should have
	 * @return the specified item
	 */
	public static Item getItem(String name, int stackSize){
		return getItem(name, stackSize, Settings.maxDurability);
	}
	
	/**
	 * return an item with specified paramets
	 * @param name - name of the item
	 * @param stackSize - stackSize or durability that the item should have
	 * @return the specified item
	 */
	public static Item getItem(String name, int stackSize, int durability){
		Item item = null;
		switch(name){
		case "Bucket": item = getBucketItem(stackSize); break;
		case "Dirt": item = getDirtItem(stackSize); break;
		case "Fence": item = getFenceItem(stackSize); break;
		case "Gravel": item = getGravelItem(stackSize); break;
		case "Hoe" : item = getHoeItem(durability); break;
		case "Milk Bucket": item = getMilkBucketItem(stackSize); break;
		case "Potato": item = getPotatoItem(stackSize);break;
		case "Seeds": item = getSeedItem(stackSize); break;
		case "Shovel": item = getShovelItem(durability); break;
		case "Sickel": item = getSickelItem(durability); break;
		case "Water Bucket": item = getWaterBucketItem(stackSize); break;
		case "Watering Can": item = getWateringCanItem(durability); break;
		case "Wheat": item = getWheatItem(stackSize); break;
		default: item = null; break;
		}
		return item;
	}
	
	/**
	 * return a BucketItem
	 * @param stackSize - size of the stack
	 * @return the new bucket item
	 */
	public static BucketItem getBucketItem(int stackSize){
		return new BucketItem(stackSize);
	}
	
	public static DirtItem getDirtItem(int stackSize){
		return new DirtItem(stackSize);
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
	 * return a GravelItem
	 * @param stackSize - size of the stack
	 * @return the new gravel item
	 */
	public static GravelItem getGravelItem(int stackSize){
		return new GravelItem(stackSize);
	}
	
	/**
	 * return a HoeItem
	 * @param durability - size of the stack
	 * @return the new hoe item
	 */
	public static HoeItem getHoeItem(int durability){
		return new HoeItem(durability);
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
	 * return a PotatoItem
	 * @param stackSize - size of the stack
	 * @return the new potato item
	 */
	public static PotatoItem getPotatoItem(int stackSize){
		return new PotatoItem(stackSize);
	}
	
	/**
	 * return a ShovelItem
	 * @param durability - size of the stack
	 * @return the new shovel item
	 */
	public static ShovelItem getShovelItem(int durability){
		return new ShovelItem(durability);
	}
	
	/**
	 * return a SickelItem
	 * @param durability - size of the stack
	 * @return sickel item
	 */
	public static SickelItem getSickelItem(int durability){
		return new SickelItem(durability);
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
	 * @param durability - size of the stack
	 * @return the new watering can item
	 */
	public static WateringCanItem getWateringCanItem(int durability){
		return new WateringCanItem(durability);
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
