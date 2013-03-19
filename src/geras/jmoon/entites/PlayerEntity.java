package geras.jmoon.entites;

import java.io.BufferedWriter;
import java.io.IOException;

import geras.jmoon.items.FenceItem;
import geras.jmoon.items.HandItem;
import geras.jmoon.items.HoeItem;
import geras.jmoon.items.SeedItem;
import geras.jmoon.items.ShovelItem;
import geras.jmoon.items.SickelItem;
import geras.jmoon.items.UsableItem;
import geras.jmoon.items.WaterBucketItem;
import geras.jmoon.items.WateringCanItem;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

import org.newdawn.slick.Graphics;


public class PlayerEntity extends LivingEntity {
	
	private UsableItem currentTool;

	/**
	 * Basic constructor, set hunger to 0 and set the image for the rendering
	 */
	public PlayerEntity(){
		super();
		this.hunger = 0;
		setEntityImg("Sprites/Entities/Hero.png");
		width = Settings.tileWidth;
		height = Settings.tileHeight;
		inventory.addItem(new HoeItem());
		inventory.addItem(new WateringCanItem());
		inventory.addItem(new SickelItem());
		inventory.addItem(new ShovelItem());
		inventory.addItem(new SeedItem(64));
		inventory.addItem(new WaterBucketItem(64));
		inventory.addItem(new FenceItem(64));
		inventory.addItem("Bucket", 64);
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

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=PlayerEntity posX=" + posX + " posY=" + posY);
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
