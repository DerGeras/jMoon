package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;

public class MilkBucketItem extends UsableItem {

	public MilkBucketItem(int stackSize) {
		super("Milk Bucket", Settings.maxStackSize, stackSize, 70);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
		//Currently: do nothing

	}

}
