package com.example.cocos_2d0721;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.particlesystem.CCParticleSystemPoint;

import android.text.style.BackgroundColorSpan;

public class ccParticleSystem extends CCLayer{
	
	public ccParticleSystem(){
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		CCParticleSystem ps = CCParticleSystemPoint.particleWithFile("f.plist");
		ps.setAnchorPoint(0, 0);
		ps.setPosition(300,300);
		this.addChild(ps,1);
		
		CCSprite cp = CCSprite.sprite("cover.jpg");
		cp.setAnchorPoint(0,0);
		cp.setPosition(0,0);
		addChild(cp);
	}
	
}
