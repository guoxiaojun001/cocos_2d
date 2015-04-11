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
		zombie.setFlipX(true);//x����,x����ת
//		zombie.setFlipY(true);//y����y����ת
		
		CCSprite cover = CCSprite.sprite("cover.jpg");
		cover.setAnchorPoint(0, 0);
		//��СΪ0.65
		cover.setScale(0.65);
		//int z����ʾ��Ҫ�ԣ�Խ��Խ������
		addChild(cover,0);
		this.addChild(zombie,1);		
	}
}
