package geras.jmoon.plants;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.world.Map;

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
	
	protected Map map; //Map this plant is growing on
	
	public Plant(int x, int y, int tileValue, Map map){
		super();
		posX = x;
		posY = y;
		this.tileValue = tileValue;
		this.map = map;
		map.setField("Plants", posX, posY, tileValue);
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
	public abstract void harvest(PlayerEntity player, Map map);
	
	/**
	 * save to xml file
	 */
	public void saveToXML(BufferedWriter out){
		try {
			out.append("<plant posX=" + posX + " posY=" + posY + " timeSinceLastGrowth=" + timeSinceLastGrowth + " tileValue=" + tileValue + ">");
			out.append("</plant>");
			out.flush();
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
