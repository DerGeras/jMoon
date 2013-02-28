package geras.jmoon.entites;

import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;

import de.matthiasmann.twl.model.SimpleChangableListModel;

/**
 * The NPC class
 * @author Geras
 *
 */
public class NPCEntity extends Entity{
	
	
	protected String name;
	protected String title;

	protected NPCEntity(SimpleChangableListModel<String> inventoryModel, String name, String title, int posX, int posY) {
		super(inventoryModel);
		this.name = name;
		this.title = title;
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void update(int timesincelastframe, Map map) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This needs to be overwritten by the different NPCs
	 * Standart is doing nothing
	 * @param plaer - the player that interacts with the NPC
	 * @param map - current map
	 * @param game - current game (Teleporting NPCs?)
	 * @param g - graphics - might be needed for interface stuff
	 */
	public void interact(PlayerEntity player, Map map, Game game){
		//Standart: Do nothing
	}
	
	//inherited
	public void draw(Graphics g, int mapTopX, int mapTopY, Map map){
		super.draw(g, mapTopX, mapTopY, map);
		//draw title and name
		int relativeX = mapTopX + posX;
		int relativeY = mapTopY + posY;
		
		//TODO specify font
		g.drawString(name, relativeX - name.length() / 2, relativeY - height/2 - 40);
		g.drawString("<" + title + ">", relativeX - title.length() / 2 - 10, relativeY -  height/2 - 20);
	}
	
	/**
	 * obviously true :)
	 * better than thos crappy instanceof calls
	 */
	@Override
	public boolean isNPC(){
		return true;
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////	
	
	
	public String getName(){
		return name;
	}
	
	public String getTitle(){
		return title;
	}

}
