package geras.jmoon.AI;

import java.util.Random;

import geras.jmoon.entity.LivingEntity;

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
			lastNextX = getNextMovementModifier();
			lastNextY = getNextMovementModifier();
			timeSinceLastMove -= moveTime;
		}
		entity.setNextX(lastNextX);
		entity.setNextY(lastNextY);
	}
	
	private int getNextMovementModifier(){
		switch(rand.nextInt(10)){
		case 0: return -1;
		case 1: return 1;
		default: return 0;
		}
	}

}
