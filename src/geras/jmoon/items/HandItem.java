package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class HandItem extends UsableItem {

	public HandItem(){
		super("Hand", 1, 1, 0);
	}
	
	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//Currently: switch from Gras to Gravel and back
		int fieldX = x / Settings.tileWidth;
		int fieldY = y / Settings.tileHeight;
		int value = map.getField("Ground", fieldX, fieldY);
		if(value == WorldElements.GRASS_VALUE && map.getField("Decoration", fieldX, fieldY) == -1 && map.getField("Plants", fieldX, fieldY) == -1){
			map.setField("Ground", fieldX, fieldY, WorldElements.GRAVEL_VALUE);
		}
		else{
			if(map.getField("Decoration", fieldX, fieldY) != WorldElements.ROCK_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.GRASS_VALUE);
				map.setField("Decoration", fieldX, fieldY, -1);
			}
		}
	}

}
