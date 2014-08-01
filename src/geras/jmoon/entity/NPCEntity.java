package geras.jmoon.entity;

import geras.jmoon.world.Region;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * The NPC class
 * @author Geras
 *
 */
public abstract class NPCEntity extends LivingEntity{
	
	
	protected String name;
	protected String title;

	public NPCEntity(int id, Region region, String name, String title, int posX, int posY) {
		super(id, region);
		this.name = name;
		this.title = title;
		this.posX = posX;
		this.posY = posY;
	}
	
	//inherited
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY){
		super.draw(g, mapTopX, mapTopY);
		//draw title and name
		int relativeX = (int) (mapTopX + posX);
		int relativeY = (int) (mapTopY + posY);
		
		//TODO specify font
		int imageHeight = img != null ? img.getHeight() : height;
		g.setColor(Color.white);
		g.drawString(name, relativeX - name.length()*8 / 2, relativeY + height/2 - imageHeight - 40);
		g.drawString("<" + title + ">", relativeX - title.length()*8 / 2 - 10, relativeY + height/2 -  imageHeight - 20);
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
