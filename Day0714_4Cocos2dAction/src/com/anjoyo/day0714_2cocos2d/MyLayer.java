package com.anjoyo.day0714_2cocos2d;

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
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class MyLayer extends CCLayer {
	private static final int ZOMBIE_ID = 1;
	private static final int BG_ID = 0;
	private CCSprite zombie;
	public MyLayer(){
		init();
		action();
	}

	private void init(){
		CCSprite sp = CCSprite.sprite("cover.jpg");
		//����ê�㣬Ĭ��Ϊ(0.5,0.5)Ҳ����ͼ������
		//���ê��Ϊ(-1,-2)? ������ʾ���������ƶ�������1��2Ϊ�����ߵı���
		sp.setAnchorPoint(0, 0);//����ê��ΪͼƬ�����½�
		sp.setPosition(0, 0);//����λ�ã�Ĭ��ֵ(0,0)
		//��������
		sp.setScale(0.65);
		//		sp.setScaleX(sx)
		//		sp.setScaleY(sy)

		//��ʬ
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0.5f, 0.5f);
		zombie.setPosition(150,200);
		//x���񣬾��Ƿ�����
		zombie.setFlipX(true);
		//���ó�ʼ��ת�Ƕ�
		zombie.setRotation(-40);

		//��һ��������Ҫ��ӵĶ��󣻵ڶ�����������Ҫ��(ֵԽ��Խ������)��������������id,��֤Ψһ
		this.addChild(sp,1,BG_ID);
		this.addChild(zombie, 2, ZOMBIE_ID);
	}
	private void action() {
//		moveBy();
//		moveTo();
//		scaleBy();
//		scaleTo();
//		rotateBy();
//		rotateTo();
//		showAndHide();
		jumpBy();
	}
	private void moveBy(){
		//�ƶ�����                   ��һ������Ϊ����ʱ�䣬�ڶ��������ƶ�����
		CCMoveBy by = CCMoveBy.action(2, ccp(100, 50));
		//�ƶ���ȥ
		CCMoveBy back = by.reverse();
		//����ִ�ж�������һ��ִ�����ִ�еڶ���
		CCSequence seq = CCSequence.actions(by, back);
		//�ظ�ִ��
		CCRepeatForever crf = CCRepeatForever.action(seq);
		zombie.runAction(crf);
	}
	private void moveTo(){
		//�ƶ������ڶ�������ΪĿ�ĵ�
		CCMoveTo to = CCMoveTo.action(2, ccp(0,0));
		System.out.println(to.reverse());
		//CCMoveToû�з����򣬼�to.reverse()����null
		//CCMoveToֻ��ִ��һ��
		zombie.runAction(to);
	}
	private void scaleBy(){
		//���ţ���һ������Ϊ����ʱ�䣬�ڶ�������Ϊ�Ŵ�����1���䣩
		CCScaleBy by = CCScaleBy.action(1, 2);
		CCScaleBy back = by.reverse();
		CCSequence seq = CCSequence.actions(by, back);
		CCRepeatForever rf = CCRepeatForever.action(seq);
		zombie.runAction(rf);
	}
	private void scaleTo(){
		//���ŵ���û�з�����ֻ��ִ��һ��
		CCScaleTo to = CCScaleTo.action(1, 0.5F);
		zombie.runAction(to);
	}
	//�Ƕ�Ϊ������ʱ����ת
	private void rotateBy(){
		//1��������ê�� ��ת360�� 
		CCRotateBy by = CCRotateBy.action(1, 360);
		//һֱִ��
		CCRepeatForever crf = CCRepeatForever.action(by);
		zombie.runAction(crf);
	}
	//����������������������ת��
	private void rotateTo(){
		CCRotateTo to = CCRotateTo.action(1, 340);
		zombie.runAction(to);
	}
	private void showAndHide(){
		//��ʱ�� ��һ������ Ϊ���������ڶ�������Ϊʱ����
		//��һ������ָ����������Ϊpublic void xxx(float)
		schedule("call", 1);
	}
	boolean isShown = true;
	public void call(float a){
		if(isShown){//�����ǰ��ʾ��������
			CCHide hide = CCHide.action();
			zombie.runAction(hide);
			isShown = false;
		}else{
			CCShow show = CCShow.action();
			zombie.runAction(show);
			isShown = true;
		}
	}
	
	private void jumpBy(){
		//�ڶ��������ƶ�λ�ã��������������䣬���ĸ�����Ϊ������
		CCJumpBy by = CCJumpBy.action(1.3F, ccp(100,120), 50, 2);
		CCJumpBy by2 = CCJumpBy.action(1.3f, ccp(100,-120), 50, 2);
		CCRotateBy rotate = CCRotateBy.action(1.3f, 360);
		//����ӽ����Ķ���ͬʱִ�С�
		CCSpawn spawn = CCSpawn.actions(by2, rotate);
		
		CCSequence seq = CCSequence.actions(by, spawn);
		zombie.runAction(seq);
	}
}
