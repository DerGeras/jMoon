package geras.jmoon.gui;

import geras.jmoon.entites.PlayerEntity;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class BasicGUIElement {
	
	protected int relativeX;
	protected int relativeY;
	
	protected LinkedList<BasicGUIElement> children = new LinkedList<BasicGUIElement>();
	protected BasicGUIElement parent;
	
	public BasicGUIElement(int relativeX, int relativeY){
		super();
		this.parent = null;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
	}
	
	/**
	 * Draw this GUI Element and its children
	 */
	public void draw(Graphics g){
		drawChildren(g);
	}
	
	/**
	 * draw the children
	 */
	protected void drawChildren(Graphics g){
		for(BasicGUIElement child: children){
			child.draw(g);
		}
	}
	
	/**
	 * Handle the input, consumes anything used (or should at least)
	 * @param input - the input
	 */
	public void handleInput(Input input, PlayerEntity player){
		childrenInput(input, player);		
	}
	
	/**
	 * Handle input for Children
	 * @param input
	 */
	protected void childrenInput(Input input, PlayerEntity player){
		Iterator<BasicGUIElement> iter = children.descendingIterator();
		BasicGUIElement currChild;
		while(iter.hasNext()){
			currChild = iter.next();
			currChild.handleInput(input, player);
		}
	}
	
	/**
	 * Add a child to the GUI Element (with top priority)
	 * @param child - the child to add
	 */
	public void addChild(BasicGUIElement child){
		children.add(child);
		child.parent = this;
	}
	
	/**
	 * remove the child from the GUI Element (if existant)
	 * @param child - the child to remove
	 * @return true if the child was removed, false otherwise
	 */
	public boolean removeChild(BasicGUIElement child){
		boolean res = children.remove(child);
		if(res) child.parent = null;
		return res;
	}
	
	/**
	 * Set priority to the given child. This should only be
	 * called by the children of this element. This will
	 * also call prioritise for the parent of this node,
	 * if it exists
	 * @param child - the child to prioritise
	 */
	protected void prioritise(BasicGUIElement child){
		int childIndex = children.indexOf(child);
		for(int i = childIndex; i < children.size() - 1; i++){
			swap(children, i, i+1);
		}
		if(parent != null){
			parent.prioritise(this);
		}
	}
	
	/**
	 * swap 2 children at the specified indexes
	 * @param list
	 * @param indexA
	 * @param indexB
	 */
	protected void swap(LinkedList<BasicGUIElement> list, int indexA, int indexB){
		BasicGUIElement tmp = list.get(indexA);
		list.set(indexA, list.get(indexB));
		list.set(indexB, tmp);
	}
	
	/**
	 * get the absolute x position of this element.
	 * @return - the absolute x
	 */
	protected int getAbsoluteX() {
		if(parent != null){
			return parent.getAbsoluteX() + relativeX;
		}
		return relativeX;
	}
	
	/**
	 * get the absolute y position of this element.
	 * @return - the absolute y
	 */
	protected int getAbsoluteY() {
		if(parent != null){
			return parent.getAbsoluteY() + relativeY;
		}
		return relativeY;
	}
	
	
	
}
