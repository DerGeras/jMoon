package geras.jmoon.gui;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.entites.Merchant;

public class TradePane extends BasicPane {

	private class SellButton implements ButtonListener{

		@Override
		public void buttonClicked() {
			if(merchant != null){
				int itemSelected = playerInventoryPane.getSelected();
				if(itemSelected != - 1){
					merchant.sellTo(player, player.getInventory().getItem(itemSelected), 1);
				}
			}
			
		}	
	}
	
	private class BuyButton implements ButtonListener{

		@Override
		public void buttonClicked() {
			if(merchant != null){
				int itemSelected = merchantInventoryPane.getSelected();
				if(itemSelected != - 1){
					merchant.buyFrom(player, merchant.getInventory().getItem(itemSelected), 1);
				}
			}
			
		}	
	}
	
	private InventoryPane playerInventoryPane;
	private InventoryPane merchantInventoryPane;
	
	private Button rightButton;
	private Button leftButton;
	
	private PlayerEntity player;
	private Merchant merchant;
	
	
	public TradePane(int relativeX, int relativeY, int width, int height, PlayerEntity player, Merchant merchant){
		super(relativeX, relativeY, width, height, "Sprites/GUI/TradeBackground.png");
		this.visible = true;
		this.player = player;
		this.merchant = merchant;
		this.playerInventoryPane = new InventoryPane(width/4 - InventoryPane.inventoryPaneWidth / 2, 150, this.player.getInventory());
		if(merchant != null){
			this.merchantInventoryPane = new InventoryPane(width/4 * 3 - InventoryPane.inventoryPaneWidth / 2, 150, this.merchant.getInventory());
			addChild(merchantInventoryPane);
		}
		
		this.rightButton = new Button(width / 2 - 50, height / 2 - 50, 100, 30, "Sprites/GUI/ArrowRight.png");
		this.rightButton.addButtonListener(new SellButton());
		
		this.leftButton = new Button(width / 2 - 50, height / 2 + 10, 100, 30, "Sprites/GUI/ArrowLeft.png");
		this.leftButton.addButtonListener(new BuyButton());
		
		addChild(playerInventoryPane);
		addChild(rightButton);
		addChild(leftButton);
	}
	
	/**
	 * set the merchant of this pane
	 * @param merchant
	 */
	public void setMerchant(Merchant merchant){
		if(this.merchant == null){
			this.merchantInventoryPane = new InventoryPane(width/4 * 3 - InventoryPane.inventoryPaneWidth / 2, 150, merchant.getInventory());
			addChild(merchantInventoryPane);
		}
		this.merchant = merchant;
		this.merchantInventoryPane.setInventory(merchant.getInventory());
		playerInventoryPane.setSelected(-1);
		merchantInventoryPane.setSelected(-1);
	}

}
