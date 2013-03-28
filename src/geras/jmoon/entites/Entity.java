package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.xml.sax.Attributes;

public abstract class Entity {
	
	private static int nextFreeEntityId = 0; //the next free entity ID (unique)
	
	protected int EntityId; //the unique id for this entity
	
	protected float posX; //x position on the map
	protected float posY; //y position on the map
	
	protected int width = 32; //width of the entity
	protected int height = 32; //height of the entity

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
			if(relativeX + width > 0 && relativeX < JMoonGame.gameContainer.getWidth() && relativeY + height > 0 && relativeY < JMoonGame.gameContainer.getHeight()){
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
	 * This needs to be overwritten by the different NPCs
	 * Standart is doing nothing
	 * @param plaer - the player that interacts with the NPC
	 * @param map - current map
	 * @param game - current game (Teleporting NPCs?)
	 * @param g - graphics - might be needed for interface stuff
	 */
	public abstract void interact(PlayerEntity player, Map map, Game game, WorldGameState state);
	
	/**
	 * save to xml file
	 * each entity has to override this method
	 */
	public abstract void saveToXML(BufferedWriter out);
	
	/**
	 * read from attributes (XML Attributes)
	 * @param attributes
	 */
	public abstract void readFromAttributes(Attributes attributes);
	
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
