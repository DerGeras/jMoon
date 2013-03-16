package geras.jmoon.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.items.Inventory;
import geras.jmoon.items.Item;

public class InventoryPane extends BasicPane {
	
	private class Closer implements ButtonListener{
		
		private InventoryPane pane;
		
		private Closer(InventoryPane pane){
			this.pane = pane;
		}
		
		@Override
		public void buttonClicked() {
			pane.setVisibility(false);		
		}
		
	}
	
	
	//top left x and y to draw the elements from
	private static final int topLeftX = 5;
	private static final int topLeftY = 5;
	private static final int elementHeight = 20;

	private Inventory inventory;
	
	private int selected;

	public InventoryPane(int relativeX, int relativeY,
			int width, int height, String imageFile, Inventory inventory) {
		super(relativeX, relativeY, width, height, imageFile);
		this.setInventory(inventory);
		this.setSelected(-1);
		Button xButton = new Button(width - 15, -15, 32, 32, "Sprites/GUI/XButton.png");
		xButton.addButtonListener(new Closer(this));
		addChild(xButton);
	}
	
	@Override
	public void draw(Graphics g){
		if(visible){
			int absX = getAbsoluteX();
			int absY = getAbsoluteY();
			if(backGroundImg != null){
				backGroundImg.draw(absX, absY, width, height);
			}
			//draw elements
			int x = absX + topLeftX;
			int y = absY + topLeftY;
			g.setColor(Color.black);
			Item item;
			for(int i = 0; i < inventory.getSize(); i++){
				item = inventory.getItem(i);
				if(i == selected) g.setColor(Color.blue);
				g.drawString(item.toString(), x, y);
				g.setColor(Color.black);
				y += elementHeight;
			}
			//draw the children
			drawChildren(g);					
		}
	}
	
	@Override
	public void handleInput(Input input, PlayerEntity player){
		if(visible){
			childrenInput(input, player);
			int mouseX = input.getAbsoluteMouseX();
			int mouseY = input.getAbsoluteMouseY();
			if(isHit(mouseX, mouseY)){
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					if(parent != null){
						parent.prioritise(this);
					}
					mouseX -= getAbsoluteX() + topLeftX;
					mouseY -= getAbsoluteY() + topLeftY;
					if(mouseX > 0 && mouseX < this.width - topLeftY){
						setSelected(mouseY / elementHeight);
					}
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////
	//
	//			Big block of getter/setters
	//
	///////////////////////////////////////////////////////

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		if(selected < inventory.getContent().size()){
			this.selected = selected;
		}
	}

}
