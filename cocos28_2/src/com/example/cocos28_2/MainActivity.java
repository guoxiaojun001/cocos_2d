package com.example.cocos28_2;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CCGLSurfaceView view = new CCGLSurfaceView(this);
		setContentView(view);
		//����������
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(view);
		director.setAnimationInterval(1.0/60);//����֡��
		director.setDisplayFPS(true);//��ʾ֡�ʣ�ע��һ��Ҫ���fps_images.png
		director.setDeviceOrientation(CCDirector.
				kCCDeviceOrientationLandscapeLeft);//������Ļ����
		//�̶��ģ�������480,320.��Ϊ�����Ϳ���ʵ���ڶ��ƽ̨��ʹ��
		director.setScreenSize(480, 320);
		//ͨ��CCScene��node()��ȡCCSceneʵ��
		CCScene scene = CCScene.node();
		
		scene.addChild(new firstLayer());
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
