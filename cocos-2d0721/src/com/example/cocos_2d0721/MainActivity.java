package com.example.cocos_2d0721;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CCGLSurfaceView view = new CCGLSurfaceView(this);
		//获取CCDirector
		CCDirector director = CCDirector.sharedDirector();
		//连接底层，初始化
		director.attachInView(view);
		//fps :frames per second,每秒钟显示帧的数量
		director.setDisplayFPS(true);//必须加上fps_images.png图片
		//设置屏幕方向
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		//设置屏幕大小，固定值，可以在多个平台使用
		director.setScreenSize(480, 320);
		
//		//场景
//		director.runWithScene(new MyScene());
		
		CCScene scene1 = CCScene.node();
//		CCScene scene1 = new MyScene();
		scene1.addChild(new MyLayer());
		director.runWithScene(scene1);

		setContentView(view);
	}
	@Override
	protected void onResume() {
		super.onResume();
		CCDirector.sharedDirector().onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		CCDirector.sharedDirector().onPause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		CCDirector.sharedDirector().end();
	}
	
}
