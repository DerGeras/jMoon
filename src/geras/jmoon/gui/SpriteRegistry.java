package geras.jmoon.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;

public class SpriteRegistry {
	
	private static Image noimg;
	
	public static final int SPRITE_SHEET_WIDTH = 1024;
	public static final int SPRITE_SHEET_HEIGHT = 1024;
	
	private static Image spriteSheet;
	
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
		Image res;
		try{
			res = new Image(path);
		} catch(SlickException e){
			System.err.println("Could not find image for path" + path);
			e.printStackTrace();
			return noimg;
		}
		int width = res.getWidth();
		int height = res.getHeight();
		Graphics g = spriteSheet.getGraphics();
		int[] pos = getPositionForImageSize(width, height);
		g.drawImage(res, pos[0], pos[1]);
		res.destroy();
		return spriteSheet.getSubImage(pos[0], pos[1], width, height);
	}
	
	private static int[] getPositionForImageSize(int width, int height){
		int[] res = {0,0};
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
