package geras.jmoon.items;

import geras.jmoon.entites.LivingEntity;
import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

public class BreadItem extends UsableItem {

	public BreadItem(int stackSize) {
		super("Bread", Settings.maxStackSize, stackSize, 3);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		// do nothing
	}
	
	@Override
	public void eat(LivingEntity entity){
		if(stackSize > 0 && entity.getHunger() > 3.0f){
			entity.setHunger(entity.getHunger() - 3.0f);
			stackSize--;
		}
	}

}
