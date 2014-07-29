package geras.jmoon.world;

import java.io.IOException;

import geras.jmoon.nbt.TagCompound;


public class MapLayer {

	private String name;
	
	private int width;
	private int height;
	
	private int[][] content;
	
	/**
	 * Simple constructor
	 * @param width - the width of the layer
	 * @param height - the height of the layer
	 */
	public MapLayer(String name, int width, int height){
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		content = new int[width][height];
	}
	
	/**
	 * create layer from tag compound
	 * @param compound
	 */
	public MapLayer(TagCompound compound){
		try {
			name = compound.getString("name");
			width = compound.getInt("width");
			height = compound.getInt("height");
			content = new int[width][height];
			//TODO
		} catch (IOException e) {
			System.err.println("Could not create layer from compound");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return the width of the layer
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * 
	 * @return the height of the layer
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * set the content of the field at position (x,y)
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param value - the value to set
	 */
	public void setField(int x, int y, int value){
		content[x][y] = value;
	}
	
	/**
	 * retrieve the content of the field at position (x,y)
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the content of the field
	 */
	public int getField(int x, int y){
		return content[x][y];
	}
	
	/**
	 * 
	 * @return the name of this layer
	 */
	public String getName(){
		return this.name;
	}
	
}
