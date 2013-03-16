package geras.jmoon.gui;

import geras.jmoon.entites.PlayerEntity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BasicPane extends BasicGUIElement{
	
	protected int width;
	protected int height;
	
	protected boolean visible;

	protected Image backGroundImg;
	
	
	public BasicPane(BasicGUIElement parent, int relativeX, int relativeY, int width, int height, String imageFile){
		super(parent, relativeX, relativeY);
		this.width = width;
		this.height = height;
		
		try {
			backGroundImg = new Image(imageFile);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void draw(){
		if(visible){
			if(backGroundImg != null){
				backGroundImg.draw(getAbsoluteX(), getAbsoluteY(), width, height);
			}
			drawChildren();
		}
	}
	
	@Override
	public void handleInput(Input input, PlayerEntity player){
		if(visible){
			childrenInput(input, player);
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isHit(input.getAbsoluteMouseX(), input.getAbsoluteMouseY())){
				if(parent != null){
					parent.prioritise(this);
				}
				input.clearMousePressedRecord();
			}
		}
	}
	
	/**
	 * check if the coordinates lie on the pane
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public boolean isHit(int x, int y){
		int absX = getAbsoluteX();
		int absY = getAbsoluteY();
		boolean hitX = x >= absX && x <= absX + width;
		boolean hitY = y >= absY && y <= absY + height;
		return hitX && hitY;
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisibility(boolean value){
		visible = value;
	}
	
}
