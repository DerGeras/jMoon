package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class HoeItem extends ToolItem {
	
	public HoeItem(int durability) {
		super("Hoe", Settings.maxDurability, durability, 60);
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
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
