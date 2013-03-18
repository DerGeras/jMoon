package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.world.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;

/**
 * The NPC class
 * @author Geras
 *
 */
public abstract class NPCEntity extends LivingEntity{
	
	
	protected String name;
	protected String title;

	protected NPCEntity(String name, String title, int posX, int posY) {
		super();
		this.name = name;
		this.title = title;
		this.posX = posX;
		this.posY = posY;
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
	
	//inherited
	public void draw(Graphics g, int mapTopX, int mapTopY, Map map){
		super.draw(g, mapTopX, mapTopY, map);
		//draw title and name
		int relativeX = (int) (mapTopX + posX);
		int relativeY = (int) (mapTopY + posY);
		
		//TODO specify font
		g.setColor(Color.white);
		g.drawString(name, relativeX - name.length()*8 / 2, relativeY - height/2 - 40);
		g.drawString("<" + title + ">", relativeX - title.length()*8 / 2 - 10, relativeY -  height/2 - 20);
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
