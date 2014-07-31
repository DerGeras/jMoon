package geras.jmoon.tileentity;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.gui.SpriteRegistry;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.nbt.TagCompound;
import geras.jmoon.reference.Settings;
import geras.jmoon.world.Region;

import java.io.IOException;

import org.newdawn.slick.Image;

public abstract class TileEntity{
	
	protected Region region;
	
	protected int id;
	protected int posX;
	protected int posY;
	
	protected String name;
	protected String displayName;

	protected TileEntity(int id, String name, String displayName, Region region, int posX, int posY) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.region = region;
		this.posX = posX;
		this.posY = posY;
		region.setTileEntity(posX, posY, this);
	}
	
	protected TileEntity(Region region, TagCompound compound){
		try {
			id = compound.getInt("id");
			name = compound.getString("name");
			displayName = compound.getString("displayName");
			posX = compound.getInt("posX");
			posY = compound.getInt("posY");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.region = region;
		region.setTileEntity(posX, posY, this);
	}
	
	public Image getImage(){
		return SpriteRegistry.getImageForName(name);
	}
	
	/**
	 * draw this entity
	 * @param mapTopX - x coordinate of the top left point of the map
	 * @param mapTopY - y coordinate of the top left point of the map
	 */
	public void draw(int mapTopX, int mapTopY){
		Image img = getImage();
		int width = img.getWidth();
		int height = img.getHeight();
		if(img != null){
			int relativeX = (mapTopX + posX * Settings.tileWidth);
			int relativeY = (mapTopY + posY * Settings.tileHeight);
			if(relativeX + width > 0 && relativeX < JMoonGame.gameContainer.getWidth() && relativeY + height > 0 && relativeY < JMoonGame.gameContainer.getHeight()){
				img.drawEmbedded(relativeX, relativeY, img.getWidth(), img.getHeight());
			}
		}
	}
	
	public abstract void update(int timeSinceLastUpdate);
	
	public abstract void interact(PlayerEntity player);
	
	/**
	 * Set the position of the entity
	 * @param x - x coodinate
	 * @param y - y coordinate
	 */
	public void setPosition(int x, int y){
		posX = x;
		posY = y;
	}
	
	public int getID(){
		return id;
	}
	
	public Region getRegion(){
		return region;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public void setDisplayName(String name){
		this.displayName = name;
	}
	
	public static String getImagePath(String modid, String name){
		return "sprites/" + modid + "/tileentity/" + name + ".png";
	}

}
