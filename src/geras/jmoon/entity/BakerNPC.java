package geras.jmoon.entity;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.item.Item;
import geras.jmoon.world.World;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;

public class BakerNPC extends NPCEntity implements Merchant{

	public BakerNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		
		//TODO get own image
		setEntityImg("Sprites/Entities/Hero.png");
		inventory.addItem("Bread", 10);
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
			out.append("<entity case=\"BakerNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
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
		if(item.getName() == "Wheat" && item.getStackSize() >= amount){
			int rest = inventory.addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			merchant.getInventory().setMoney(merchant.getInventory().getMoney() + soldAmount * (int)Math.ceil(getSellSale() * item.getSellingPrice()));
		}
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		if(item.getName() == "Bread" && item.getStackSize() >= amount && merchant.getInventory().getMoney() >= (int)Math.ceil(item.getSellingPrice() * getBuySale()) * amount){
			int rest = merchant.getInventory().addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			merchant.getInventory().setMoney(merchant.getInventory().getMoney() - soldAmount * (int)Math.ceil(getBuySale() * item.getSellingPrice()));
		}
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
		return true;
	}

	@Override
	public boolean canSellTo() {
		return true;
	}

}
