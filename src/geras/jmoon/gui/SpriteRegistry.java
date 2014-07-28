package geras.jmoon.gui;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;

public class SpriteRegistry {
	
	private static Image noimg;
	
	public static final int SPRITE_SHEET_WIDTH = 1024;
	public static final int SPRITE_SHEET_HEIGHT = 1024;
	
	private static Image spriteSheet;
	
	private static LinkedList<Region2D> occupiedRegions = new LinkedList<Region2D>();
	
	static{
		try {
			spriteSheet = new Image(SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
			noimg = new Image("res/sprites/noimg.png");
			int width = noimg.getWidth();
			int height = noimg.getHeight();
			Graphics g = spriteSheet.getGraphics();
			int[] pos = getPositionForImageSize(width, height);
			g.drawImage(noimg, pos[0], pos[1]);
			noimg.destroy();
			noimg = spriteSheet.getSubImage(pos[0], pos[1], width, height);			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * register images for given paths and return the (sub)images
	 * @param paths
	 * @return
	 */
	public static Image registerImage(String path) throws SlickException{
		Image src;
		try{
			src = new Image(path);
		} catch(SlickException e){
			System.err.println("Could not find image for path" + path);
			e.printStackTrace();
			return noimg;
		}
		int width = src.getWidth();
		int height = src.getHeight();
		Graphics g = spriteSheet.getGraphics();
		int[] pos = getPositionForImageSize(width, height);
		g.drawImage(src, pos[0], pos[1]);
		src.destroy();
		return spriteSheet.getSubImage(pos[0], pos[1], width, height);
	}
	
	/**
	 * compute the position for the new image on the spritesheet
	 * @param width
	 * @param height
	 * @return
	 */
	private static int[] getPositionForImageSize(int width, int height){
		int[] res = {0,0};
		for(Region2D region: occupiedRegions){
			//try to append right of the region
			int x = region.x + region.width;
			int y = region.y;
			boolean hit = false;
			if(x + width < SPRITE_SHEET_WIDTH && y + height < SPRITE_SHEET_HEIGHT){
				for(Region2D r: occupiedRegions){
					hit = hit || r.collidesWithRegion(x, y, width, height);
				}
			} else { //out of bounds
				hit = true;
			}
			if(!hit){ //append to the left
				res[0] = x;
				res[1] = y;
			} else {
				//try to append to the bottom of the region
				x = region.x;
				y = region.y + region.height;
				if(x + width < SPRITE_SHEET_WIDTH && y + height < SPRITE_SHEET_HEIGHT){
					for(Region2D r: occupiedRegions){
						hit = hit || r.collidesWithRegion(x, y, width, height);
					}
				} else {
					hit = true; //out of bounds
				}
				if(!hit){ //append to the bottom
					res[0] = x;
					res[1] = y;
				} else {
					//this should basically not happen
					System.err.println("Insufficient space for new images!");
				}
			}
		}
		boolean merged = false;
		for(Region2D region : occupiedRegions){ //try to merge with existing region
			merged = region.mergeWithRegion(res[0], res[1], width, height);
			if(merged){
				break;
			}
		}
		if(!merged){ //create new region
			occupiedRegions.add(new Region2D(res[0], res[1], width, height));
		}
		return res;
	}
	
	/**
	 * write the current spritesheet to a file
	 * @param path
	 */
	public static void writeSpriteSheetToFile(String path){
		try {
			ImageOut.write(spriteSheet, path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void startUse(){
		spriteSheet.startUse();
	}
	
	public static void endUse(){
		spriteSheet.endUse();
	}
	
}
