package com.anjoyo.day0714_2cocos2d;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CCGLSurfaceView view = new CCGLSurfaceView(this);
		//获取CCDirector
		CCDirector director = CCDirector.sharedDirector();
		//连接底层，初始化
		director.attachInView(view);
		//设置帧率
		//director.setAnimationInterval(1.0/90);
		//设置是否显示帧率，需要放入assets目录一张名为fps_images.png
		//fps :frames per second,每秒钟显示帧的数量
		director.setDisplayFPS(true);
		//设置屏幕方向
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		//设置屏幕大小，固定值
		director.setScreenSize(480, 320);
		CCScene scene1 = CCScene.node();
		scene1.addChild(new MyLayer());
		director.runWithScene(scene1);
		setContentView(view);
	}

}
