package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

public class SickelItem extends ToolItem {
	
	public SickelItem(int durability) {
		super("Sickel", Settings.maxDurability, durability, 60);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			
			if(map.getField("Plants", fieldX, fieldY) != -1){
				map.getPlant(fieldX, fieldY).harvest(player, map);
				if(--durability <= 0){
					--stackSize;
					durability = maxDurability; //reset durability for the next item
				}
			}
		}

	}

}
