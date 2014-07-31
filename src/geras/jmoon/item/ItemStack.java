package geras.jmoon.item;

import geras.jmoon.entity.LivingEntity;
import geras.jmoon.nbt.TagCompound;
import geras.jmoon.registry.GameRegistry;

public class ItemStack {
	
	private Item item;

	private int amount;
	
	private int durability;
	
	private TagCompound nbtTagCompount;

	public ItemStack(Item item, int amount, int durability){
		this(item.getID(), amount, durability);
	}
	
	public ItemStack(Item item, int amount){
		this(item, amount, 0);
	}
	
	public ItemStack(Item item){
		this(item, 1, 0);
	}
	
	public ItemStack(int id, int amount, int durability){
		this.item = GameRegistry.allItems[id];
		this.amount = amount;
		this.durability = durability;
	}
	
	public ItemStack(int id, int amount){
		this(id, amount, 0);
	}
	public ItemStack(int id){
		this(id, 1, 0);
	}
	
	public void consume(LivingEntity entity){
		getItem().consume(entity, this);
	}
	
	public int getItemID(){
		return item.getID();
	}
	
	public int getMaxStackSize(){
		return getItem().getMaxStackSize();
	}
	
	public int getMaxDurability(){
		return getItem().getMaxDurability();
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public Item getItem() {
		return item;
	}
	
	public int getSellingPrice(){
		return getItem().getSellingPrice(this);
	}
	
	public boolean hasTagCompound(){
		return nbtTagCompount != null;
	}

	public TagCompound getTagCompount() {
		return nbtTagCompount;
	}

	public void setTagCompount(TagCompound nbtTagCompount) {
		this.nbtTagCompount = nbtTagCompount;
	}
	
	
}
