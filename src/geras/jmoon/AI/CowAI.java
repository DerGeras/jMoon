package geras.jmoon.AI;

import java.util.Random;

import geras.jmoon.entites.LivingEntity;

public class CowAI extends BaseAI {
	
	private Random rand = new Random();
	private int timeSinceLastMove = 0;
	private final int moveTime = 1000;
	private int lastNextX = 0;
	private int lastNextY = 0;

	@Override
	public void update(LivingEntity entity, int timeSinceLastFrame) {
		timeSinceLastMove += timeSinceLastFrame;
		if(timeSinceLastMove > moveTime){
			lastNextX = rand.nextInt(3) - 1;
			lastNextY = rand.nextInt(3) - 1;
			timeSinceLastMove -= moveTime;
		}
		entity.setNextX(lastNextX);
		entity.setNextY(lastNextY);
	}

}
