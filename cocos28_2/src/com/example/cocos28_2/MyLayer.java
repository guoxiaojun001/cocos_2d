package com.example.cocos28_2;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class MyLayer  extends CCLayer {
	CCSprite sp;
	CCSprite zombie,zombie1;
	boolean flag = true;
	private static final int ZOMBIE_ID = 1;
	private static final int BG_ID = 0;

	public MyLayer(){

		init();
	}
	
	private void init(){
		sp = CCSprite.sprite("cover.jpg");
		//设置锚点，默认为(0.5,0.5)也就是图像中心
		//如果锚点为(-1,-2)? 负数表示向正方向移动，其中1和2为自身宽高的倍数
		sp.setAnchorPoint(0, 0);//设置锚点为图片的左下角
		sp.setPosition(0, 0);//设置位置，默认值(0,0)
		//设置缩放
		sp.setScale(0.65);
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0.5f, 0.5f);
		zombie.setPosition(150,200);
		
		zombie1 = CCSprite.sprite("z_1_01.png");
		zombie1.setAnchorPoint(0.5f, 0.5f);
		zombie1.setPosition(250,50);
		
		this.addChild(sp,1,BG_ID);
		this.addChild(zombie, 2, ZOMBIE_ID);
	}
	
}
