package com.example.cocos_2d0721;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;

public class MyScene extends CCScene{
	public MyScene(){
//		init();
	}

	private void init() {

		CCSprite sprit = CCSprite.sprite("home_button.png");
		//…Ë÷√√™µ„
		sprit.setAnchorPoint(0,0);
		sprit.setPosition(200, 100);
		this.addChild(sprit);
	}
	

}
