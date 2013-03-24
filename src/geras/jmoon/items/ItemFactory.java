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
	 * @param value - stackSize or durability that the item should have
	 * @return the specified item
	 */
	public static Item getItem(String name, int value){
		switch(name){
		case "Bucket": return getBucketItem(value);
		case "Dirt": return getDirtItem(value);
		case "Fence": return getFenceItem(value);
		case "Gravel": return getGravelItem(value);
		case "Hoe" : return getHoeItem(value);
		case "Milk Bucket": return getMilkBucketItem(value);
		case "Potato": return getPotatoItem(value);
		case "Seeds": return getSeedItem(value);
		case "Shovel": return getShovelItem(value);
		case "Sickel": return getSickelItem(value);
		case "Water Bucket": return getWaterBucketItem(value);
		case "Watering Can": return getWateringCanItem(value);
		case "Wheat": return getWheatItem(value);
		default: return null;
		}
	}
	
	/**
	 * return an item with specified paramets
	 * @param name - name of the item
	 * @param value - stackSize or durability that the item should have
	 * @return the specified item
	 */
	public static Item getItem(String name, int value, int durability){
		Item item = null;
		switch(name){
		case "Bucket": item = getBucketItem(value); break;
		case "Dirt": item = getDirtItem(value); break;
		case "Fence": item = getFenceItem(value); break;
		case "Gravel": item = getGravelItem(value); break;
		case "Hoe" : item = getHoeItem(value); break;
		case "Milk Bucket": item = getMilkBucketItem(value); break;
		case "Potato": item = getPotatoItem(value);break;
		case "Seeds": item = getSeedItem(value); break;
		case "Shovel": item = getShovelItem(value); break;
		case "Sickel": item = getSickelItem(value); break;
		case "Water Bucket": item = getWaterBucketItem(value); break;
		case "Watering Can": item = getWateringCanItem(value); break;
		case "Wheat": item = getWheatItem(value); break;
		default: item = null; break;
		}
		item.setDurability(durability);
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
