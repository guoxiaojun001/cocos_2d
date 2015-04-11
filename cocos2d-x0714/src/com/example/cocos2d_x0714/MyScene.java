package com.example.cocos2d_x0714;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;

public class MyScene extends CCScene{

	public  MyScene (){
		init();
		
	}

	private void init() {
		
		//创建精灵
		CCSprite sp = CCSprite.sprite("cover.jpg");
		sp.setAnchorPoint(0, 0);
		//设置位置
		sp.setPosition(100, 100);
		this.addChild(sp);
	}

}
