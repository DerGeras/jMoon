package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class WateringCanItem extends ToolItem {

	public WateringCanItem(int durability) {
		super("Watering Can", Settings.maxDurability, durability, 200);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
	
			//water dirt if possible
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.DIRT_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.WETDIRT_VALUE);
				if(--durability <= 0){
					--stackSize;
					durability = maxDurability; //reset durability for the next item
				}
			}
			
			//or fill up the can
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.WATER_VALUE){
				durability = this.maxDurability;
			}
			
			
		}
	}

}
