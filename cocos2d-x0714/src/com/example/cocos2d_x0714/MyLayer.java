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
		//创建精灵
		sp = CCSprite.sprite("cover.jpg");
		//设置锚点 为左下角  默认为（0.5,0.5）
		//(-1,-2)表示x右移动1，y上边移2
		sp.setAnchorPoint(0, 0);
		//设置位置
		sp.setPosition(0, 0);
		sp.setScale(0.65);//缩放

		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0, 0);
		zombie.setPosition(100, 100);
		//x镜像 就是反过来
		zombie.setFlipX(true);
		//zombie.setFlipY(true);
		//设置旋转角度
		zombie.setRotation(40);
		/**
		 * 1.第一个参数为添加的节点
		 * 2.第二个参数：重要性  值越大 最后画出
		 * 3.id 唯一标示
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
		//移动动画 第一个参数： 移动持续时间，第二个参数：移动了多少距离
		CCMoveBy mby = CCMoveBy.action(2, ccp(100,50));
//		zombie.runAction(mby);
		//移动回去
		CCMoveBy back = mby.reverse();
		CCSequence seq = CCSequence.actions(mby, back);
		//重复执行 seq 
		CCRepeatForever crf = CCRepeatForever.action(seq);
		zombie.runAction(crf);

	}
	
	private void moveTo() {

		//移动到某处 第二个参数为目的地
		CCMoveTo mto = CCMoveTo.action(2, ccp(0,0));
		//没有反方向，没有reverse方法  返回值为null
		zombie.runAction(mto);
	}
	void scarleBy(){
		
		//第一个参数是持续时间 第二个为放大倍数
		CCScaleBy ccb = CCScaleBy.action(1, 2);
		CCScaleBy back = ccb.reverse();
		//CCSequence设置一系列动作按照顺序执行
		CCSequence seq = CCSequence.actions(ccb, back);
		CCRepeatForever cf = CCRepeatForever.action(seq);
		zombie.runAction(cf);
	}
	private void scarleTo() {
		// TODO Auto-generated method stub
		
	}
	private void roteteBy() {
		//一秒钟旋转90度
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
		//第一个参数为方法名 ，该方法带有一个float参数
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
