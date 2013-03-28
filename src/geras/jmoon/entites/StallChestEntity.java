package geras.jmoon.entites;

import java.io.BufferedWriter;
import java.io.IOException;

import geras.jmoon.GameStates.WorldGameState;

public class StallChestEntity extends ChestEntity {

	public StallChestEntity(int posX, int posY) {
		super(posX, posY);
		WorldGameState.stall = this;
	}
	
	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"StallChestEntity\" posX=\"" + posX + "\" posY=\"" + posY + "\">");
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
