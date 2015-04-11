package com.example.cocos2d_x0714;

import java.util.List;

import org.cocos2d.Cocos2D;
import org.cocos2d.actions.CCActionManager;
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;

public class MyLayer extends CCLayer{

	private static final int ZOMBIE_ID = 6;
	private static final int SP_ID = 1;
	private CCSprite zombie;
	private CCSprite sp;
	public MyLayer(){
		init();
		action();
	}

	private void init() {
		//��������
		sp = CCSprite.sprite("cover.jpg");
		//����ê�� Ϊ���½�  Ĭ��Ϊ��0.5,0.5��
		//(-1,-2)��ʾx���ƶ�1��y�ϱ���2
		sp.setAnchorPoint(0, 0);
		//����λ��
		sp.setPosition(0, 0);
		sp.setScale(0.65);//����

		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(100, 100);
		//x���� ���Ƿ�����
		zombie.setFlipX(true);
		//zombie.setFlipY(true);
		//������ת�Ƕ�
		zombie.setRotation(40);
		/**
		 * 1.��һ������Ϊ��ӵĽڵ�
		 * 2.�ڶ�����������Ҫ��  ֵԽ�� ��󻭳�
		 * 3.id Ψһ��ʾ
		 */
		this.addChild(sp,1,SP_ID);
		this.addChild(zombie,2,ZOMBIE_ID);
	}
	private void action(){
//		moveBy();
//		moveTo();
//		scarleBy();
//		scarleTo();
//		roteteBy();
//		roteteTo();
//		hide_show();
		jumpBy();

	}

	private void moveBy() {
		//�ƶ����� ��һ�������� �ƶ�����ʱ�䣬�ڶ����������ƶ��˶��پ���
		CCMoveBy mby = CCMoveBy.action(2, ccp(100,50));
//		zombie.runAction(mby);
		//�ƶ���ȥ
		CCMoveBy back = mby.reverse();
		CCSequence seq = CCSequence.actions(mby, back);
		//�ظ�ִ�� seq 
		CCRepeatForever crf = CCRepeatForever.action(seq);
		zombie.runAction(crf);

	}
	
	private void moveTo() {

		//�ƶ���ĳ�� �ڶ�������ΪĿ�ĵ�
		CCMoveTo mto = CCMoveTo.action(2, ccp(0,0));
		//û�з�����û��reverse����  ����ֵΪnull
		zombie.runAction(mto);
	}
	void scarleBy(){
		
		//��һ�������ǳ���ʱ�� �ڶ���Ϊ�Ŵ���
		CCScaleBy ccb = CCScaleBy.action(1, 2);
		CCScaleBy back = ccb.reverse();
		//CCSequence����һϵ�ж�������˳��ִ��
		CCSequence seq = CCSequence.actions(ccb, back);
		CCRepeatForever cf = CCRepeatForever.action(seq);
		zombie.runAction(cf);
	}
	private void scarleTo() {
		// TODO Auto-generated method stub
		
	}
	private void roteteBy() {
		//һ������ת90��
		CCRotateBy ccr = CCRotateBy.action(1, 90);
		CCRepeatForever cf = CCRepeatForever.action(ccr);
		zombie.runAction(cf);
	}
	private void roteteTo() {
		
		CCRotateTo cct = CCRotateTo.action(1, 90);
		CCRepeatForever cf = CCRepeatForever.action(cct);
		zombie.runAction(cf);
	}
	
	private void hide_show() {
		//��һ������Ϊ������ ���÷�������һ��float����
		schedule("call",1);
	}
	boolean isshow = false;
	public void call(float a) {

		if (isshow) {
			CCHide hide = CCHide.action();
			zombie.runAction(hide);
			isshow = false;
		}else{
			CCShow show = CCShow.action();
			zombie.runAction(show);
			isshow = true;
		}
	}
	
	private void jumpBy() {

		CCJumpBy ccj1 = CCJumpBy.action(1.3f, ccp(100,120), 50, 2);
		CCJumpBy ccj2 = CCJumpBy.action(1.3f, ccp(100,-120), 50, 2);
		
		CCRotateBy rote = CCRotateBy.action(1.3f, 360);
		CCSpawn spawn = CCSpawn.actions(ccj2, rote);
		
		CCSequence seq = CCSequence.actions(rote, spawn);
		zombie.runAction(seq);
	}
	
}
