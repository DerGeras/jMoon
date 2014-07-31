package geras.jmoon.tile;

import geras.jmoon.gui.SpriteRegistry;
import geras.jmoon.item.Item;
import geras.jmoon.world.Region;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile extends Item{

	public Tile(int id, String name, String displayName, int maxStackSize, int sellingPrice) {
		super(id, name, displayName, 0, maxStackSize, sellingPrice);
	}

	/**
	 * register images at startup
	 */
	public void registerImages(String modid){
		try {
			image = SpriteRegistry.registerImage(getImagePath(modid));
		} catch (SlickException e) {
			System.err.println("Could not register image for item " + name
					+ "of " + modid);
			e.printStackTrace();
		}
	}
	
	public String getImagePath(String modid){
		return "sprites/" + modid + "/tile/" + name + ".png";
	}
	
	/**
	 * get the image to be rendered on the ground (tile image)
	 * @param region
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Image getImage(Region region, int posX, int posY){
		return image;
	}
	
}
