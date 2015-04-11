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
		//设置锚点，默认为(0.5,0.5)也就是图像中心
		//如果锚点为(-1,-2)? 负数表示向正方向移动，其中1和2为自身宽高的倍数
		sp.setAnchorPoint(0, 0);//设置锚点为图片的左下角
		sp.setPosition(0, 0);//设置位置，默认值(0,0)
		//设置缩放
		sp.setScale(0.65);
		//		sp.setScaleX(sx)
		//		sp.setScaleY(sy)

		//僵尸
		zombie = CCSprite.sprite("z_1_01.png");
		zombie.setAnchorPoint(0.5f, 0.5f);
		zombie.setPosition(150,200);
		//x镜像，就是反过来
		zombie.setFlipX(true);
		//设置初始旋转角度
		zombie.setRotation(-40);

		//第一个参数：要填加的对象；第二个参数：重要性(值越大越最后绘制)；第三个参数：id,保证唯一
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
		//移动动作                   第一个参数为持续时间，第二个参数移动多少
		CCMoveBy by = CCMoveBy.action(2, ccp(100, 50));
		//移动回去
		CCMoveBy back = by.reverse();
		//有序执行动画，第一个执行完后执行第二个
		CCSequence seq = CCSequence.actions(by, back);
		//重复执行
		CCRepeatForever crf = CCRepeatForever.action(seq);
		zombie.runAction(crf);
	}
	private void moveTo(){
		//移动到，第二个参数为目的地
		CCMoveTo to = CCMoveTo.action(2, ccp(0,0));
		System.out.println(to.reverse());
		//CCMoveTo没有反方向，即to.reverse()返回null
		//CCMoveTo只能执行一次
		zombie.runAction(to);
	}
	private void scaleBy(){
		//缩放，第一个参数为持续时间，第二个参数为放大倍数（1不变）
		CCScaleBy by = CCScaleBy.action(1, 2);
		CCScaleBy back = by.reverse();
		CCSequence seq = CCSequence.actions(by, back);
		CCRepeatForever rf = CCRepeatForever.action(seq);
		zombie.runAction(rf);
	}
	private void scaleTo(){
		//缩放到，没有反方向，只能执行一次
		CCScaleTo to = CCScaleTo.action(1, 0.5F);
		zombie.runAction(to);
	}
	//角度为正，逆时针旋转
	private void rotateBy(){
		//1秒钟绕着锚点 旋转360度 
		CCRotateBy by = CCRotateBy.action(1, 360);
		//一直执行
		CCRepeatForever crf = CCRepeatForever.action(by);
		zombie.runAction(crf);
	}
	//不管正负，离得最近方向旋转。
	private void rotateTo(){
		CCRotateTo to = CCRotateTo.action(1, 340);
		zombie.runAction(to);
	}
	private void showAndHide(){
		//计时器 第一个参数 为方法名，第二个参数为时间间隔
		//第一个参数指定方法必须为public void xxx(float)
		schedule("call", 1);
	}
	boolean isShown = true;
	public void call(float a){
		if(isShown){//如果当前显示，则隐藏
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
		//第二个参数移动位置，第三个参数回落，第四个参数为次数。
		CCJumpBy by = CCJumpBy.action(1.3F, ccp(100,120), 50, 2);
		CCJumpBy by2 = CCJumpBy.action(1.3f, ccp(100,-120), 50, 2);
		CCRotateBy rotate = CCRotateBy.action(1.3f, 360);
		//让添加进来的动作同时执行。
		CCSpawn spawn = CCSpawn.actions(by2, rotate);
		
		CCSequence seq = CCSequence.actions(by, spawn);
		zombie.runAction(seq);
	}
}
