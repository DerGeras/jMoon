package geras.jmoon.entites;

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

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import de.matthiasmann.twl.model.SimpleChangableListModel;


public class PlayerEntity extends LivingEntity {
	
	private ArrayList<UsableItem> tools = new ArrayList<UsableItem>();
	private UsableItem currentTool;

	/**
	 * Basic constructor, set hunger to 0 and set the image for the rendering
	 */
	public PlayerEntity(SimpleChangableListModel<String> inventoryModel){
		super(inventoryModel);
		this.hunger = 0;
		setEntityImg("Sprites/Entities/Hero.png");
		width = Settings.tileWidth;
		height = Settings.tileHeight;
		tools.add(new HandItem());
		tools.add(new HoeItem());
		tools.add(new WateringCanItem());
		tools.add(new SickelItem());
		tools.add(new ShovelItem());
		for(UsableItem item : tools){
			if(item.getName() != "Hand"){
				inventory.addItem(item);
			}
		}
		inventory.addItem(new SeedItem(64));
		inventory.addItem(new WaterBucketItem(64));
		inventory.addItem(new FenceItem(64));
		inventory.addItem("Bucket", 64);
		currentTool = tools.get(0);
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
		inventory.updateModel();
	}
	
	/**
	 * Set the tool from the "Hotbar" to be used
	 * @param toolIndex - index of the tool in the "Hotbar"
	 */
	public void setTool(int toolIndex){
		if(toolIndex >= 0 && toolIndex < tools.size()){
			currentTool = tools.get(toolIndex);
		}
	}
	
	/**
	 * set the tool at the specified index (for the hotbar)
	 * @param toolIndex - index to set the tool to
	 * @param item - a usable Item
	 */
	public void setTool(int toolIndex, UsableItem item){
		if(toolIndex >= 0 && toolIndex < tools.size()){
			tools.set(toolIndex, item);
		}
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

	
	
}
