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
		//��ȡCCDirector
		CCDirector director = CCDirector.sharedDirector();
		//���ӵײ㣬��ʼ��
		director.attachInView(view);
		//����֡��
		//director.setAnimationInterval(1.0/90);
		//�����Ƿ���ʾ֡�ʣ���Ҫ����assetsĿ¼һ����Ϊfps_images.png
		//fps :frames per second,ÿ������ʾ֡������
		director.setDisplayFPS(true);
		//������Ļ����
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		//������Ļ��С���̶�ֵ
		director.setScreenSize(480, 320);
		CCScene scene1 = CCScene.node();
		scene1.addChild(new MyLayer());
		director.runWithScene(scene1);
		setContentView(view);
	}

}
