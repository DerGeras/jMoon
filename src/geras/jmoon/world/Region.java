package geras.jmoon.world;

import geras.jmoon.entity.Entity;
import geras.jmoon.nbt.TagCompound;
import geras.jmoon.plants.Plant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Region {
	
	public LinkedList<Entity> entityList = new LinkedList<Entity>(); //List of entities in this region
	
	private String name;
	private int width;
	private int height;
	
	private HashMap<String, MapLayer> layers = new HashMap<String, MapLayer>();
	private ArrayList<String> layerPos = new ArrayList<String>();
	
	private LinkedList<Plant> plants = new LinkedList<Plant>();
	
	public Region(String name, int width, int height){
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * create new region from NBTTagCompound
	 * @param compound
	 */
	public Region(TagCompound compound){
		try {
			this.name = compound.getString("name");
			this.width = compound.getInt("width");
			this.height = compound.getInt("height");
			//TODO
		} catch (IOException e) {
			System.err.println("Could not load data from region compound");
			e.printStackTrace();
		}
	}
	
	public void addEntity(Entity entity){
		entityList.add(entity);
	}
	
	public void removeEntity(Entity entity){
		entityList.remove(entity);
	}
	
	public void addPlant(Plant plant){
		plants.add(plant);
	}
	
	public void removePlant(Plant plant){
		plants.add(plant);
	}
	
	public void addLayer(String name){
		addLayer(new MapLayer(name, width, height));
	}
	
	public void addLayer(MapLayer layer){
		layers.put(layer.getName(), layer);
		layerPos.add(layer.getName());
	}
	
	/* **************************
	 * 
	 * GETTERS AND SETTERS
	 * 
	 ****************************/

	public LinkedList<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(LinkedList<Entity> entityList) {
		this.entityList = entityList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, MapLayer> getLayers() {
		return layers;
	}

	public void setLayers(HashMap<String, MapLayer> layers) {
		this.layers = layers;
	}

	public ArrayList<String> getLayerPos() {
		return layerPos;
	}

	public void setLayerPos(ArrayList<String> layerPos) {
		this.layerPos = layerPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public LinkedList<Plant> getPlants() {
		return plants;
	}

}
