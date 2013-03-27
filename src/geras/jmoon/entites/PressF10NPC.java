package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;
import org.xml.sax.Attributes;

public class PressF10NPC extends NPCEntity {
	
	public static boolean pressedF10;

	public PressF10NPC(int posX, int posY) {
		super("Press F10", "Not crazy", posX, posY);
		setEntityImg("Sprites/Entities/Hero.png");
		pressedF10 = false;
		move_speed /= 3;
	}

	@Override
	public void interact(PlayerEntity player, Map map, Game game,
			WorldGameState state) {
		//currently: do nothing

	}

	@Override
	public void update(int timesincelastframe, Map map) {
		if(!pressedF10){
			setNextX((int)(JMoonGame.player.getPosX() - posX));
			setNextY((int)(JMoonGame.player.getPosY() - posY));
			move(timesincelastframe, map);
		}

	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"PressF10NPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\" pressedF10=\"" + pressedF10 + "\">");
			out.flush();
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
		
		if(pressedF10s != null){
			pressedF10 = Boolean.parseBoolean(pressedF10s);
		}
	}

}
