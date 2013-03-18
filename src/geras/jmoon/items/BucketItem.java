package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class BucketItem extends UsableItem {

	public BucketItem(int stackSize) {
		super("Bucket", Settings.maxStackSize, stackSize, 100, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//Fill from a waterhole
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
	
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.WATER_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.HOLE_VALUE);
				player.getInventory().addItem("Water Bucket", 1);
				--stackSize;
			}
		}

	}

}
