package geras.jmoon.gui;

public class Region2D {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Region2D(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean collidesWithRegion(Region2D region){
		return collidesWithRegion(region.x, region.y, region.width, region.height);
	}
	
	public boolean collidesWithRegion(int x, int y, int width, int height){
		return (Math.abs(this.x - x) * 2 < (this.width + width)) &&
		         (Math.abs(this.y - y) * 2 < (this.height + height));
	}
	
	public boolean mergeWithRegion(Region2D region){
		return mergeWithRegion(region.x, region.y, region.width, region.height);
	}
	
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
