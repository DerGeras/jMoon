package geras.jmoon.entity;

import geras.jmoon.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.xml.sax.Attributes;

/**
 * The NPC class
 * @author Geras
 *
 */
public abstract class NPCEntity extends LivingEntity{
	
	
	protected String name;
	protected String title;

	public NPCEntity(String name, String title, int posX, int posY) {
		super();
		this.name = name;
		this.title = title;
		this.posX = posX;
		this.posY = posY;
	}
	
	//inherited
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY, World map){
		super.draw(g, mapTopX, mapTopY, map);
		//draw title and name
		int relativeX = (int) (mapTopX + posX);
		int relativeY = (int) (mapTopY + posY);
		
		//TODO specify font
		int imageHeight = entityImg != null ? entityImg.getHeight() : height;
		g.setColor(Color.white);
		g.drawString(name, relativeX - name.length()*8 / 2, relativeY + height/2 - imageHeight - 40);
		g.drawString("<" + title + ">", relativeX - title.length()*8 / 2 - 10, relativeY + height/2 -  imageHeight - 20);
	}
	
	@Override
	public void readFromAttributes(Attributes attributes) {
		name = attributes.getValue("name");
		if(name == null){
			name = "";
		}
		title = attributes.getValue("title");
		if(title == null){
			title = "";
		}
		
		String posXS = attributes.getValue("posX");
		String posYS = attributes.getValue("posY");
		String hungerS = attributes.getValue("hunger");
		String thirstS = attributes.getValue("thirst");
		
		if(posXS != null){
			posX = Float.parseFloat(posXS);
		}
		if(posYS != null){
			posY = Float.parseFloat(posYS);
		}
		if(hungerS != null){
			hunger = Float.parseFloat(hungerS);
		}
		if(thirstS != null){
			thirst = Float.parseFloat(thirstS);
		}
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
