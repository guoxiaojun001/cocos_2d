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
		//激活触摸事件
		this.setIsTouchEnabled(true);
		init();
		action();
	}

	private void init(){
		sp = CCSprite.sprite("cover.jpg");
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
		
		zombie1 = CCSprite.sprite("z_1_01.png");
		zombie1.setAnchorPoint(0.5f, 0.5f);
		zombie1.setPosition(250,50);
		//x镜像，就是反过来
		//		zombie.setFlipX(true);
		//		zombie.setFlipY(true);

		//第一个参数：要填加的对象；第二个参数：重要性(值越大越最后绘制)；第三个参数：id,保证唯一
		this.addChild(sp,1,BG_ID);
		this.addChild(zombie, 2, ZOMBIE_ID);
		this.addChild(zombie1, 2, 3);
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		//触摸事件，对应坐标原点为左上角(注意引擎为左下角为原点),转换坐标
		CGPoint point = this.convertTouchToNodeSpace(event);
		// 通过tag可以拿到僵尸
		zombie = (CCSprite) getChildByTag(ZOMBIE_ID);
		//判断是否点击在僵尸上
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
		//移动动作                   第一个参数为持续时间，第二个参数移动多少
		CCMoveBy by = CCMoveBy.action(2, ccp(-20, 0));
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
		CCMoveTo to = CCMoveTo.action(1, ccp(50,255));
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
	//角度为正，逆时针旋转
	private void rotateBy(){
		//1秒钟绕着锚点 旋转360度 
		CCRotateBy by = CCRotateBy.action(1, 360);
		//一直执行
		CCRepeatForever crf = CCRepeatForever.action(by);
		zombie.runAction(crf);
	}
	private void rotateTo(){
		CCRotateTo to = CCRotateTo.action(1, 360);
		zombie1.runAction(to);
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
			zombie1.runAction(hide);
			isShown = false;
		}else{
			CCShow show = CCShow.action();
			zombie1.runAction(show);
			isShown = true;
		}
	}
	
	private void jumpBy(){
		//第二个参数移动位置，第三个参数回落，第四个参数为次数。
		CCJumpBy by = CCJumpBy.action(1.2F, ccp(100,120), 50, 2);
		CCJumpBy by2 = CCJumpBy.action(1.2f, ccp(100,-120), 50, 2);
		CCRotateBy rotate = CCRotateBy.action(1.3f, 90);
		//让添加进来的动作同时执行。
		CCSpawn spawn = CCSpawn.actions(rotate, by);
		CCSequence seq = CCSequence.actions(by2, spawn);
		CCRepeatForever crf = CCRepeatForever.action(spawn);
		
		zombie.runAction(crf);
	}
	//抛物线
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
		//第一个参数是动画名，第二个是间隔时间
		CCAnimation anim = CCAnimation.animation("", 0.25f,frames);
		CCAnimate animate = CCAnimate.action(anim);
		
//		CCMoveBy by = CCMoveBy.action(2, ccp(-20, 0));
//		CCSpawn spawn = CCSpawn.actions(by, animate);
		
//		zombie.runAction(CCRepeatForever.action(spawn)); 
		zombie.runAction(CCRepeatForever.action(animate)); 
	}
	
	private void easeIn(){
		//先慢后快
		CCMoveBy mob = CCMoveBy.action(4, ccp(0, 300));
		CCEaseIn cci = CCEaseIn.action(mob, 2);
		CCSequence seq = CCSequence.actions(cci, cci.reverse());
		zombie1.runAction(seq);
	}
	private void easeOut(){
		//先快后慢
		CCMoveBy mob = CCMoveBy.action(4, ccp(0, 300));
		CCEaseIn cci = CCEaseIn.action(mob, 2);
		CCSequence seq = CCSequence.actions(cci, cci.reverse());
		zombie1.runAction(seq);
	}

	private void tindTo(){
		CCLabel label = CCLabel.labelWithString("植物大战僵尸", "", 20);
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
