package com.example.cocos_2d0721;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;


public class audioLayer extends CCLayer{
	
	audioLayer(){
		init();
	}

	private void init() {
		
		CCSprite cp = CCSprite.sprite("cover.jpg");
		cp.setAnchorPoint(0,0);
		cp.setPosition(0,0);
		addChild(cp);
		
		SoundEngine sd = SoundEngine.sharedEngine();
		//��һ������Ϊ���������activity���ڶ���Ϊ��Դid���������Ƿ�ѭ��
		sd.playSound(CCDirector.theApp, R.raw.abc, false);

	}
	
	
}
