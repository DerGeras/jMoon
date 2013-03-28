package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.HandItem;
import geras.jmoon.items.Item;
import geras.jmoon.items.UsableItem;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.xml.sax.Attributes;


public class PlayerEntity extends LivingEntity implements Merchant{
	
	private UsableItem currentTool;

	/**
	 * Basic constructor, set hunger to 0 and set the image for the rendering
	 */
	public PlayerEntity(){
		super();
		this.hunger = 0;
		setEntityImg("Sprites/Entities/Hero.png");
		inventory.addItem("Hoe",1);
		inventory.addItem("Watering Can", 1);
		inventory.addItem("Sickel",1);
		inventory.addItem("Shovel",1);
		inventory.addItem("Seeds", 64);
		inventory.addItem("Water Bucket", 64);
		inventory.addItem("Fence",64);
		inventory.addItem("Bucket", 64);
		inventory.addItem("Potato", 64);
		inventory.setMoney(500);
		currentTool = new HandItem();
	}
	
	public void update(int timesincelastframe, Map map){
		move(timesincelastframe, map);
	}
	
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY, Map map) {
		super.draw(g, mapTopX,mapTopY,map);
	}
	
	/**
	 * Use the currently selected tool
	 * @param map - current Map
	 */
	public void useItem(Map map, int x, int y){
		currentTool.useWorld(x, y, map, this);
	}
	
	public void setCurrentTool(UsableItem item){
		currentTool = item;
	}
	
	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"PlayerEntity\" posX=\"" + posX + "\" posY=\"" + posY);
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
	public void readFromAttributes(Attributes attributes) {
		String posXS = attributes.getValue("posX");
		String posYS = attributes.getValue("posY");
		String hungerS = attributes.getValue("hunger");
		String thirstS = attributes.getValue("thirst");
		
		if(posXS != null){
			posX = Float.parseFloat(posXS);
		}
		if(posYS != null){
			posY = Float.parseFloat(posYS);
		}
		if(hungerS != null){
			hunger = Float.parseFloat(hungerS);
		}
		if(thirstS != null){
			thirst = Float.parseFloat(thirstS);
		}
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


	@Override
	public void interact(PlayerEntity player, Map map, Game game,
			WorldGameState state) {
		//Currently no player interactions
		
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
	public UsableItem getCurrentTool(){
		return currentTool;
	}



	
	
}
