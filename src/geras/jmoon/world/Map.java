package geras.jmoon.world;

import geras.jmoon.entites.Entity;
import geras.jmoon.plants.Plant;
import geras.jmoon.settings.Settings;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Map {
	
	public LinkedList<Entity> entityList = new LinkedList<Entity>(); //List of entities in the world
	
	public enum direction{
		north,
		south,
		east,
		west
	}
	
	private HashMap<String, MapLayer> layers = new HashMap<String, MapLayer>();
	private ArrayList<String> layerPos = new ArrayList<String>();
	
	private WorldElements worldElements;
	
	private int width;
	private int height;
	
	private LinkedList<Plant> plants = new LinkedList<Plant>();
	
	/**
	 * 
	 * @param imageFile relative path to the sprite file
	 */
	public Map(String imageFile, int width, int height){
		super();
		worldElements = new WorldElements(imageFile);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Add a layer to the map
	 */
	public MapLayer addLayer(String name){
		layers.put(name, new MapLayer(name, width, height));
		layerPos.add(name);
		return layers.get(name);
	}
	
	/**
	 * remove the given layer
	 * @param layer - the layer to remove
	 * @return if the layer was removed
	 */
	public boolean removeLayer(String name){
		boolean res = layers.remove(name) != null;
		if(res) layerPos.remove(name);
		return res;
	}
	
	/**
	 * remove a layer with the given index
	 * @param index - the index of the layer
	 * @return whether the layer was removed
	 */
	public boolean removeLayer(int index){
		return layers.remove(layerPos.remove(index)) != null;
	}
	
	/**
	 * set the positions of the layer (bottom to top)
	 * Keep in mind that layers that are not in the given layerPos
	 * are not accessable by index anymore!
	 * @param layerPos - the new positions
	 */
	public void setLayerPos(ArrayList<String> layerPos){
		this.layerPos = layerPos;
	}
	
	/**
	 * get the value of the specified field
	 * @param layerName - name of the layer
	 * @param x - x coordinate of the field
	 * @param y - y coordinate of the field
	 * @return the value of the field
	 */
	public int getField(String layerName, int x, int y){
		return layers.get(layerName).getField(x, y);
	}
	
	/**
	 * get the value of the specified field
	 * @param layerIndex - index of the layer
	 * @param x - x coordinate of the field
	 * @param y - y coordinate of the field
	 * @return the value of the field
	 */
	public int getField(int layerIndex, int x, int y){
		return getField(layerPos.get(layerIndex),x,y);
	}
	
	/**
	 * set the field on the layer to the given value
	 * @param layerName - name of the layer
	 * @param x - x coordinate of the field
	 * @param y - y coordinate of the field
	 * @param value - the new value for the field
	 */
	public void setField(String layerName, int x, int y, int value){
		layers.get(layerName).setField(x, y, value);
	}
	
	/**
	 * set the field on the layer to the given value
	 * @param layerIndex - index of the layer
	 * @param x - x coordinate of the field
	 * @param y - y coordinate of the field
	 * @param value - the new value for the field
	 */
	public void setField(int layerIndex, int x, int y, int value){
		setField(layerPos.get(layerIndex), x, y, value);
	}
	
	/**
	 * 
	 * @param plant the new plant
	 */
	public void addPlant(Plant plant){
		plants.add(plant);
	}
	
	/**
	 * Update all the plants on the map
	 * @param timeSinceLastFrame - time since the last update
	 */
	public void updatePlants(int timeSinceLastFrame){
		for(Plant plant:plants){
			plant.update(timeSinceLastFrame);
		}
	}
	
	/**
	 * returns the plant at the specific position, if existend
	 * @param posX - x coordinate
	 * @param posY - y coordinate
	 * @return the plant at position (posX,posY) or null
	 */
	public Plant getPlant(int posX, int posY){
		for(Plant plant : plants){
			if(plant.getPosX() == posX && plant.getPosY() == posY){
				return plant;
			}
		}
		return null;
	}
	
	/**
	 * remove the given plant from the plants
	 * @param plant - the plant to remove
	 */
	public void removePlant(Plant plant){
		plants.remove(plant);
		setField("Plants", plant.getPosX(), plant.getPosY(), -1);
	}
	
	/**
	 * 
	 * @return the worldElements instance for this world
	 */
	public WorldElements getWorldElement(){
		return worldElements;
	}
	
	/**
	 * Render the map
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param sx - x coordinate of the start tile
	 * @param sy - y coordinate of the start tile
	 * @param width - draw width many tiles in x direction
	 * @param height - draw height many tiles in y direction
	 * @param layer - the layer to be drawn
	 */
	public void render(int x, int y, int sx, int sy, int width, int height, String layer){
		if(layers.get(layer) == null){
			throw new IllegalArgumentException("Layer " + layer + " doesn't exist");
		}
		MapLayer currLayer;
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				currLayer = layers.get(layer);
				int tileValue;
				if(sx + i < 0 || sy + j < 0 || sx + i >= currLayer.getWidth() || sy + j >= currLayer.getHeight()){
					//set a default value for tiles not on the map
					switch(layer){
					case "Ground" : tileValue = WorldElements.STONE_VALUE; break;
					default: tileValue = -1; break;
					}
				}
				else{
					tileValue = layers.get(layer).getField(sx + i, sy + j);
				}
				worldElements.draw(x + i * Settings.tileWidth,y + j * Settings.tileHeight, sx + i, sy + j, tileValue, this);
			}
		}
	}
	
	/**
	 * Render the map
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param sx - x coordinate of the start tile
	 * @param sy - y coordinate of the start tile
	 * @param width - draw width many tiles in x direction
	 * @param height - draw height many tiles in y direction
	 * @param layerIndex - index of the layer to be drawn
	 */
	public void render(int x, int y, int sx, int sy, int width, int height, int layerIndex){
		if(layerPos.get(layerIndex) == null){
			throw new IllegalArgumentException("Layer " + layerIndex + " doesn't exist");
		}
		render(x,y,sx,sy,width,height,layerPos.get(layerIndex));
	}
	
	public void render(int x, int y, String layer){
		render(x,y,0,0,width,height,layer);
	}
	
	public void render(int x, int y, int layerIndex){
		if(layerPos.get(layerIndex) == null){
			throw new IllegalArgumentException("Layer " + layerIndex + " doesn't exist");
		}
		render(x,y,layerPos.get(layerIndex));
	}
	
	public void render(int x, int y){
		for(String layer : layerPos){
			render(x,y,layer);
		}
	}
	
	public void saveToXML(BufferedWriter out){
		try {
			out.write("<map width=\"" + width + "\" height=\"" + height + "\">");
			out.newLine();
			
			//save layers
			out.append("<layers>");
			out.newLine();
			for(String layerName : layerPos){
				layers.get(layerName).saveToXML(out);
				out.newLine();
			}
			out.append("</layers>");
			out.newLine();
			
			//save plants
			out.append("<plants>");
			out.newLine();
			for(Plant plant : plants){
				plant.saveToXML(out);
				out.newLine();
			}
			out.append("</plants>");
			out.newLine();
			
			//save entities
			out.append("<entities>");
			out.newLine();
			for(Entity entity : entityList){
				entity.saveToXML(out);
				out.newLine();
			}
			out.append("</entities>");
			out.newLine();
			
			out.write("</map>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new map
	 */
	public void initialize(){
		addLayer("Ground");
		addLayer("Decoration");
		addLayer("Plants");
		MapLayer gorundLayer = layers.get("Ground");
		MapLayer decoLayer = layers.get("Decoration");
		MapLayer plantLayer = layers.get("Plants");
		int middle = Settings.mapHeight / 2;
		for(int i = 0; i < Settings.mapWidth; i++){
			for(int j = 0; j < Settings.mapHeight; j++){
				//Ground Layer
				if(j == middle - 1 || j == middle || j == middle + 1){
					gorundLayer.setField(i, j, WorldElements.GRAVEL_VALUE);					
				}
				else{
					gorundLayer.setField(i, j, WorldElements.GRASS_VALUE);
				}
				
				//Deco Layer
				if(i == 0 || j == 0 || i == decoLayer.getWidth() - 1 || j == decoLayer.getHeight() - 1){
					decoLayer.setField(i, j, WorldElements.ROCK_VALUE);
				}
				else{
					decoLayer.setField(i, j, -1);
				}
				
				//Plant Layer
				plantLayer.setField(i, j, -1);
			}
		}
	}
}
