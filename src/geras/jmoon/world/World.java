package geras.jmoon.world;

import geras.jmoon.entity.Entity;
import geras.jmoon.plants.Plant;

import java.util.HashMap;
import java.util.LinkedList;


public class World {
	
	public enum direction{
		north,
		south,
		east,
		west
	}
	
	private HashMap<String, Region> regions = new HashMap<String, Region>();
	
	
	
	public World(){
		super();
	}
	
	/**
	 * 
	 * @param regionName
	 * @param layerName
	 * @return
	 */
	public MapLayer addLayerToRegion(String regionName, String layerName){
		return null; //TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param layerName
	 * @return
	 */
	public boolean removeLayerFromRegion(String regionName, String layerName){
		return false; //TODO
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean removeLayerFromRegion(int index){
		return false; //TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param layerName
	 * @param x
	 * @param y
	 * @return
	 */
	public int getField(String regionName, String layerName, int x, int y){
		return 0; //TODO
	}
	
	/**
	 * get the value of the specified field
	 * @param layerIndex - index of the layer
	 * @param x - x coordinate of the field
	 * @param y - y coordinate of the field
	 * @return the value of the field
	 */
	public int getField(String regionName, int layerIndex, int x, int y){
		return 0; //TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param layerName
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setField(String regionName, String layerName, int x, int y, int value){
		//TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param layerIndex
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setField(String regionName, int layerIndex, int x, int y, int value){
		//TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param plant
	 */
	public void addPlantToRegion(String regionName, Plant plant){
		//TODO
	}
	
	/**
	 * 
	 * @param timeSinceLastFrame
	 */
	public void updatePlants(int timeSinceLastFrame){
		//TODO
	}
	
	/**
	 * 
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Plant getPlant(int posX, int posY){
		return null; //TODO
	}
	
	/**
	 * 
	 * @param plant
	 */
	public void removePlant(Plant plant){
		//TODO
	}
	
	/**
	 * 
	 * @param regionName
	 * @param x
	 * @param y
	 * @param sx
	 * @param sy
	 * @param width
	 * @param height
	 * @param layer
	 */
	public void render(String regionName, int x, int y, int sx, int sy, int width, int height, String layer){
		//TODO
	}
	
	/**
	 * Render the map
	 * @param region - the region to be drawn
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param sx - x coordinate of the start tile
	 * @param sy - y coordinate of the start tile
	 * @param width - draw width many tiles in x direction
	 * @param height - draw height many tiles in y direction
	 * @param layerIndex - index of the layer to be drawn
	 */
	public void render(Region region, int x, int y, int sx, int sy, int width, int height){
		//TODO
	}
	
	public void render(int x, int y, String layer){
		//TODO
	}
	
	public void render(int x, int y, int layerIndex){
		//TODO
	}
	
	public void render(int x, int y){
		//TODO
	}
}
