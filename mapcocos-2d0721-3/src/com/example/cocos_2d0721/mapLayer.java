package com.example.cocos_2d0721;

import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCSprite;

public class mapLayer extends CCLayer{

	private static int menutag = 3;
	private static int item01 = 1;
	private static int item02 = 2;

	public mapLayer(){

		init();
	}

	private void init(){
		CCMenu menu = CCMenu.menu();
		menu.setPosition(0,0);

		CCSprite normal = CCSprite.sprite("home_button.png");
		CCSprite selenormal = CCSprite.sprite("ic_launcher.png");

		CCMenuItem item1 = CCMenuItemSprite.item(normal, selenormal);
		item1.setAnchorPoint(0,0);
		item1.setPosition(0,0);
		menu.addChild(item1,1,item01);

		CCSprite normal2 = CCSprite.sprite("ico_2.png");
		CCSprite selenormal2 = CCSprite.sprite("ic_launcher.png");

		CCMenuItem item2 = CCMenuItemSprite.item(normal2, selenormal2);
		item1.setAnchorPoint(0,0);
		item1.setPosition(0,0);
		menu.addChild(item2,1,item02);

		this.addChild(menu,3,menutag);
	}

	private boolean showMenuItem2 = true;
	private void menuCall(Object obj){

		CCMenuItem item = (CCMenuItem) obj;
		int tag = item.getTag();

		switch (tag) {
		case 1:
			this.getChildByTag(menutag).getChildByTag(item02).runAction(CCHide.action());
			this.getChildByTag(menutag).getChildByTag(item01).runAction(CCShow.action());
			break;
		case 2:
			this.getChildByTag(menutag).getChildByTag(item02).runAction(CCShow.action());
			this.getChildByTag(menutag).getChildByTag(item01).runAction(CCHide.action());
			break;
		default:
			break;
		}

	}

}
