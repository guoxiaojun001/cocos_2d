package com.example.cocos28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import android.view.MotionEvent;

public class Maylayer extends CCLayer{
	CCTMXTiledMap titlemap;
	CCSprite sprite;
	List<CGPoint> position = new ArrayList<CGPoint>();

	public Maylayer(){
		setIsTouchEnabled(true);
		init();
	}
	private void init() {
		titlemap = CCTMXTiledMap.tiledMap("map.tmx");
		titlemap.setAnchorPoint(0.5f,0.5f);
		titlemap.setPosition(200,150);

		parseMap();
		sprite = CCSprite.sprite("z_1_01.png");
		sprite.setFlipX(true);
		sprite.setScale(0.65f);
		sprite.setPosition(position.get(0));
		System.out.println(position);
		addChild(titlemap);
		titlemap.addChild(sprite);
		walk();
	}
	//1秒走60像素
	float spcae = 60;
	int point = 1;
	public void walk() {

		float distance = ccpDistance(sprite.getPosition(),position.get(point));
		float time = distance/spcae;
		CCMoveTo mto = CCMoveTo.action(time, position.get(point));
		CCCallFunc func = CCCallFunc.action(this, "callWalk");
		CCSequence seq = CCSequence.actions(mto, func);
		//		schedule("func",1);//public void fun(float a){}
		//		CCCallFunc 指定方法名，无参数
		sprite.runAction(seq);

		ArrayList<CCSpriteFrame> frame = new ArrayList<CCSpriteFrame>();
		for (int i = 1; i <= 7; i++) {
			CCSprite sprite = CCSprite.sprite(String.format("z_1_%02d.png",i ));
			frame.add(sprite.displayedFrame());
		}
		CCAnimation animation = CCAnimation.animation("",0.15f,frame);
		CCAnimate animate = CCAnimate.action(animation);
		CCRepeatForever repeat = CCRepeatForever.action(animate);
		sprite.runAction(repeat);
	}

	public void parseMap(){
		CCTMXObjectGroup group = titlemap.objectGroupNamed("obj");
		ArrayList<HashMap<String,String>> list = group.objects;
		for (HashMap<String,String> map : list) {
			position.add(ccp(Double.parseDouble(map.get("x"))+10,
					Double.parseDouble(map.get("y"))+20));
		}

	}
	public void callWalk(){
		sprite.stopAllActions();
		point++;
		if (point >= position.size()) {
			point = 1;
			sprite.setPosition(position.get(0));
		}
		walk();
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		// TODO Auto-generated method stub
		titlemap.touchMove(event, titlemap);
		return super.ccTouchesMoved(event);
	}

}
