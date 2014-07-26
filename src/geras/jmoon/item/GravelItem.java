package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class GravelItem extends UsableItem {

	public GravelItem(int stackSize) {
		super("Gravel", Settings.maxStackSize, stackSize, 1);
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
		//put Gravel on gras
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
	
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.GRASS_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.GRAVEL_VALUE);
				--stackSize;
			}
		}
	}

}
