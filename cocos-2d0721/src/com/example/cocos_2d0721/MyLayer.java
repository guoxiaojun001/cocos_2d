package com.example.cocos_2d0721;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseIn;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintTo;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.graphics.Color;
import android.view.MotionEvent;

public class MyLayer extends CCLayer {
	private static final int ZOMBIE_ID = 1;
	private static final int BG_ID = 0;
	CCSprite sp;
	CCSprite zombie,zombie1;
	boolean flag = true;
	public MyLayer(){
		//������¼�
		this.setIsTouchEnabled(true);
		init();
		action();
	}

	private void init(){
		sp = CCSprite.sprite("cover.jpg");
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
		
		zombie1 = CCSprite.sprite("z_1_01.png");
		zombie1.setAnchorPoint(0.5f, 0.5f);
		zombie1.setPosition(250,50);
		//x���񣬾��Ƿ�����
		//		zombie.setFlipX(true);
		//		zombie.setFlipY(true);

		//��һ��������Ҫ��ӵĶ��󣻵ڶ�����������Ҫ��(ֵԽ��Խ������)��������������id,��֤Ψһ
		this.addChild(sp,1,BG_ID);
		this.addChild(zombie, 2, ZOMBIE_ID);
		this.addChild(zombie1, 2, 3);
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		//�����¼�����Ӧ����ԭ��Ϊ���Ͻ�(ע������Ϊ���½�Ϊԭ��),ת������
		CGPoint point = this.convertTouchToNodeSpace(event);
		// ͨ��tag�����õ���ʬ
		zombie = (CCSprite) getChildByTag(ZOMBIE_ID);
		//�ж��Ƿ����ڽ�ʬ��
		if(CGRect.containsPoint(zombie.getBoundingBox(), point)){
			if (flag) {
				this.removeChildByTag(BG_ID, false);
				flag = false;
			}else{
				this.addChild(sp);
				flag = true;
			}
		}
		return super.ccTouchesEnded(event);
	}

	private void action() {
//		moveTo();
		moveBy();
//		scaleBy();
//		rotateBy();
//		rotateTo();
//		showAndHide();
//		jumpBy();
//		bezier();
//		blink();
		frameAnimation();
//		easeIn();
//		tindTo();
//		callFunc();
	}

	private void moveBy(){
		//�ƶ�����                   ��һ������Ϊ����ʱ�䣬�ڶ��������ƶ�����
		CCMoveBy by = CCMoveBy.action(2, ccp(-20, 0));
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
		CCMoveTo to = CCMoveTo.action(1, ccp(50,255));
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
	//�Ƕ�Ϊ������ʱ����ת
	private void rotateBy(){
		//1��������ê�� ��ת360�� 
		CCRotateBy by = CCRotateBy.action(1, 360);
		//һֱִ��
		CCRepeatForever crf = CCRepeatForever.action(by);
		zombie.runAction(crf);
	}
	private void rotateTo(){
		CCRotateTo to = CCRotateTo.action(1, 360);
		zombie1.runAction(to);
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
			zombie1.runAction(hide);
			isShown = false;
		}else{
			CCShow show = CCShow.action();
			zombie1.runAction(show);
			isShown = true;
		}
	}
	
	private void jumpBy(){
		//�ڶ��������ƶ�λ�ã��������������䣬���ĸ�����Ϊ������
		CCJumpBy by = CCJumpBy.action(1.2F, ccp(100,120), 50, 2);
		CCJumpBy by2 = CCJumpBy.action(1.2f, ccp(100,-120), 50, 2);
		CCRotateBy rotate = CCRotateBy.action(1.3f, 90);
		//����ӽ����Ķ���ͬʱִ�С�
		CCSpawn spawn = CCSpawn.actions(rotate, by);
		CCSequence seq = CCSequence.actions(by2, spawn);
		CCRepeatForever crf = CCRepeatForever.action(spawn);
		
		zombie.runAction(crf);
	}
	//������
	private void bezier(){
		CCBezierConfig ccbc = new CCBezierConfig();
		ccbc.controlPoint_1 = ccp(10, 10);
		ccbc.controlPoint_1 = ccp(50, 100);
		ccbc.endPosition = ccp(100, 20);
		
		CCBezierBy ccby = CCBezierBy.action(2, ccbc);
		zombie1.runAction(ccby);
	}
	
	private void blink(){
		CCBlink blink = CCBlink.action(1, 2);
		CCRepeatForever forever = CCRepeatForever.action(blink);
		zombie1.runAction(forever);
		
	}
	
	private void frameAnimation(){
		ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
		
		for (int i = 1; i < 8; i++) {
			
			CCSprite sp = CCSprite.sprite(String.format("z_1_%02d.png",i ));
			CCSpriteFrame frame = sp.displayedFrame();
			frames.add(frame);
		}
		//��һ�������Ƕ��������ڶ����Ǽ��ʱ��
		CCAnimation anim = CCAnimation.animation("", 0.25f,frames);
		CCAnimate animate = CCAnimate.action(anim);
		
//		CCMoveBy by = CCMoveBy.action(2, ccp(-20, 0));
//		CCSpawn spawn = CCSpawn.actions(by, animate);
		
//		zombie.runAction(CCRepeatForever.action(spawn)); 
		zombie.runAction(CCRepeatForever.action(animate)); 
	}
	
	private void easeIn(){
		//�������
		CCMoveBy mob = CCMoveBy.action(4, ccp(0, 300));
		CCEaseIn cci = CCEaseIn.action(mob, 2);
		CCSequence seq = CCSequence.actions(cci, cci.reverse());
		zombie1.runAction(seq);
	}
	private void easeOut(){
		//�ȿ����
		CCMoveBy mob = CCMoveBy.action(4, ccp(0, 300));
		CCEaseIn cci = CCEaseIn.action(mob, 2);
		CCSequence seq = CCSequence.actions(cci, cci.reverse());
		zombie1.runAction(seq);
	}

	private void tindTo(){
		CCLabel label = CCLabel.labelWithString("ֲ���ս��ʬ", "", 20);
		label.setAnchorPoint(0,0);
		label.setPosition(10,250);
		this.addChild(label,5);
		
		CCTintTo cct = CCTintTo.actionWithDuration(0.6f, 255, 0, 0);
		CCTintTo back = CCTintTo.actionWithDuration(2, 0, 255, 0);
		
		CCSequence ccq = CCSequence.actions(cct, back);
		label.runAction(CCRepeatForever.action(ccq));
	}
	
	void callFunc(){
		CCCallFunc func = CCCallFunc.action(this, "callmethod");
		CCMoveTo to = CCMoveTo.action(1,ccp(500, 300));
		CCSequence seq = CCSequence.actions(to, func);
		zombie.runAction(seq);
	}
	public void callmethod(){
		System.out.println("ok!!!!");
	}
}
