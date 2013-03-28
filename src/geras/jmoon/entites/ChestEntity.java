package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.Inventory;
import geras.jmoon.items.Item;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;
import org.xml.sax.Attributes;

public class ChestEntity extends Entity implements Merchant {
	
	private Inventory inventory;
	
	public ChestEntity(int posX, int posY){
		super();
		width = 32;
		height = 32;
		this.posX = posX;
		this.posY = posY;
		
		setEntityImg("Sprites/Entities/Chest.png");
		
		inventory = new Inventory();
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		if(item.getStackSize() >= amount){
			int rest = inventory.addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
		}
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		if(item.getStackSize() >= amount){
			int rest = merchant.getInventory().addItem(item.getName(), amount, item.getDurability());
			int soldAmount = amount - rest;
			item.removeItems(soldAmount);
			if(merchant != JMoonGame.player){
				inventory.addMoney(soldAmount * (int)Math.ceil(getBuySale() * item.getSellingPrice()));
			}
		}
	}

	@Override
	public float getSellSale() {
		return 1;
	}

	@Override
	public float getBuySale() {
		return 2;
	}

	@Override
	public boolean canBuyFrom() {
		return true;
	}

	@Override
	public boolean canSellTo() {
		return true;
	}

	@Override
	public void update(int timesincelastframe, Map map) {
		if(inventory.getMoney() > 0){
			JMoonGame.player.getInventory().addMoney(inventory.getMoney());
			inventory.setMoney(0);
		}
	}

	@Override
	public void interact(PlayerEntity player, Map map, Game game,
			WorldGameState state) {

		state.startTrade(this);

	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"ChestEntity\" posX=\"" + posX + "\" posY=\"" + posY + "\">");
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
	public void readFromAttributes(Attributes attributes) {		
		String posXS = attributes.getValue("posX");
		String posYS = attributes.getValue("posY");
		
		if(posXS != null){
			posX = Float.parseFloat(posXS);
		}
		if(posYS != null){
			posY = Float.parseFloat(posYS);
		}

	}

}
