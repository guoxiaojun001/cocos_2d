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
		//��ȡCCDirector
		CCDirector director = CCDirector.sharedDirector();
		//���ӵײ㣬��ʼ��
		director.attachInView(view);
		//fps :frames per second,ÿ������ʾ֡������
		director.setDisplayFPS(true);//�������fps_images.pngͼƬ
		//������Ļ����
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		//������Ļ��С���̶�ֵ�������ڶ��ƽ̨ʹ��
		director.setScreenSize(480, 320);
		
//		//����
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
