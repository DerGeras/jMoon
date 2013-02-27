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
import geras.jmoon.world.WorldElements;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.model.SimpleChangableListModel;


public class PlayerEntity extends Entity {
	
	private float hunger; //Hunger Value
	
	private ArrayList<UsableItem> tools = new ArrayList<UsableItem>();
	private UsableItem currentTool;
	
	private Image cursorImg;

	/**
	 * Basic constructor, set hunger to 0 and set the image for the rendering
	 */
	public PlayerEntity(SimpleChangableListModel<String> inventoryModel){
		super(inventoryModel);
		this.hunger = 0;
		setEntityImg("Sprites/Hero.png");
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
		inventory.addItem(new FenceItem(Settings.maxStackSize));
		currentTool = tools.get(0);
		
		//TODO this is just testing crap
		try{
			cursorImg = new Image("Sprites/Cursor.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	public void update(int timesincelastframe, Map map){
		move(timesincelastframe, map);
	}
	
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY) {
		super.draw(g, mapTopX,mapTopY);
		
		//TODO this is just testing crap
		cursorImg.draw(mapTopX + getCursorX() - 3, mapTopY + getCursorY() - 3);
	}
	
	/**
	 * move the entity on the next frame
	 * depending on timesincelastframe, the move_speed and nextX/nextY
	 */
	private void move(int timesincelastframe, Map map){
		int oldX = posX;
		int oldY = posY;
		posX += timesincelastframe * move_speed * nextX;
		posY += timesincelastframe * move_speed * nextY;
		nextX = nextY = 0;
		//Collision
		int firstX = (posX - width / 3) / Settings.tileWidth;
		int firstY = (posY - height / 3) / Settings.tileHeight;
		int secondX = (posX + width / 3) / Settings.tileWidth;
		int secondY = (posY + height / 3) / Settings.tileHeight;
		
		if(map.getField("Decoration", firstX, firstY) == WorldElements.ROCK_VALUE
				|| map.getField("Decoration", secondX, firstY) == WorldElements.ROCK_VALUE
				|| map.getField("Decoration", firstX, secondY) == WorldElements.ROCK_VALUE
				|| map.getField("Decoration", secondX, secondY) == WorldElements.ROCK_VALUE){
			posX = oldX;
			posY = oldY;
		}
	}
	
	/**
	 * Use the currently selected tool
	 * @param map - current Map
	 */
	public void useItem(Map map){
		currentTool.useWorld(getCursorX(), getCursorY(), map, this);
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
	
	/**
	 * 
	 * @return the x coordinate of the cursor
	 */
	public int getCursorX(){
		switch(direction){
		case west: return posX - (width / 2) - 5;
		case east: return posX + (width / 2) + 5;
		default: return posX;
		}
	}
	
	/**
	 * 
	 * @return the y coordinate of the cursor
	 */
	public int getCursorY(){
		switch(direction){
		case north: return posY - (width / 2) - 5;
		case south: return posY + (width / 2) + 5;
		default: return posY;
		}
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
	
	public float getHunger() {
		return hunger;
	}

	/**
	 * Set hunger value
	 * @param hunger should be between 0 and 16
	 */
	public void setHunger(float hunger) {
		if(hunger < 0.0f) this.hunger = 0.0f;
		if(hunger > 16.0f) this.hunger = 16.0f;
		this.hunger = hunger;
	}

	
	
}
