package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class ShovelItem extends UsableItem {
	
	public ShovelItem() {
		super("Shovel" , 1, 1, 100, 100);
	}

	public ShovelItem(int stackSize) {
		super("Shovel" , 1, 1, 100, 100);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			if((fieldValue == WorldElements.GRASS_VALUE || fieldValue == WorldElements.DIRT_VALUE || fieldValue == WorldElements.WETDIRT_VALUE)
					&& map.getField("Decoration", fieldX, fieldY) == -1 && map.getField("Plants", fieldX, fieldY) == -1){
				//I am a dwarf and I'm digging a hole
				map.setField("Ground", fieldX, fieldY, WorldElements.HOLE_VALUE);
				player.getInventory().addItem("Dirt", 1);
				if(--durability <= 0){
					--stackSize;
					durability = maxDurability; //reset durability for the next item
				}
			}
			else{
				if(fieldValue == WorldElements.GRAVEL_VALUE){
					map.setField("Ground", fieldX, fieldY, WorldElements.GRASS_VALUE);
					player.getInventory().addItem("Gravel",1);
					if(--durability <= 0){
						--stackSize;
						durability = maxDurability; //reset durability for the next item
					}
				}
			}
		}

	}

}
