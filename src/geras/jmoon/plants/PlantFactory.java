package geras.jmoon.plants;

import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

import org.xml.sax.Attributes;

public class PlantFactory {

	public static Plant getPlant(Attributes attributes, World map){
		Plant plant = null;
		String tileValueS = attributes.getValue("tileValue");
		int tileValue = -1;
		
		if(tileValueS != null){
			tileValue = Integer.parseInt(tileValueS);
		}
		
		int posX = Integer.parseInt(attributes.getValue("posX"));
		int posY = Integer.parseInt(attributes.getValue("posY"));
		int timeSinceLastGrowth = Integer.parseInt(attributes.getValue("timeSinceLastGrowth"));
		
		//Wheat
		if(tileValue >= WorldElements.WHEAT_MIN_VALUE && tileValue <= WorldElements.WHEAT_MAX_VALUE){
			plant = new WheatPlant(posX,posY,tileValue,map);
			plant.setTimeSinceLastGrowth(timeSinceLastGrowth);
		}
		
		//Potatoes
		if(tileValue >= WorldElements.POTATO_MIN_VALUE && tileValue <= WorldElements.POTATO_MAX_VALUE){
			plant = new PotatoPlant(posX, posY, tileValue, map);
			plant.setTimeSinceLastGrowth(timeSinceLastGrowth);
		}
		
		return plant;		
	}
	
}
