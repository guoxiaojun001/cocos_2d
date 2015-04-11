package com.anjoyo.day0721_1cocos2dbase;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseBackIn;
import org.cocos2d.actions.ease.CCEaseIn;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintTo;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCBezierConfig;

public class ActionLayer extends CCLayer {
	CCSprite zombie;
	public ActionLayer(){
		init();
		action();
	}
	private void init() {
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0.5f, 0.5f);
		zombie.setPosition(50, 150);
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
	private void action() {
//		moveBy();
		//		moveTo();
		//		scaleBy();
		//		rotateBy();
		//		rotateTo();
		//		hideAndShow();
		//		jumpBy();
		//		bezierBy();
		//		 blink();
		//		frameAnimation();
		//		easeIn();
//		tintTo();
		callFunc();
	}

	//CCMoveBy
	private void moveBy(){
		//移动偏移量
		CCMoveBy by = CCMoveBy.action(2.5f, ccp(200,0));
		//反转，和之前动作相反
		CCMoveBy back = by.reverse();
		//有序执行，执行完第一个CCAction，再执行第二个,..
		//		CCSequence seq = CCSequence.actions(by, back, by,back);

		CCSequence seq = CCSequence.actions(by, back);
		//永远执行
		CCRepeatForever repeat = CCRepeatForever.action(seq);
		zombie.runAction(repeat);
	}
	private void moveTo(){
		//移动到，指定的位置。
		CCMoveTo to = CCMoveTo.action(2, ccp(200,0));
		//而且没有reverse(),也就说调用reverse()之后返回值是null.
		//而且不能repeat
		zombie.runAction(to);
	}
	//缩放
	private void scaleBy(){
		CCScaleBy by = CCScaleBy.action(1, 2F);
		CCScaleBy back = by.reverse();
		CCSequence seq = CCSequence.actions(by, back);
		CCRepeatForever forever = CCRepeatForever.action(seq);
		zombie.runAction(forever);
	}
	//缩放
	private void scaleTo(){
		//而且没有reverse(),也就说调用reverse()之后返回值是null.
		//而且不能repeat
		CCScaleTo to = CCScaleTo.action(1, 2F);
		zombie.runAction(to);
	}
	//旋转
	private void rotateBy(){
		//顺时针为正
		CCRotateBy by = CCRotateBy.action(1, 350);
		CCRepeatForever forever = CCRepeatForever.action(by);
		zombie.runAction(forever);
	}
	private void rotateTo(){
		CCRotateTo to = CCRotateTo.action(1, 300);
		//		CCRotateTo to = CCRotateTo.action(1, 60);

		//就近原则，哪边离得近，就朝哪边转，180度为顺时针
		zombie.runAction(to);
	}

	private void hideAndShow(){
		//计时器，每隔1秒执行一次call方法
		schedule("call", 1);
	} 
	boolean isShow = false;
	public void call(float a){
		if(!isShow){
			CCShow show = CCShow.action();
			zombie.runAction(show);
			isShow = true;
		}else{
			CCHide hide = CCHide.action();
			zombie.runAction(hide);
			isShow = false;
		}
	}
	//跳跃
	private void jumpBy(){
		CCJumpBy by1 = CCJumpBy.action(1.2F, ccp(100,120), 50, 2);
		CCJumpBy by2 = CCJumpBy.action(1.2F, ccp(100,-120), 50, 2);
		CCRotateBy rotate = CCRotateBy.action(1.3F, 360);
		//同时执行（CCSequence是有序执行）
		CCSpawn spawn = CCSpawn.actions(by2, rotate);
		//停留3秒
		CCDelayTime delay = CCDelayTime.action(3);
		CCSequence seq = CCSequence.actions(by1, spawn,delay,spawn.reverse(),by1.reverse());
		CCRepeatForever forever = CCRepeatForever.action(seq);
		zombie.runAction(forever);
	}
	//贝塞尔曲线
	//抛物线
	private void bezierBy(){
		CCBezierConfig config = new CCBezierConfig();
		config.controlPoint_1 = ccp(10,10);//控制点，起始点
		config.controlPoint_2 = ccp(50,100);//制高点
		config.endPosition = ccp(100,20);//结束点
		CCBezierBy by = CCBezierBy.action(2, config);
		zombie.runAction(by);
	}
	//闪烁
	private void blink(){
		CCBlink blink = CCBlink.action(0.5f, 1);
		CCRepeatForever forever = CCRepeatForever.action(blink);
		zombie.runAction(forever);
	}
	//序列帧动作
	private void frameAnimation(){
		ArrayList<CCSpriteFrame> frames = 
				new ArrayList<CCSpriteFrame>();
		for(int i=1;i<8;i++){
			CCSprite sp = CCSprite.sprite(
					String.format("z_1_%02d.png", i));
			CCSpriteFrame frame = sp.displayedFrame();
			frames.add(frame);
		}
		//第一个参数，动画名字，不需要就直接传""
		//delay表示每个frame执行时间
		CCAnimation anim = CCAnimation.animation("", 0.25f, frames);
		CCAnimate animate = CCAnimate.action(anim);
		zombie.runAction(CCRepeatForever.action(animate));
	}
	private void easeIn(){
		CCMoveBy move = CCMoveBy.action(4, ccp(300,0));
		CCEaseIn in = CCEaseIn.action(move, 2);
		CCSequence seq = CCSequence.actions(in, in.reverse());
		zombie.runAction(CCRepeatForever.action(seq));
	}
	private void tintTo() {
		CCLabel label = CCLabel.labelWithString("植物大战僵尸", "", 20);
		label.setAnchorPoint(0,0);
		label.setPosition(10,290);
		this.addChild(label,1);
		CCTintTo tint = CCTintTo.actionWithDuration(0.6F, 255, 0, 0);
		CCTintTo back = CCTintTo.actionWithDuration(2, 0, 255, 0);
		CCSequence seq = CCSequence.actions(tint, back);
		label.runAction(CCRepeatForever.action(seq));
	}
	private void callFunc(){
		CCCallFunc func = CCCallFunc.action(this, "callMethod");
		CCMoveTo to = CCMoveTo.action(1, ccp(500,300));
		CCSequence seq = CCSequence.actions(to, func);
		zombie.runAction(seq);
	}
	public void callMethod(){
		System.out.println("ok........");
	}
}
