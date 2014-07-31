package geras.jmoon.gui;

public class Box {

	public int x;
	public int y;
	public int width;
	public int height;
	
	public Box(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @param region
	 * @return true if the regions collide
	 */
	public boolean collidesWithRegion(Box region){
		return collidesWithRegion(region.x, region.y, region.width, region.height);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return true if the regions collide
	 */
	public boolean collidesWithRegion(int x, int y, int width, int height){
		return (Math.abs(this.x - x) * 2 < (this.width + width)) &&
		         (Math.abs(this.y - y) * 2 < (this.height + height));
	}
	
	/**
	 * merge with a region
	 * @param region
	 * @return  true if region has been changed
	 */
	public boolean mergeWithRegion(Box region){
		return mergeWithRegion(region.x, region.y, region.width, region.height);
	}
	
	/**
	 * merge with a region
	 * @param xs
	 * @param ys
	 * @param widths
	 * @param heights
	 * @return true if the region has been changed
	 */
	public boolean mergeWithRegion(int xs, int ys, int widths, int heights){
		if(y == ys && height == heights){
			if(xs == x + width){ //append on the right
				width += widths;
				return true;
			}
			if(xs + widths == x){ //append on the left
				width = width + widths;
				x = xs;
				return true;
			}
		}
		if(x == xs && width == widths){
			if(ys == y + height){ //append below
				height += heights;
				return true;
			}
			if(ys + heights == y){ //append on top
				height += heights;
				y = ys;
				return true;
			}
		}
		return false;
	}
	
}
