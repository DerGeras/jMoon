package geras.jmoon.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteRegistry {
	
	private static Image noimg;
	
	static{
		try {
			noimg = new Image("res/sprites/noimg.png");
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
		try{
			return new Image(path);
		} catch(SlickException e){
			System.err.println("Could not find image for path" + path);
			e.printStackTrace();
			return noimg;
		}
	}
	
}
