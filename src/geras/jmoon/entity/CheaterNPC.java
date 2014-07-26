package geras.jmoon.entity;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.item.Item;
import geras.jmoon.world.World;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;

public class CheaterNPC extends NPCEntity implements Merchant{

	public CheaterNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		setEntityImg("Sprites/Entities/Cheater.png");
		//fill inventory
//		inventory.addItem("Sickel", 1);
//		inventory.addItem("Shovel", 1);
//		inventory.addItem("Water Bucket", 1);
//		inventory.addItem("Hoe", 1);
//		inventory.addItem("Fence", 1);
//		inventory.addItem("Watering Can", 1);
	}
	
	public void interact(PlayerEntity player, World map, Game game, WorldGameState state){
		state.startTrade(this);
	}

	@Override
	public void update(int timesincelastframe, World map) {
		move(timesincelastframe, map);
		
	}

	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		//can't sell
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		//it's a cheater, you just get the item :)
		if(item.getStackSize() >= amount && merchant.getInventory().getMoney() >= item.getSellingPrice() * getBuySale() * amount){
			merchant.getInventory().addItem(item.getName(), amount);
		}
		
	}

	@Override
	public float getSellSale() {
		return 0;
	}

	@Override
	public float getBuySale() {
		return 0;
	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"CheaterNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
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
		return false;
	}

}
