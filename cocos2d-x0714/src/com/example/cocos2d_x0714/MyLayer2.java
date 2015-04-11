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
		//������¼�
		this.setIsTouchEnabled(true);
		//��������
		CCSprite sp = CCSprite.sprite("cover.jpg");
		//����ê�� Ϊ���½�  Ĭ��Ϊ��0.5,0.5��
		//(-1,-2)��ʾx���ƶ�1��y�ϱ���2
		sp.setAnchorPoint(0, 0);
		//����λ��
		sp.setPosition(0, 0);
//		sp.setScale(0.5);//����
//		sp.setScaleX((float) 0.5);
//		sp.setScaleY(0.5);

		CCSprite zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(100, 100);
		//x���� ���Ƿ�����
		zombie.setFlipX(true);
		//zombie.setFlipY(true);
		/**
		 * 1.��һ������Ϊ��ӵĽڵ�
		 * 2.�ڶ�����������Ҫ��  ֵԽ�� ��󻭳�
		 * 3.id Ψһ��ʾ
		 */
		this.addChild(sp,1,SP_ID);
		this.addChild(zombie,2,ZOMBIE_ID);
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// �����¼� ��Ӧԭ��Ϊ���Ͻǣ���������Ϊ���½�ԭ��
		CGPoint cgpoint = this.convertTouchToNodeSpace(event);

		CCSprite zombie = (CCSprite) getChildByTag(ZOMBIE_ID);
		System.out.println(zombie);
		if (CGRect.containsPoint(zombie.getBoundingBox(), cgpoint)) {

			removeChildByTag(SP_ID, true);
		}
		return super.ccTouchesEnded(event);
	}
}
