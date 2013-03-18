package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class HoeItem extends UsableItem {

	public HoeItem(){
		super("Hoe", 1, 1, 100, 1);
	}
	
	public HoeItem(int stackSize) {
		super("Hoe", 1, stackSize, 100, 1);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//Make dirt out of Gras
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			if(map.getField("Decoration", fieldX, fieldY) == -1 && map.getField("Ground", fieldX, fieldY) == WorldElements.GRASS_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.DIRT_VALUE);
				if(--durability <= 0){
					--stackSize;
					durability = maxDurability; //reset durability for the next item
				}
			}
		}
	}

}
