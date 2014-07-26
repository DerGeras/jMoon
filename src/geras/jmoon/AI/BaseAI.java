package geras.jmoon.AI;

import geras.jmoon.entity.LivingEntity;


public abstract class BaseAI {
	
	/**
	 * Make your next move
	 * @param entity - the entity calling this method
	 */
	public abstract void update(LivingEntity entity, int timeSinceLastUpdate);
	
}
