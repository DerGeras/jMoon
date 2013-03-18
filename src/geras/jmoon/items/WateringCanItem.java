package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class WateringCanItem extends UsableItem {

	public WateringCanItem() {
		super("Watering Can", 1, 1, 100, 1);
	}

	public WateringCanItem(int stackSize) {
		super("Watering Can", 1, stackSize, 100, 1);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
	
			//water dirt if possible
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.DIRT_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.WETDIRT_VALUE);
				--durability;
			}
			
			//or fill up the can
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.WATER_VALUE){
				durability = 100;
			}
			
			
		}
	}

}
