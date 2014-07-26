package geras.jmoon.item;

import geras.jmoon.settings.Settings;

public class WheatItem extends Item {

	public WheatItem(int stackSize) {
		super("Wheat", Settings.maxStackSize, stackSize, 2);
	}

}
