package com.example.cocos28_2;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.particlesystem.CCParticleSystemPoint;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCSlideInLTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class firstLayer extends CCLayer{
	public firstLayer() {
		setIsTouchEnabled(true);
		init();
	}

	private void init() {

		SoundEngine.sharedEngine().playSound(CCDirector.theApp, R.raw.abc, true);
		CCSprite cover = CCSprite.sprite("cover.jpg");
		cover.setScale(0.7);
		cover.setAnchorPoint(0,0);
		cover.setPosition(0,0);
		addChild(cover,-1);
		
		CCSprite sprit = CCSprite.sprite("z_1_01.png");
		sprit.setScale(0.7);
		sprit.setAnchorPoint(0,0);
		sprit.setPosition(240,200);
		addChild(sprit,0,1);
		
		CCParticleSystem ccparticle = CCParticleSystemPoint.particleWithFile("f.plist");
		ccparticle.setAnchorPoint(0,0);
		ccparticle.setPosition(300,300);
		addChild(ccparticle,0);
		
		
	}
	
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		// TODO Auto-generated method stub
		CGPoint point = convertTouchToNodeSpace(event);
		if (CGRect.containsPoint(getChildByTag(1).getBoundingBox(), point)) {
			CCScene scen = CCScene.node();
			scen.addChild(new MyLayer());
			CCSlideInLTransition tran = CCSlideInLTransition.transition(0.3f, scen);
			CCDirector.sharedDirector().replaceScene(tran);
			
		}
		return super.ccTouchesMoved(event);
	}

}
