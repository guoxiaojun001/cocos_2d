package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

public class MenuLayer extends CCLayer {
	private static final int MENU_ITEM1 = 1;
	private static final int MENU_ITEM2 = 2;
	private static final int MENU = 3;
	public MenuLayer(){
		init();
	}

	private void init() {
		CCMenu menu = CCMenu.menu();
		//menu的锚点始终为0.5,0.5,对其修改是无效的
		menu.setPosition(0,0);
		CCSprite normalSprite = CCSprite.sprite("home_button.png");
		CCSprite selectedSprite = CCSprite.sprite("ic_launcher.png");
		CCMenuItem item1 = CCMenuItemSprite.item(normalSprite, selectedSprite, this, "menuCall");
		item1.setAnchorPoint(0, 0);
		item1.setPosition(0,0);
		menu.addChild(item1, 1, MENU_ITEM1);
		
		CCSprite normalSprite2 = CCSprite.sprite("ico_2.png");
		CCMenuItem item2 = CCMenuItemSprite.item(normalSprite2, normalSprite2, this, "menuCall");
		item2.setAnchorPoint(0,0);
		CGSize size = CCDirector.sharedDirector().getWinSize();
		item2.setPosition(size.getWidth()-72,0);
		menu.addChild(item2, 2, MENU_ITEM2);
		
		this.addChild(menu, 3, MENU);
	}
	boolean showMenuItem2 = true;
	public void menuCall(Object obj){
		CCMenuItem item = (CCMenuItem) obj;
		int tag = item.getTag();
		switch (tag) {
		case MENU_ITEM1:
			if(showMenuItem2){
				this.getChildByTag(MENU).getChildByTag(MENU_ITEM2).runAction(CCHide.action());
			}else{
				this.getChildByTag(MENU).getChildByTag(MENU_ITEM2).runAction(CCShow.action());
			}
			showMenuItem2 = !showMenuItem2;
			break;
		case MENU_ITEM2:
			this.getChildByTag(MENU).removeChildByTag(MENU_ITEM1, true);
			break;
		}
	}
}
