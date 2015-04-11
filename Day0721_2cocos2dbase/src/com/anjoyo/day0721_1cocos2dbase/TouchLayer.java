package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class TouchLayer extends CCLayer {
	CCSprite zombie;
	public TouchLayer(){
		//第一步：激活
		this.setIsTouchEnabled(true);
		init();
	}

	private void init() {
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(200, 150);
		zombie.setFlipX(true);//x镜像,x方向反转
//		zombie.setFlipY(true);//y镜像，y方向反转
		
		CCSprite cover = CCSprite.sprite("cover.jpg");
		cover.setAnchorPoint(0, 0);
		//缩小为0.65
		cover.setScale(0.65);
		//int z，表示重要性，越大越最后绘制
		//int tag，表示id，唯一的
		addChild(cover,0,1);
		this.addChild(zombie,1,2);		
	}
	//第二步，重写ccTouchesEnded方法
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		//第三步，转换坐标
		//获取，点在该Layer上的哪个点
		CGPoint point = this.convertTouchToNodeSpace(event);
		if(CGRect.containsPoint(zombie.getBoundingBox(), point)){
			/*CCSprite cover = (CCSprite) getChildByTag(1);
			this.removeChild(cover, true);//
*/			this.removeChildByTag(1, true);
			}
		return super.ccTouchesEnded(event);
	}
}
