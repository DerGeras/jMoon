package geras.jmoon.entity;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.item.Item;
import geras.jmoon.world.World;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BlackSmithNPC extends NPCEntity implements Merchant{

	public BlackSmithNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		
		try{
			this.img = new Image("Sprites/Entities/Blacksmith.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
		
		//fill inventory
		inventory.addItem("Sickel", 2);
		inventory.addItem("Shovel", 2);
		inventory.addItem("Bucket", 5);
		inventory.addItem("Hoe", 2);
		
	}

	@Override
	public void interact(PlayerEntity player, World map, Game game,
			WorldGameState state) {
		state.startTrade(this);

	}

	@Override
	public void update(int timesincelastframe, World map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"BlackSmithNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
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
	public void sellTo(Merchant merchant, Item item, int amount) {
		//can't sell to the BlackSmith (currently)
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		if(item.getStackSize() >= amount && merchant.getInventory().getMoney() >= item.getSellingPrice() * getBuySale() * amount){
			int rest = merchant.getInventory().addItem(item.getName(), amount);
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			merchant.getInventory().setMoney(merchant.getInventory().getMoney() - soldAmount * (int)Math.ceil(getBuySale() * item.getSellingPrice()));
		}
	}

	@Override
	public float getSellSale() {
		return 2.0f;
	}

	@Override
	public float getBuySale() {
		return 0.9f;
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
