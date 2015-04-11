//package com.example.cocos_2d0721;
//
//import org.cocos2d.actions.CCProgressTimer;
//import org.cocos2d.nodes.CCSprite;
//
//public class progressLayer {
//	
//	progressLayer(){
//		init();
//	}
//
//	private void init() {
//
//		CCProgressTimer progress = CCProgressTimer.progressWithFile("healthCircle.png");
//		progress.setPosition(getContentSize().getWidth()/2, getContentSize().getHeight()/2);
//		
//		this.addChild(progress,0);
//		progress.setPercentage(90);
//		
//		CCSprite bg = CCSprite.sprite("healthCircleBackground.png");
//		bg.setPosition(getContentSize().getWidth()/2, getContentSize().getHeight()/2);
//		this.addChild(bg,0);
//	}
//	
//	
//}
