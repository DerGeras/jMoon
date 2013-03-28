package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.Item;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;

public class CityMerchantNPC extends NPCEntity implements Merchant {

	public CityMerchantNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		width = 32;
		height = 32;
		
		setEntityImg("Sprites/Entities/CityMerchant.png");
		
		//fill inventory
		inventory.addItem("Sickel", 1);
		inventory.addItem("Shovel", 1);
		inventory.addItem("Water Bucket", 1);
		inventory.addItem("Hoe", 1);
		inventory.addItem("Fence", 512);
		inventory.addItem("Watering Can", 1);
		
	}

	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		if(item.getStackSize() >= amount){
			int rest = inventory.addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			merchant.getInventory().setMoney(merchant.getInventory().getMoney() + soldAmount * (int)Math.ceil(getSellSale() * item.getSellingPrice()));
		}
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		if(item.getStackSize() >= amount && merchant.getInventory().getMoney() >= (int)Math.ceil(item.getSellingPrice() * getBuySale()) * amount){
			int rest = merchant.getInventory().addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			merchant.getInventory().setMoney(merchant.getInventory().getMoney() - soldAmount * (int)Math.ceil(getBuySale() * item.getSellingPrice()));
		}
	}

	@Override
	public float getSellSale() {
		//some fixed value for testing
		return 0.7f;
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
		return 1.5f;
	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"CityMerchantNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\">");
			out.flush();
			out.newLine();
			
			//output the inventory
			inventory.saveToXML(out);
			
			out.newLine();
			out.write("</entity>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public boolean canBuyFrom() {
		return true;
	}

	@Override
	public boolean canSellTo() {
		return true;
	}

}
