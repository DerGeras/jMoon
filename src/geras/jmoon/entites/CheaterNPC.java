package geras.jmoon.entites;

import java.io.BufferedWriter;
import java.io.IOException;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.Item;
import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CheaterNPC extends NPCEntity implements Merchant{

	public CheaterNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		width = 32;
		height = 32;
		try{
			this.entityImg = new Image("Sprites/Entities/Cheater.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
		//fill inventory
		inventory.addItem("Sickel", 1);
		inventory.addItem("Shovel", 1);
		inventory.addItem("Water Bucket", 1);
		inventory.addItem("Hoe", 1);
		inventory.addItem("Fence", 1);
		inventory.addItem("Watering Can", 1);
	}
	
	public void interact(PlayerEntity player, Map map, Game game, WorldGameState state){
		state.startTrade(this);
	}

	@Override
	public void update(int timesincelastframe, Map map) {
		move(timesincelastframe, map);
		
	}

	@Override
	public void sellTo(PlayerEntity player, Item item, int amount) {
		//can't sell
		
	}

	@Override
	public void buyFrom(PlayerEntity player, Item item, int amount) {
		//it's a cheater, you just get the item :)
		if(item.getStackSize() >= amount && player.getInventory().getMoney() >= item.getSellingPrice() * getBuySale() * amount){
			player.getInventory().addItem(item.getName(), amount);
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
			out.append("<entity case=CheaterNPC name=" + name + " title=" + title + " posX=" + posX + " posY=" + posY);
			out.append(" hunger=" + hunger + " thirst" + thirst + ">");
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

}
