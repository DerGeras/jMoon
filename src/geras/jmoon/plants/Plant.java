package geras.jmoon.plants;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.world.Region;
import geras.jmoon.world.World;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 
 * @author Geras
 *
 */
public abstract class Plant {
	
	protected int posX;
	protected int posY;
	
	protected int growthInterval = 1000 * 60; //grow automatically after x ticks
	protected int timeSinceLastGrowth = 0; //time since the last growth (+accumulated delays)
	
	protected int tileValue; //tile Value on the map
	
	protected Region region; //Map this plant is growing on
	
	public Plant(int x, int y){
		super();
		posX = x;
		posY = y;
	}
	
	public void setRegion(Region region){
		this.region = region;
	}
	
	/**
	 * update the plant
	 * @param timeSinceLastFrame - time since the last updates
	 */
	public abstract void update(int timeSinceLastFrame);
	
	/**
	 * grow the plant
	 */
	public abstract boolean grow();
	
	/**
	 * harvest the plant
	 * @param player - the player that harvests it. Mainly used to add items to the inventory
	 * @param map - the current map (used to destroy the plant if necessary
	 */
	public abstract void harvest(PlayerEntity player, World map);
	
	/**
	 * save to xml file
	 */
	public void saveToXML(BufferedWriter out){
		try {
			out.append("<plant posX=\"" + posX + "\" posY=\"" + posY + "\" timeSinceLastGrowth=\"" + timeSinceLastGrowth + "\" tileValue=\"" + tileValue + "\">");
			out.append("</plant>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	
	public int getGrowthInterval() {
		return growthInterval;
	}
	
	public void setGrowthInterval(int growthInterval) {
		this.growthInterval = growthInterval;
	}
	
	public int getTimeSinceLastGrowth() {
		return timeSinceLastGrowth;
	}
	
	public void setTimeSinceLastGrowth(int timeSinceLastGrowth) {
		this.timeSinceLastGrowth = timeSinceLastGrowth;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public int getTileValue(){
		return tileValue;
	}
	
	
}
