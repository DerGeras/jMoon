package geras.jmoon.gui;

import geras.jmoon.entites.LivingEntity;
import geras.jmoon.entites.PlayerEntity;

public class TradePane extends BasicPane {

	private InventoryPane playerInventoryPane;
	private InventoryPane merchantInventoryPane;
	
	private Button rightButton;
	private Button leftButton;
	
	private PlayerEntity player;
	private LivingEntity merchant;
	
	public TradePane(int relativeX, int relativeY, int width, int height, PlayerEntity player, LivingEntity merchant){
		super(relativeX, relativeY, width, height, "Sprites/GUI/TradeBackground.png");
		this.visible = true;
		this.player = player;
		this.merchant = merchant;
		this.playerInventoryPane = new InventoryPane(width/4 - InventoryPane.inventoryPaneWidth / 2, 150, this.player.getInventory());
		this.merchantInventoryPane = new InventoryPane(width/4 * 3 - InventoryPane.inventoryPaneWidth / 2, 150, this.merchant.getInventory());
		this.rightButton = new Button(width / 2 - 50, height / 2 - 50, 100, 30, "Sprites/GUI/ArrowRight.png");
		this.leftButton = new Button(width / 2 - 50, height / 2 + 10, 100, 30, "Sprites/GUI/ArrowLeft.png");
		addChild(playerInventoryPane);
		addChild(merchantInventoryPane);
		addChild(rightButton);
		addChild(leftButton);
	}
	
	public void setMerchant(LivingEntity merchant){
		this.merchant = merchant;
		this.merchantInventoryPane.setInventory(merchant.getInventory());
	}
	
	
	
	

}
