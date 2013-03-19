package geras.jmoon.world;

import java.io.BufferedWriter;
import java.io.IOException;

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
	
	/**
	 * save to XML file
	 */
	public void saveToXML(BufferedWriter out){
		try {
			//startElement
			out.write("<layer name=" + name + " width=" + width + " height=" + height + ">");
			out.newLine();
			
			//write content
			for(int j = 0; j < height; j++){
				for(int i = 0; i < width; i++){
					out.append(content[i][j] + " ");
				}
				out.newLine();
			}
			out.flush();
			
			//endElement
			out.write("</layer>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
