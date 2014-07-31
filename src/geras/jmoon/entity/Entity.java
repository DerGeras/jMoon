package geras.jmoon.entity;

import java.io.IOException;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.nbt.TagCompound;
import geras.jmoon.world.Region;
import geras.jmoon.world.World;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Entity {
	
	protected int entityID; //the unique id for this entity
	
	protected Region region; //the region this entity inhabits
	
	protected float posX = 0.0f; //x position on the map
	protected float posY = 0.0f; //y position on the map
	
	protected int width = 32; //width of the entity
	protected int height = 32; //height of the entity

	protected Image img; //image for the entity
	
	/**
	 * Basic Constructor, initialize values
	 * Override this for your Entities!
	 */
	protected Entity(int id, Region region){
		entityID = id;
	}
	
	protected Entity(Region region, TagCompound compound){
		try {
			this.posX = compound.getFloat("posX");
			this.posY = compound.getFloat("posY");
			this.entityID = compound.getInt("id");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Kill the entity and remove it from the entityList
	 */
	public void kill(){
		region.removeEntity(this);
	}
	
	/**
	 * update the entity
	 * @param timesincelastframe
	 */
	public abstract void update(int timesincelastframe, World map);
	
	/**
	 * draw this entity
	 * @param mapTopX - x coordinate of the top left point of the map
	 * @param mapTopY - y coordinate of the top left point of the map
	 */
	public void draw(Graphics g, int mapTopX, int mapTopY, World map){
		if(img != null){
			int relativeX = (int) (mapTopX + posX - img.getWidth() / 2);
			int relativeY = (int) (mapTopY + posY + (height / 2) - img.getHeight());
			if(relativeX + width > 0 && relativeX < JMoonGame.gameContainer.getWidth() && relativeY + height > 0 && relativeY < JMoonGame.gameContainer.getHeight()){
				img.drawEmbedded(relativeX, relativeY, img.getWidth(), img.getHeight());
			}
		}
	}
	
	/**
	 * Set the position of the entity
	 * @param x - x coodinate
	 * @param y - y coordinate
	 */
	public void setPosition(float x, float y){
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
	public abstract void interact(PlayerEntity player, Game game, WorldGameState state);
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public int getID(){
		return this.entityID;
	}
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public Image getEntityImg() {
		return img;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
