package geras.jmoon.village;

import geras.jmoon.AI.VillagerAI;
import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.entity.Merchant;
import geras.jmoon.entity.NPCEntity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.item.Item;
import geras.jmoon.reference.Settings;
import geras.jmoon.time.Clock;
import geras.jmoon.world.World;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.xml.sax.Attributes;

public class VillagerNPC extends NPCEntity implements Merchant{
	
	private static float hungerRatio = 8.0f / Clock.millisecondsPerDay;
	
	public static int OUT_OF_FIELD_VALUE_X = -50;
	public static int OUT_OF_FIELD_VAlUE_Y = -50;
	
	private VillagerAI brain = new VillagerAI();

	public VillagerNPC(){
		super("", "Villager", OUT_OF_FIELD_VALUE_X, OUT_OF_FIELD_VAlUE_Y);
		
		Village.addVillager(this);
		setEntityImg("Sprites/Entities/Hero.png");
		move_speed /= 2;
	}
	
	public VillagerNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		
		Village.addVillager(this);
		setEntityImg("Sprites/Entities/Hero.png");
		move_speed /= 2;
	}
	
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY, World map){
		if(posX != OUT_OF_FIELD_VALUE_X && posY != OUT_OF_FIELD_VAlUE_Y){
			super.draw(g, mapTopX, mapTopY, map);
		}
	}

	@Override
	public void update(int timesincelastframe, World map) {
		hunger += (float)timesincelastframe * hungerRatio;
		
		//check if the hunger is too big
		if(hunger > Settings.maxHunger){
			kill(map);
			return;
		}
		
		brain.update(this, timesincelastframe);
		move(timesincelastframe,map);
	}
	
	@Override
	public void move(int timesincelastframe, World map){
		posX += timesincelastframe * move_speed * nextX;
		posY += timesincelastframe * move_speed * nextY;
	}

	@Override
	public void interact(PlayerEntity player, World map, Game game,
			WorldGameState state) {
		//Currently: do nothing

	}
	
	@Override
	public void kill(World map){
		map.entityList.remove(this);
		Village.removeVillager(this);
	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"VillagerNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\" timeSinceLastBuy=\"" + brain.getTimeSinceLastBuy() + "\">");
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
	public void readFromAttributes(Attributes attributes){
		super.readFromAttributes(attributes);
		
		String timeSinceLastBuyS = attributes.getValue("timeSinceLastBuy");
		
		if(timeSinceLastBuyS != null){
			brain.setTimeSinceLastBuy(Integer.parseInt(timeSinceLastBuyS));
		}
	}
	

	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		// can't sell
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		// can't buy
		
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
	public boolean canBuyFrom() {
		return false;
	}

	@Override
	public boolean canSellTo() {
		return false;
	}

}
