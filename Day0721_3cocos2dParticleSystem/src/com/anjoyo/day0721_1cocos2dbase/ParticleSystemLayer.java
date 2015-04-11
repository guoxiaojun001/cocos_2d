package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.particlesystem.CCParticleSystemPoint;

public class ParticleSystemLayer extends CCLayer {
	public ParticleSystemLayer(){
		init();
	}

	private void init() {
		CCParticleSystem ps = CCParticleSystemPoint.particleWithFile("f.plist");
		ps.setAnchorPoint(0,0);
		ps.setPosition(300,300);
		addChild(ps,1000);
		
		CCSprite bg = CCSprite.sprite("cover.jpg");
		bg.setAnchorPoint(0,0);
		bg.setScale(0.65);
		addChild(bg,-1);
	}
}
