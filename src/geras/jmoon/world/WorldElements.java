package geras.jmoon.world;

import geras.jmoon.settings.Settings;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 * @author Geras
 *	
 *	this class contains all t he information about objects in the world
 */
public class WorldElements {
	
	//Map
	public static final int GRASS_VALUE = 0;
	public static final int GRAVEL_VALUE = 1;
	public static final int STONE_VALUE = 2;
	public static final int DIRT_VALUE = 3;
	public static final int WETDIRT_VALUE = 4;
	public static final int WATER_VALUE = 5;
	public static final int HOLE_VALUE = 6;
	public static final int OVERLAY_VALUE = 7;
	
	//Deco
	public static final int ROCK_VALUE = 100;
	public static final int FENCE_VALUE = 101;
	
	//Plants
	public static final int WEED_MIN_VALUE = 200;
	public static final int WEED_MAX_VALUE = 205;
	
	private Image worldPNG;
	
	public HashMap<String,Image> tiles = new HashMap<String,Image>();
	
	
	/**
	 * 
	 * @param imageFile - relative path to the sprite image
	 */
	public WorldElements(String imageFile){
		super();
		try {
			worldPNG = new Image(imageFile);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		initializeSubImages();
	}
	
	
	private void initializeSubImages(){
		//initialize all the subimages
		int tileWidth = Settings.tileWidth;
		int tileHeight = Settings.tileHeight;
		
		//Map
		tiles.put("Grass", worldPNG.getSubImage(0, 0, tileWidth, tileHeight));
		tiles.put("Gravel", worldPNG.getSubImage(1*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("Stone", worldPNG.getSubImage(2*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("Dirt", worldPNG.getSubImage(3*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("WetDirt", worldPNG.getSubImage(4*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("Water", worldPNG.getSubImage(5*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("Hole", worldPNG.getSubImage(6*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		tiles.put("Overlay", worldPNG.getSubImage(7*tileWidth, 0*tileHeight, tileWidth, tileHeight));
		
		//Decoration
		//FENCE BLOCK
		tiles.put("Fence", worldPNG.getSubImage(0*tileWidth, 1*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceLeft", worldPNG.getSubImage(1*tileWidth, 1*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceLeftRightCenter", worldPNG.getSubImage(2*tileWidth, 1*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceRight", worldPNG.getSubImage(3*tileWidth, 1*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceTop", worldPNG.getSubImage(0*tileWidth, 2*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceTopBottomCenter", worldPNG.getSubImage(0*tileWidth, 3*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceBottom", worldPNG.getSubImage(0*tileWidth, 4*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceTopLeft", worldPNG.getSubImage(1*tileWidth, 2*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceTopCenter", worldPNG.getSubImage(2*tileWidth, 2*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceTopRight", worldPNG.getSubImage(3*tileWidth, 2*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceCenterLeft", worldPNG.getSubImage(1*tileWidth, 3*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceCenter", worldPNG.getSubImage(2*tileWidth, 3*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceCenterRight", worldPNG.getSubImage(3*tileWidth, 3*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceBottomLeft", worldPNG.getSubImage(1*tileWidth, 4*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceBottomCenter", worldPNG.getSubImage(2*tileWidth, 4*tileHeight, tileWidth, tileHeight));
		tiles.put("FenceBottomRight", worldPNG.getSubImage(3*tileWidth, 4*tileHeight, tileWidth, tileHeight));
		
		//Other Decoration
		tiles.put("Rock", worldPNG.getSubImage(4 * tileWidth, 1*tileHeight, tileWidth, tileHeight));
		
		//Crops'n'Weed
		tiles.put("Weed1", worldPNG.getSubImage(0*tileWidth, 5*tileHeight, tileWidth,tileHeight));
		tiles.put("Weed2", worldPNG.getSubImage(1*tileWidth, 5*tileHeight, tileWidth,tileHeight));
		tiles.put("Weed3", worldPNG.getSubImage(2*tileWidth, 5*tileHeight, tileWidth,tileHeight));
		tiles.put("Weed4", worldPNG.getSubImage(3*tileWidth, 5*tileHeight, tileWidth,tileHeight));
		tiles.put("Weed5", worldPNG.getSubImage(4*tileWidth, 5*tileHeight, tileWidth,tileHeight));
		tiles.put("Weed6", worldPNG.getSubImage(5*tileWidth, 5*tileHeight, tileWidth,tileHeight));
	}
	
	/**
	 * draw the given tile at coordinate x,y
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param fieldX - x coordinate on the map
	 * @param fieldY - y coordinate on the map
	 * @param tileValue - value of the tile that should be drawn
	 * @param map - the current map
	 */
	public void draw(int x, int y, int fieldX, int fieldY, int tileValue, Map map){
		switch(tileValue){
		//Map
		case GRASS_VALUE: drawGrass(x,y,map); break;
		case GRAVEL_VALUE: drawGravel(x,y,map);break;
		case STONE_VALUE: drawStone(x,y,map);break;
		case DIRT_VALUE: drawDirt(x,y,map);break;
		case WETDIRT_VALUE: drawWetDirt(x,y,map);break;
		case WATER_VALUE: drawWater(x,y,map);break;
		case HOLE_VALUE: drawHole(x,y,map);break;
		case OVERLAY_VALUE: drawOverlay(x,y,map);break;
		
		//Decoration
		case FENCE_VALUE: drawFence(x,y,map, fieldX, fieldY);break;
		case ROCK_VALUE: drawRock(x,y,map);break;
		}
		
		//Crops'n'Weed
		if(tileValue >= WEED_MIN_VALUE && tileValue <= WEED_MAX_VALUE){
			drawWeed(x,y,map,tileValue - WEED_MIN_VALUE);
		}
	}
	
	
	/////////////////////////////////////////////////////////////
	//
	// 		many ugly drawing functions ahead
	//
	/////////////////////////////////////////////////////////////
	
	
	//Map


	private void drawGrass(int x,int y, Map map){
		tiles.get("Grass").draw(x,y);
	}
	
	private void drawGravel(int x, int y, Map map){
		tiles.get("Gravel").draw(x,y);
	}
	
	private void drawStone(int x, int y, Map map){
		tiles.get("Stone").draw(x, y);
	}
	
	private void drawDirt(int x, int y, Map map){
		tiles.get("Dirt").draw(x,y);
	}
	
	private void drawWetDirt(int x, int y, Map map) {
		tiles.get("WetDirt").draw(x,y);
		
	}
	
	private void drawWater(int x, int y, Map map){
		tiles.get("Water").draw(x,y);
	}
	
	private void drawHole(int x, int y, Map map){
		tiles.get("Hole").draw(x,y);
	}
	
	private void drawOverlay(int x, int y, Map map){
		tiles.get("Overlay").draw(x,y);
	}
	
	//Decoration
	
	/**
	 * Yes, this one is kinda ugly
	 * @param x
	 * @param y
	 * @param map
	 * @param fieldX
	 * @param fieldY
	 */
	private void drawFence(int x, int y, Map map, int fieldX, int fieldY){
		//Check right
		if(map.getField("Decoration", fieldX + 1, fieldY) == FENCE_VALUE){
			if(map.getField("Decoration", fieldX, fieldY + 1) == FENCE_VALUE){
				if(map.getField("Decoration", fieldX - 1, fieldY) == FENCE_VALUE){
					if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
						tiles.get("FenceCenter").draw(x,y);
					}
					else{
						tiles.get("FenceTopCenter").draw(x,y);
					}
				}
				else{
					if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
						tiles.get("FenceCenterLeft").draw(x,y);
					}
					else{
						tiles.get("FenceTopLeft").draw(x,y);
					}
				}
			}
			else{
				if(map.getField("Decoration", fieldX - 1, fieldY) == FENCE_VALUE){
					if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
						tiles.get("FenceBottomCenter").draw(x,y);
					}
					else{
						tiles.get("FenceLeftRightCenter").draw(x,y);
					}
				}
				else{
					if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
						tiles.get("FenceBottomLeft").draw(x,y);
					}
					else{
						tiles.get("FenceLeft").draw(x,y);
					}
				}
			}
			return;
		}
		
		//Check left
		if(map.getField("Decoration", fieldX-1, fieldY) == FENCE_VALUE){
			if(map.getField("Decoration", fieldX, fieldY + 1) == FENCE_VALUE){
				if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
					tiles.get("FenceCenterRight").draw(x,y);
				}
				else{
					tiles.get("FenceTopRight").draw(x,y);
				}
			}
			else{
				if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
					tiles.get("FenceBottomRight").draw(x,y);
				}
				else{
					tiles.get("FenceRight").draw(x,y);
				}
			}
			return;
		}
		
		//check bottom
		if(map.getField("Decoration", fieldX, fieldY + 1) == FENCE_VALUE){
			if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
				tiles.get("FenceTopBottomCenter").draw(x,y);
			}
			else{
				tiles.get("FenceTop").draw(x,y);
			}
			return;
		}
		
		//Check top
		if(map.getField("Decoration", fieldX, fieldY - 1) == FENCE_VALUE){
			tiles.get("FenceBottom").draw(x,y);
			return;
		}
		//Standalone
		tiles.get("Fence").draw(x,y);
	}
	
	
	private void drawRock(int x, int y, Map map){
		tiles.get("Rock").draw(x,y);
	}
	
	
	//Crops'n'Weed
	private void drawWeed(int x, int y, Map map, int growth){
		switch(growth){
		case 0: tiles.get("Weed1").draw(x,y);break;
		case 1: tiles.get("Weed2").draw(x,y);break;
		case 2: tiles.get("Weed3").draw(x,y);break;
		case 3: tiles.get("Weed4").draw(x,y);break;
		case 4: tiles.get("Weed5").draw(x,y);break;
		case 5: tiles.get("Weed6").draw(x,y);break;
		}
	}
	
}
