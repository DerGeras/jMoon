package geras.jmoon.gui;

import geras.jmoon.entites.PlayerEntity;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Input;

public class BasicGUIElement {
	
	protected int relativeX;
	protected int relativeY;
	
	protected LinkedList<BasicGUIElement> children = new LinkedList<BasicGUIElement>();
	protected BasicGUIElement parent;
	
	public BasicGUIElement(BasicGUIElement parent, int relativeX, int relativeY){
		super();
		this.parent = parent;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
	}
	
	/**
	 * Draw this GUI Element and its children
	 */
	public void draw(){
		drawChildren();
	}
	
	/**
	 * draw the children
	 */
	protected void drawChildren(){
		for(BasicGUIElement child: children){
			child.draw();
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
	}
	
	/**
	 * remove the child from the GUI Element (if existant)
	 * @param child - the child to remove
	 * @return true if the child was removed, false otherwise
	 */
	public boolean removeChild(BasicGUIElement child){
		return children.remove(child);
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
