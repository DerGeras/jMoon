package geras.jmoon.plants;

import geras.jmoon.nbt.TagCompound;
import geras.jmoon.tileentity.TileEntity;
import geras.jmoon.world.Region;

import java.io.IOException;

/**
 * 
 * @author Geras
 *
 */
public abstract class Plant extends TileEntity{
	
	protected int growthInterval = 1000 * 60; //grow automatically after x ticks
	protected int timeSinceLastGrowth = 0; //time since the last growth (+accumulated delays)
	
	public Plant(int id, String name, String displayName, Region region, int x, int y){
		super(id, displayName, displayName, region, x, y);
	}
	
	public Plant(Region region, TagCompound compound){
		super(region, compound);
		try {
			timeSinceLastGrowth = compound.getInt("timeSinceLastGrowth");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * force growth by adding growth time
	 * @param increasedGrowth
	 */
	public void forceGrow(int increasedGrowth){
		timeSinceLastGrowth += increasedGrowth;
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	
	public int getGrowthInterval() {
		return growthInterval;
	}
	
	public void setGrowthInterval(int growthInterval) {
		this.growthInterval = growthInterval;
	}
	
	public int getTimeSinceLastGrowth() {
		return timeSinceLastGrowth;
	}
	
	public void setTimeSinceLastGrowth(int timeSinceLastGrowth) {
		this.timeSinceLastGrowth = timeSinceLastGrowth;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	
}
