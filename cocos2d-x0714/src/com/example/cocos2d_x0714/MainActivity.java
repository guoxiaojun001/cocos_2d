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
		//����  ��ʼ��
		director.attachInView(ccview);
		setContentView(ccview);
		
//		director.setAnimationInterval(1.0/60);//����֡Ƶ
//		director.setDisplayFPS(true);//��ʾ֡Ƶ�����ͼƬ
		//������Ļ����
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		
		director.setScreenSize(480,320);//�̶���С
		
		CCScene scen1 = CCScene.node();
		scen1.addChild(new MyLayer());
		
		director.runWithScene(scen1);
		setContentView(ccview);
		
	}

}
