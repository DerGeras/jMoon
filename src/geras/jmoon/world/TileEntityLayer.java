package geras.jmoon.world;

import geras.jmoon.tileentity.TileEntity;

public class TileEntityLayer {
	
	private int width;
	private int height;
	
	private TileEntity[][] content;
	
	/**
	 * Simple constructor
	 * @param width - the width of the layer
	 * @param height - the height of the layer
	 */
	public TileEntityLayer(int width, int height){
		super();
		this.width = width;
		this.height = height;
		content = new TileEntity[width][height];
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
	public void setField(int x, int y, TileEntity value){
		content[x][y] = value;
	}
	
	/**
	 * retrieve the content of the field at position (x,y)
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the content of the field
	 */
	public TileEntity getField(int x, int y){
		return content[x][y];
	}
	
}
