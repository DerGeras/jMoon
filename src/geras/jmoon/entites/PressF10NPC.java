package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.xml.sax.Attributes;

public class PressF10NPC extends NPCEntity {
	
	private static boolean pressedF10;
	private static boolean talking;
	private static int timeSincePressF10  = 0;
	private static final int helpDuration = 1000 * 30;
	
	private float initialX;
	private float initialY;

	public PressF10NPC(int posX, int posY) {
		super("Press F10", "Not crazy", posX, posY);
		setEntityImg("Sprites/Entities/Hero.png");
		initialX = posX;
		initialY = posY;
		pressedF10 = false;
		talking = false;
		move_speed /= 3;
	}

	@Override
	public void interact(PlayerEntity player, Map map, Game game,
			WorldGameState state) {
		//currently: do nothing

	}

	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY, Map map){
		super.draw(g, mapTopX, mapTopY, map);
		
		if(talking && timeSincePressF10 <= helpDuration){
			g.setColor(Color.white);
			float topLeftX = mapTopX + posX + 30;
			float topLeftY = mapTopY + posY - 64;
			
			g.drawString("WASD: Movement", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("I: Inventory", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("LeftClick: Use the current item or select an item from the inventory", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("RightClick: Interact with NPCs", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("Note that the range for using items and interactions is limited", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("Basic Planting: Use Hoe, Plant Seed, Water with the watering can", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("Use a bucket with rightclick on a cow to get milk", topLeftX, topLeftY);
			topLeftY += 15;
			g.drawString("Bye bye", topLeftX, topLeftY);
		}
	}
	
	@Override
	public void update(int timesincelastframe, Map map) {
		if(!pressedF10){
			setNextX((int)(JMoonGame.player.getPosX() - posX));
			setNextY((int)(JMoonGame.player.getPosY() - posY));
			move(timesincelastframe, map);
		}
		else{
			if(timeSincePressF10 > helpDuration){
				posX = initialX;
				posY = initialY;
				talking = false;
			}
			else{
				timeSincePressF10 += timesincelastframe;
			}
			
		}

	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"PressF10NPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\" pressedF10=\"" + pressedF10);
			out.append("\" initialX=\"" + initialX + "\" initialY=\"" + initialY + "\">");
			out.newLine();
			
			//output the inventory
			inventory.saveToXML(out);
			
			out.newLine();
			out.write("</entity>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void readFromAttributes(Attributes attributes) {
		super.readFromAttributes(attributes);
		
		String pressedF10s = attributes.getValue("pressedF10");
		String initialXs = attributes.getValue("initialX");
		String initialYs = attributes.getValue("initialY");
		
		if(pressedF10s != null){
			pressedF10 = Boolean.parseBoolean(pressedF10s);
		}
		if(initialXs != null){
			initialX = Float.parseFloat(initialXs);
		}
		if(initialYs != null){
			initialY = Float.parseFloat(initialYs);
		}
	}
	
	/**
	 * press F10!
	 */
	public static void pressF10(){
		pressedF10 = true;
		talking = true;
		timeSincePressF10 = 0;
	}

}
