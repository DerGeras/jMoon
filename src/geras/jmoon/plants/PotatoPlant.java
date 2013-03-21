package geras.jmoon.plants;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class PotatoPlant extends Plant {

	public PotatoPlant(int x, int y, int tileValue, Map map) {
		super(x, y, tileValue, map);
	}

	@Override
	public void update(int timeSinceLastFrame) {
		//check if the ground is still alright
		if(map.getField("Ground", posX, posY) != WorldElements.DIRT_VALUE && map.getField("Ground", posX, posY) != WorldElements.WETDIRT_VALUE){
			map.removePlant(this);
			return;
		}
		//only do something the plant isn't fully grown
		if(tileValue < WorldElements.POTATO_MAX_VALUE){
			timeSinceLastGrowth += timeSinceLastFrame;
			//time to grow?
			if(timeSinceLastGrowth > growthInterval){
				//time to grow!
				timeSinceLastGrowth -= growthInterval;
				grow();
			}
		}

	}

	@Override
	public boolean grow() {
		boolean res = tileValue < WorldElements.POTATO_MAX_VALUE && map.getField("Ground", posX, posY) == WorldElements.WETDIRT_VALUE;
		if(res){
			map.setField("Plants", posX, posY, ++tileValue);
		}
		return res;
	}

	@Override
	public void harvest(PlayerEntity player, Map map) {
		int growth = tileValue - WorldElements.POTATO_MIN_VALUE;
		if(growth >= 0){
			player.getInventory().addItem("Potato Seeds", 1);
		}
		if(growth == 5){
			player.getInventory().addItem("Potato", 5);
		}
		map.removePlant(this);
	}

}
