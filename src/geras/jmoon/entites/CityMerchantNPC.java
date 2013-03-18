package geras.jmoon.entites;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.Item;
import geras.jmoon.world.Map;

public class CityMerchantNPC extends NPCEntity implements Merchant {

	public CityMerchantNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		width = 32;
		height = 32;
		//TODO own Image for this one
		try{
			this.entityImg = new Image("Sprites/Entities/Hero.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}

	@Override
	public void sellTo(PlayerEntity player, Item item, int amount) {
		if(item.getStackSize() >= amount){
			int rest = inventory.addItem(item.getName(), amount);
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			player.getInventory().setMoney(player.getInventory().getMoney() + soldAmount * (int)Math.ceil(getSellSale() * item.getSellingPrice()));
		}
	}

	@Override
	public void buyFrom(PlayerEntity player, Item item, int amount) {
		if(item.getStackSize() >= amount){
			int rest = player.getInventory().addItem(item.getName(), amount);
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			player.getInventory().setMoney(player.getInventory().getMoney() - soldAmount * (int)Math.ceil(getSellSale() * item.getSellingPrice()));
		}
	}

	@Override
	public float getSellSale() {
		//some fixed value for testing
		return 1.0f;
	}

	@Override
	public void update(int timesincelastframe, Map map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void interact(PlayerEntity player, Map map, Game game, WorldGameState state) {
		state.startTrade(this);
		
	}

	@Override
	public float getBuySale() {
		return 1.0f;
	}

}
