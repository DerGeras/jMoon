package geras.jmoon.entites;

import geras.jmoon.items.Inventory;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.model.SimpleChangableListModel;

public abstract class Entity {
	
	private static int nextFreeEntityId = 0; //the next free entity ID (unique)
	
	protected float move_speed = 0.15f;
	
	protected int EntityId; //the unique id for this entity
	
	protected int posX; //x position on the map
	protected int posY; //y position on the map
	
	protected int width; //width of the entity
	protected int height; //height of the entity
	
	protected Map.direction direction;
	
	protected int nextX; //direction to move on the next update (-1, 0, 1)
	protected int nextY; //direction to move on the next update (-1, 0, 1)

	protected Image entityImg; //image for the entity
	
	protected Inventory inventory;
	
	/**
	 * Basic Constructor, initialize values
	 * Override this for your Entities!
	 */
	protected Entity(SimpleChangableListModel<String> inventoryModel){
		posX = posY = nextX = nextY = 0;
		EntityId = getNextFreeEntityId();
		direction = Map.direction.south;
		inventory = new Inventory(inventoryModel);
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
	public void draw(Graphics g, int mapTopX, int mapTopY){
		if(entityImg != null){
			int relativeX = mapTopX + posX - width / 2;
			int relativeY = mapTopY + posY - height / 2;
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
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public int getID(){
		return this.EntityId;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getNextX() {
		return nextX;
	}

	public void setNextX(int nextX) {
		if(nextX > 0){
			this.nextX = 1;
			this.direction = Map.direction.east;
		}
		if(nextX < 0){
			this.nextX = -1;
			this.direction = Map.direction.west;
		}
		if(nextX == 0) this.nextX = nextX;
	}

	public int getNextY() {
		return nextY;
	}

	public void setNextY(int nextY) {
		if(nextY > 0){
			this.nextY = 1;
			this.direction = Map.direction.south;
		}
		if(nextY < 0){
			this.nextY = -1;
			this.direction = Map.direction.north;
		}
		if(nextY == 0)this.nextY = 0;
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
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public void setInventory(Inventory inventory){
		this.inventory = inventory;
	}
	
}
