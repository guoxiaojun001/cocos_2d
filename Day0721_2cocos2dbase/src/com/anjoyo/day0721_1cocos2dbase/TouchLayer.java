package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class TouchLayer extends CCLayer {
	CCSprite zombie;
	public TouchLayer(){
		//��һ��������
		this.setIsTouchEnabled(true);
		init();
	}

	private void init() {
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(200, 150);
		zombie.setFlipX(true);//x����,x����ת
//		zombie.setFlipY(true);//y����y����ת
		
		CCSprite cover = CCSprite.sprite("cover.jpg");
		cover.setAnchorPoint(0, 0);
		//��СΪ0.65
		cover.setScale(0.65);
		//int z����ʾ��Ҫ�ԣ�Խ��Խ������
		//int tag����ʾid��Ψһ��
		addChild(cover,0,1);
		this.addChild(zombie,1,2);		
	}
	//�ڶ�������дccTouchesEnded����
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		//��������ת������
		//��ȡ�����ڸ�Layer�ϵ��ĸ���
		CGPoint point = this.convertTouchToNodeSpace(event);
		if(CGRect.containsPoint(zombie.getBoundingBox(), point)){
			/*CCSprite cover = (CCSprite) getChildByTag(1);
			this.removeChild(cover, true);//
*/			this.removeChildByTag(1, true);
			}
		return super.ccTouchesEnded(event);
	}
}
