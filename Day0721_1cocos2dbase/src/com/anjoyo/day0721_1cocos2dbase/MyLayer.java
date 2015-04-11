package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class MyLayer extends CCLayer {
	public MyLayer(){
		init();
	}

	private void init() {
		CCSprite sprite = CCSprite.sprite("home_button.png");
		//默认锚点为0.5,0.5
		//sprite.setAnchorPoint(-1,0); 负数向右或向上
		sprite.setPosition(200,200);
		this.addChild(sprite);		
	}
}
