package geras.jmoon.plants;

import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

/**
 * 
 * @author Geras
 *
 */
public class WeedPlant extends Plant {

	public WeedPlant(int x, int y, int tileValue, Map map) {
		super(x, y, tileValue, map);
	}

	@Override
	public void update(int timeSinceLastFrame) {
		//only do something the plant isn't fully grown
		if(tileValue < WorldElements.WEED_MAX_VALUE){
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
		boolean res = tileValue < WorldElements.WEED_MAX_VALUE && map.getField("Ground", posX, posY) == WorldElements.WETDIRT_VALUE;
		if(res){
			map.setField("Plants", posX, posY, ++tileValue);
		}
		return res;
	}

}
