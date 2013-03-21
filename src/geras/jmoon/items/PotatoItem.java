package geras.jmoon.items;

import geras.jmoon.settings.Settings;

public class PotatoItem extends Item {

	public PotatoItem(int stackSize) {
		super("Potato", Settings.maxStackSize, stackSize, 2);
	}

}
