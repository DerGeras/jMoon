package geras.jmoon.entity;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.item.Inventory;
import geras.jmoon.item.Item;
import geras.jmoon.item.ItemStack;
import geras.jmoon.world.Region;
import geras.jmoon.world.World;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;


public class PlayerEntity extends LivingEntity implements Merchant{
	
	private ItemStack currentItem;
	
	private Inventory inventory;

	/**
	 * Basic constructor, set hunger to 0 and set the image for the rendering
	 */
	public PlayerEntity(int id, Region region){
		super(id, region);
		this.hunger = 0;
		//TODO
	}
	
	@Override
	public void update(int timesincelastframe){
		move(timesincelastframe);
	}
	
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY) {
		super.draw(g, mapTopX,mapTopY);
	}
	
	/**
	 * Use the currently selected tool
	 * @param map - current Map
	 */
	public void useItem(World map, int x, int y){
		//TODO
	}
	
	public void setCurrentItem(ItemStack item){
		//TODO
	}
	
	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		//do nothing
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		//do nothing
		
	}

	@Override
	public float getSellSale() {
		return 1;
	}

	@Override
	public float getBuySale() {
		return 1;
	}

	@Override
	public boolean canBuyFrom() {
		return false;
	}

	@Override
	public boolean canSellTo() {
		return false;
	}

	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return the currently selected tool
	 */
	public ItemStack getCurrentItem(){
		return currentItem;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	@Override
	public void interact(PlayerEntity player, Game game, WorldGameState state) {
		// TODO Auto-generated method stub
		
	}



	
	
}
