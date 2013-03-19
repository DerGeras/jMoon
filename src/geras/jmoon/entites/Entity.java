package geras.jmoon.entites;

import java.io.BufferedWriter;

import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Entity {
	
	private static int nextFreeEntityId = 0; //the next free entity ID (unique)
	
	protected int EntityId; //the unique id for this entity
	
	protected float posX; //x position on the map
	protected float posY; //y position on the map
	
	protected int width; //width of the entity
	protected int height; //height of the entity

	protected Image entityImg; //image for the entity
	
	/**
	 * Basic Constructor, initialize values
	 * Override this for your Entities!
	 */
	protected Entity(){
		posX = posY = 0;
		EntityId = getNextFreeEntityId();
	}
	
	/**
	 * Kill the entity and remove it from the entityList
	 */
	public void kill(Map map){
		map.entityList.remove(this);
	}

	/**
	 * 
	 * @return the next free entity id (unique)
	 */
	public static int getNextFreeEntityId(){
		return nextFreeEntityId++;
	}
	
	/**
	 * update the entity
	 * @param timesincelastframe
	 */
	public abstract void update(int timesincelastframe, Map map);
	
	/**
	 * draw this entity
	 * @param mapTopX - x coordinate of the top left point of the map
	 * @param mapTopY - y coordinate of the top left point of the map
	 */
	public void draw(Graphics g, int mapTopX, int mapTopY, Map map){
		if(entityImg != null){
			int relativeX = (int) (mapTopX + posX - entityImg.getWidth() / 2);
			int relativeY = (int) (mapTopY + posY + (height / 2) - entityImg.getHeight());
			if(relativeX + width > 0 && relativeX < Settings.resolutionX && relativeY + height > 0 && relativeY < Settings.resolutionY){
				entityImg.draw(relativeX, relativeY);
			}
		}
	}
	
	/**
	 * Set the position of the entity
	 * @param x - x coodinate
	 * @param y - y coordinate
	 */
	public void setPosition(int x, int y){
		posX = x;
		posY = y;
	}
	
	/**
	 * Quite obvious, but better than some instanceof checks
	 * @return false, since this isn't a particular NPC
	 */
	public boolean isNPC(){
		return false;
	}
	
	/**
	 * save to xml file
	 * each entity has to override this method
	 */
	public abstract void saveToXML(BufferedWriter out);
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public int getID(){
		return this.EntityId;
	}
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public Image getEntityImg() {
		return entityImg;
	}


	public void setEntityImg(Image playerImg) {
		this.entityImg = playerImg;
	}
	
	public void setEntityImg(String fileName) {
		try {
			this.entityImg = new Image(fileName);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
