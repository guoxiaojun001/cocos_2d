package com.anjoyo.day0721_1cocos2dbase;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class ProgressLayer extends CCLayer {
	public ProgressLayer(){
		init();
	}
	private void init(){
		CCProgressTimer progress = CCProgressTimer.progressWithFile("healthCircle.png");
		progress.setPosition(getContentSize().getWidth()/2, getContentSize().getHeight()/2);
		this.addChild(progress, 0);
		progress.setPercentage(90);
		
		
		CCSprite bg = CCSprite.sprite("healthCircleBackground.png");
		bg.setPosition(getContentSize().getWidth()/2, getContentSize().getHeight()/2);
		this.addChild(bg, -1);
	}
}
