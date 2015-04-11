package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class MyLayer extends CCLayer {
	public MyLayer(){
		init();
	}

	private void init() {
		CCSprite zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(200, 150);
		zombie.setFlipX(true);//x镜像,x方向反转
//		zombie.setFlipY(true);//y镜像，y方向反转
		
		CCSprite cover = CCSprite.sprite("cover.jpg");
		cover.setAnchorPoint(0, 0);
		//缩小为0.65
		cover.setScale(0.65);
		//int z，表示重要性，越大越最后绘制
		addChild(cover,0);
		this.addChild(zombie,1);		
	}
}
