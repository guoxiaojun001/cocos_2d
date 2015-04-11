package com.anjoyo.day0721_1cocos2dbase;

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
		setContentView(view);
		//附属到引擎
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(view);
		director.setAnimationInterval(1.0/60);//设置帧率
		director.setDisplayFPS(true);//显示帧率，注意一定要添加fps_images.png
		director.setDeviceOrientation(CCDirector.
				kCCDeviceOrientationLandscapeLeft);//设置屏幕方向
		//固定的，必须是480,320.因为这样就可以实现在多个平台下使用
		director.setScreenSize(480, 320);
		//通过CCScene的node()获取CCScene实例
		CCScene scene = CCScene.node();
		scene.addChild(new SoundEngineLayer());
		director.runWithScene(scene);
	}
	@Override
	protected void onPause() {
		super.onPause();
		CCDirector.sharedDirector().onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
		CCDirector.sharedDirector().onResume();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		CCDirector.sharedDirector().end();
	}

}
