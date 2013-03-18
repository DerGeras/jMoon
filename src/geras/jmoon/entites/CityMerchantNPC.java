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
	public void sellTo(PlayerEntity player, Item item) {
		int oldSize = item.getStackSize();
		this.inventory.addItem(item);
		int soldAmount = oldSize - item.getStackSize();
		player.getInventory().setMoney((int)Math.ceil(player.getInventory().getMoney() + getSale()*soldAmount*item.getSellingPrice()));
	}

	@Override
	public void buyFrom(PlayerEntity player, Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getSale() {
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

}
