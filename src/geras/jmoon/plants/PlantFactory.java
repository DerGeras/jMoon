package geras.jmoon.plants;

import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

import org.xml.sax.Attributes;

public class PlantFactory {

	public static Plant getPlant(Attributes attributes, Map map){
		Plant plant = null;
		String tileValueS = attributes.getValue("tileValue");
		int tileValue = -1;
		
		if(tileValueS != null){
			tileValue = Integer.parseInt(tileValueS);
		}
		
		int posX = Integer.parseInt(attributes.getValue("posX"));
		int posY = Integer.parseInt(attributes.getValue("posY"));
		int timeSinceLastGrowth = Integer.parseInt(attributes.getValue("timeSinceLastGrowth"));
		
		//Weed
		if(tileValue >= WorldElements.WEED_MIN_VALUE && tileValue <= WorldElements.WEED_MAX_VALUE){
			plant = new WeedPlant(posX,posY,tileValue,map);
			plant.setTimeSinceLastGrowth(timeSinceLastGrowth);
		}
		
		return plant;		
	}
	
}
