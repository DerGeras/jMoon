package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class BucketItem extends UsableItem {

	public BucketItem(int stackSize) {
		super("Bucket", Settings.maxStackSize, stackSize, 50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
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
