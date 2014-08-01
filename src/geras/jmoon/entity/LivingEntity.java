package geras.jmoon.entity;

import geras.jmoon.reference.Settings;
import geras.jmoon.world.Region;
import geras.jmoon.world.World;

public abstract class LivingEntity extends Entity {
	
	protected float hunger; //Hunger Value
	protected float thirst; //Thirst Value
	
	protected float move_speed = 0.15f;
	
	protected World.direction direction; //which way is the entity facing
	
	protected int nextX; //direction to move on the next update (-1, 0, 1)
	protected int nextY; //direction to move on the next update (-1, 0, 1)
	
	protected boolean isFlying;

	protected LivingEntity(int id, Region region) {
		super(id, region);
		hunger = 0.0f;
		thirst = 0.0f;
		width = Settings.tileWidth;
		height = Settings.tileHeight;
		nextX = nextY = 0;
		direction = World.direction.south;
	}
	
	
	/**
	 * move the entity on the next frame
	 * depending on timesincelastframe, the move_speed and nextX/nextY
	 */
	protected void move(int timesincelastframe){
		//TODo
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
	
	public boolean isFlying(){
		return isFlying;
	}
	
	public void setFlying(boolean b){
		isFlying = b;
	}

}
