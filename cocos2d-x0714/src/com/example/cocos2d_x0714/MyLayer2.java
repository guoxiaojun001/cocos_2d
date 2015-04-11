package com.example.cocos2d_x0714;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class MyLayer2 extends CCLayer{

	private static final int ZOMBIE_ID = 6;
	private static final int SP_ID = 1;
	public MyLayer2(){
		init();
	}

	private void init() {
		//激活触摸事件
		this.setIsTouchEnabled(true);
		//创建精灵
		CCSprite sp = CCSprite.sprite("cover.jpg");
		//设置锚点 为左下角  默认为（0.5,0.5）
		//(-1,-2)表示x右移动1，y上边移2
		sp.setAnchorPoint(0, 0);
		//设置位置
		sp.setPosition(0, 0);
//		sp.setScale(0.5);//缩放
//		sp.setScaleX((float) 0.5);
//		sp.setScaleY(0.5);

		CCSprite zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(100, 100);
		//x镜像 就是反过来
		zombie.setFlipX(true);
		//zombie.setFlipY(true);
		/**
		 * 1.第一个参数为添加的节点
		 * 2.第二个参数：重要性  值越大 最后画出
		 * 3.id 唯一标示
		 */
		this.addChild(sp,1,SP_ID);
		this.addChild(zombie,2,ZOMBIE_ID);
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// 触摸事件 对应原点为左上角，但是引擎为做下角原点
		CGPoint cgpoint = this.convertTouchToNodeSpace(event);

		CCSprite zombie = (CCSprite) getChildByTag(ZOMBIE_ID);
		System.out.println(zombie);
		if (CGRect.containsPoint(zombie.getBoundingBox(), cgpoint)) {

			removeChildByTag(SP_ID, true);
		}
		return super.ccTouchesEnded(event);
	}
}
