package geras.jmoon.world;

import geras.jmoon.entity.Entity;
import geras.jmoon.nbt.TagCompound;
import geras.jmoon.nbt.TagList;
import geras.jmoon.registry.GameRegistry;
import geras.jmoon.tile.Tile;
import geras.jmoon.tileentity.TileEntity;

import java.io.IOException;
import java.util.LinkedList;

public class Region {
	
	private String name;
	private int width;
	private int height;
	
	private MapLayer baseLayer;
	private MapLayer decoLayer;
	private TileEntityLayer tileLayer;
	private boolean[][] obstacleLayer;
	
	public LinkedList<Entity> entityList = new LinkedList<Entity>(); //List of entities in this region
	
	private LinkedList<TileEntity> tileEntities = new LinkedList<TileEntity>();
	
	public Region(String name, int width, int height){
		this.name = name;
		this.width = width;
		this.height = height;
		baseLayer = new MapLayer(width, height);
		decoLayer = new MapLayer(width, height);
		tileLayer = new TileEntityLayer(width, height);
		obstacleLayer = new boolean[width][height];
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
			obstacleLayer = new boolean[width][height];
			baseLayer = new MapLayer(width, height);
			readLayerFromArray(baseLayer, compound.getIntegerArray("base"));
			decoLayer = new MapLayer(width, height);
			readLayerFromArray(decoLayer, compound.getIntegerArray("deco"));
			tileLayer = new TileEntityLayer(width, height);
			//read entities
			readEntities(compound);
			//read tileEntities
			readTileEntities(compound);
		} catch (IOException e) {
			System.err.println("Could not load data from region compound");
			e.printStackTrace();
		}
	}
	
	private void readLayerFromArray(MapLayer layer, int[] array){
		int c = 0;
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int value = array[c++];
				layer.setField(x, y, value);
				Tile tile = (Tile) GameRegistry.allItems[value];
				if(tile.isObstacle()){
					obstacleLayer[x][y] = true;
				}
			}
		}
	}
	
	private void readEntities(TagCompound compound){
		try {
			TagList list = compound.getTagList("entities");
			LinkedList<TagCompound> compounds = list.getTags(TagCompound.class);
			for(TagCompound c : compounds){
				//TODO
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readTileEntities(TagCompound compound){
		try {
			TagList list = compound.getTagList("tileEntities");
			LinkedList<TagCompound> compounds = list.getTags(TagCompound.class);
			for(TagCompound c : compounds){
				//TODO
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEntity(Entity entity){
		entityList.add(entity);
	}
	
	public void removeEntity(Entity entity){
		entityList.remove(entity);
	}
	
	public Tile getBaseTile(int x, int y){
		return (Tile) GameRegistry.allItems[getBaseTileID(x, y)];
	}
	
	public int getBaseTileID(int x, int y){
		return baseLayer.getField(x, y);
	}
	
	public void setBaseTile(int x, int y, int value){
		baseLayer.setField(x, y, value);
	}
	
	public Tile getDecoTile(int x, int y){
		return (Tile) GameRegistry.allItems[getDecoTileID(x, y)];
	}
	
	public int getDecoTileID(int x, int y){
		return decoLayer.getField(x, y);
	}
	
	public void setDecoTile(int x, int y, int value){
		decoLayer.setField(x, y, value);
	}
	
	public TileEntity getTileEntity(int x, int y){
		return tileLayer.getField(x, y);
	}
	
	public void setTileEntity(int x, int y, TileEntity entity){
		tileLayer.setField(x, y, entity);
	}
	
	public boolean isObstacle(int x, int y){
		return obstacleLayer[x][y];
	}
	
	public void setObstacle(int x, int y, boolean value){
		obstacleLayer[x][y] = value;
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
	public void render( int x, int y, int sx, int sy, int width, int height){
		//TODO
	}
	
	/* **************************
	 * 
	 * GETTERS AND SETTERS
	 * 
	 ****************************/

	public LinkedList<Entity> getEntityList() {
		return entityList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public LinkedList<TileEntity> getTileEntities() {
		return tileEntities;
	}

}
