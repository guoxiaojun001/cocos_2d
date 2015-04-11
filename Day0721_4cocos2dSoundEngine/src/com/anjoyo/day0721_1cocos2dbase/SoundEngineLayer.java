package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.particlesystem.CCParticleSystemPoint;
import org.cocos2d.sound.SoundEngine;

public class SoundEngineLayer extends CCLayer {
	public SoundEngineLayer(){
		init();
	}

	private void init() {
		CCSprite bg = CCSprite.sprite("cover.jpg");
		bg.setAnchorPoint(0,0);
		bg.setScale(0.65);
		addChild(bg,-1);
		
		
		SoundEngine soundEngine = SoundEngine.sharedEngine();
		//��һ������Ϊ����Activity����
		//�ڶ�������Ϊ��Դid
		//����������Ϊ�Ƿ�ѭ��
		//���ű�����
		soundEngine.playSound(CCDirector.theApp, R.raw.abc, false);
		//������Ч��С������
//		soundEngine.playEffect(app, resId, isloop);
	}
}
