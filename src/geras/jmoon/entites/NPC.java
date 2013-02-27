package geras.jmoon.entites;

import geras.jmoon.world.Map;

import org.newdawn.slick.Game;

import de.matthiasmann.twl.model.SimpleChangableListModel;

/**
 * The NPC class
 * @author Geras
 *
 */
public class NPC extends Entity{
	
	
	protected String name;
	protected String title;

	protected NPC(SimpleChangableListModel<String> inventoryModel, String name, String title, int posX, int posY) {
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
		//TODO
	}
	
	/**
	 * obviously true :)
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
