package com.example.cocos2d_x0714;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CCGLSurfaceView ccview = new CCGLSurfaceView(this);
		CCDirector director = CCDirector.sharedDirector();
		//连接  初始化
		director.attachInView(ccview);
		setContentView(ccview);
		
//		director.setAnimationInterval(1.0/60);//设置帧频
//		director.setDisplayFPS(true);//显示帧频，添加图片
		//设置屏幕方向
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		
		director.setScreenSize(480,320);//固定大小
		
		CCScene scen1 = CCScene.node();
		scen1.addChild(new MyLayer());
		
		director.runWithScene(scen1);
		setContentView(ccview);
		
	}

}
