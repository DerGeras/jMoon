package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class FenceItem extends UsableItem {

	public FenceItem(int stackSize){
		super("Fence", Settings.maxStackSize, stackSize, 1);
	}
	
	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			
			if((fieldValue == WorldElements.GRASS_VALUE || fieldValue == WorldElements.GRAVEL_VALUE || fieldValue == WorldElements.DIRT_VALUE)
				&& (map.getField("Decoration", fieldX, fieldY) == -1) && (map.getField("Plants", fieldX, fieldY) == -1)){
				map.setField("Decoration", fieldX, fieldY, WorldElements.FENCE_VALUE);
				--stackSize;
			}
		}
	}

}
