package geras.jmoon.entity;

import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public abstract class LivingEntity extends Entity {
	
	protected float hunger; //Hunger Value
	protected float thirst; //Thirst Value
	
	protected float move_speed = 0.15f;
	
	protected World.direction direction;
	
	protected int nextX; //direction to move on the next update (-1, 0, 1)
	protected int nextY; //direction to move on the next update (-1, 0, 1)
	
	
	protected int[] obstacles;

	protected LivingEntity() {
		super();
		hunger = 0;
		thirst = 0;
		width = Settings.tileWidth;
		height = Settings.tileHeight;
		nextX = nextY = 0;
		direction = World.direction.south;
		obstacles = new int[2];
		obstacles[0] = WorldElements.ROCK_VALUE;
		obstacles[1] = WorldElements.FENCE_VALUE;
	}

	@Override
	public abstract void update(int timesincelastframe, World map);
	
	
	/**
	 * move the entity on the next frame
	 * depending on timesincelastframe, the move_speed and nextX/nextY
	 */
	protected void move(int timesincelastframe, World map){
		float oldX = posX;
		float oldY = posY;
		posX += timesincelastframe * move_speed * nextX;
		posY += timesincelastframe * move_speed * nextY;
		nextX = nextY = 0;
		//Collision
		int firstX = (int) ((posX - width / 3) / Settings.tileWidth);
		int firstY = (int) ((posY - width / 3) / Settings.tileHeight);
		int secondX = (int) ((posX + width / 3) / Settings.tileWidth);
		int secondY = (int) ((posY + height / 3) / Settings.tileHeight);
		
		boolean topLeftHit = isObstacle(map.getField("Decoration", firstX, firstY));
		boolean topRightHit = isObstacle(map.getField("Decoration", secondX, firstY));
		boolean BottomLeftHit = isObstacle(map.getField("Decoration", firstX, secondY));
		boolean BottomRightHit = isObstacle(map.getField("Decoration", secondX, secondY));
		
		if(topLeftHit || BottomLeftHit || topRightHit || BottomRightHit){
			posX = oldX;
			posY = oldY;
			//TODO better ideas for this one -.-
		}
	}
	
	/**
	 * helper method, is this tileValue an obstacles?
	 * @param tileValue
	 * @return
	 */
	protected boolean isObstacle(int tileValue){
		for(int i : obstacles){
			if(i == tileValue){
				return true;
			}
		}
		return false;
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public float getHunger() {
		return hunger;
	}

	/**
	 * Set hunger value
	 * @param hunger should be between 0 and 16
	 */
	public void setHunger(float hunger) {
		if(hunger < 0.0f) this.hunger = 0.0f;
		if(hunger > Settings.maxHunger) this.hunger = Settings.maxHunger;
		this.hunger = hunger;
	}
	
	public float getThirst(){
		return thirst;
	}
	
	/**
	 * Set thirst value
	 * @param thirst should be between 0 and 16
	 */
	public void setThirst(float thirst) {
		if(thirst < 0.0f) this.thirst = 0.0f;
		if(thirst > Settings.maxThirst) this.thirst = Settings.maxThirst;
		this.thirst = thirst;
	}
	
	public int getNextX() {
		return nextX;
	}

	public void setNextX(int nextX) {
		if(nextX > 0){
			this.nextX = 1;
			this.direction = World.direction.east;
		}
		if(nextX < 0){
			this.nextX = -1;
			this.direction = World.direction.west;
		}
		if(nextX == 0) this.nextX = 0;
	}

	public int getNextY() {
		return nextY;
	}

	public void setNextY(int nextY) {
		if(nextY > 0){
			this.nextY = 1;
			this.direction = World.direction.south;
		}
		if(nextY < 0){
			this.nextY = -1;
			this.direction = World.direction.north;
		}
		if(nextY == 0)this.nextY = 0;
	}

}
