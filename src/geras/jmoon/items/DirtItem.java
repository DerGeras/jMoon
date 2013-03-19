package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class DirtItem extends UsableItem {

	public DirtItem(int stackSize) {
		super("Dirt", Settings.maxStackSize, stackSize, 1);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//fill Holes with dirt
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			if(fieldValue == WorldElements.HOLE_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.DIRT_VALUE);
				stackSize--;
			}
		}

	}

}
