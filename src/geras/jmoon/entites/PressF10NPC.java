package geras.jmoon.entites;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.world.Map;

import java.io.BufferedWriter;
import java.io.IOException;

import org.newdawn.slick.Game;

public class PressF10NPC extends NPCEntity {

	public PressF10NPC(int posX, int posY) {
		super("Press F10", "Not crazy", posX, posY);
	}

	@Override
	public void interact(PlayerEntity player, Map map, Game game,
			WorldGameState state) {
		//currently: do nothing

	}

	@Override
	public void update(int timesincelastframe, Map map) {
		//currently: do nothing

	}

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"PressF10NPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\">");
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

}
