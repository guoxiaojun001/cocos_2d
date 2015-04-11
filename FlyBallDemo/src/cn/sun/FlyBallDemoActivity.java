package cn.sun;

import java.util.ArrayList;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class FlyBallDemoActivity extends Activity {
	private final static int RATE = 10;// ��Ļ����ʵ����ı���
										// 10px��1m;����Ҫע�⣬�����Ǹ���android���е�����ȥ��������λ��ʱ��������Ҫ����������������������統�еĳ��ȣ���������������ж��塣
	private AABB worldAABB; // ����һ������ϵͳ
	private World world; // ����һ������
	private float timeStep; // ģ��ĵ�Ƶ��
	private int iterations; // ����Խ��ģ��Լ��ȷ��������Խ��
    private int[] iBobyX = {350,371,392,413,360,381,402,371,392,380};// ģ��ؿ������X����,���һ������Ϊ��������λ��
    private int[] iBodyY = {310,310,310,310,290,290,290,270,270,250};// ģ��ؿ������Y����,���һ������Ϊ��������λ��
    private int Bodynum = 0;
    private ArrayList<Body> arrBody;  //������¼�����統�д��ڵĳ��߽���ĸ���
	private MyGraphicsView myView; // �����Լ������view,�������Ƴ��������
	private Handler mHandler;
	private float density;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // ȥtitle
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȫ��

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		density = metric.density;//��õ�ǰ��Ļ�ֱ��� 320 - 480Ϊ1  480 -800Ϊ 1.5������Ҫͨ�����������滭���������

		// ���������������귶Χ,�����趨�����ޣ�����Ӧ���ǰ�����ĳ�������ģ�Ҳ����˵�����Χ���㹻��ģ�����ֻ���������Χ�ڴ�������
		worldAABB = new AABB();
		worldAABB.lowerBound.set(-100.0f, -100.0f);
		worldAABB.upperBound.set(100.0f, 100.0f);
		// end
		arrBody = new ArrayList<Body>(); //������¼���統�����еĸ���
		Vec2 gravity = new Vec2(0.0f, 10.0f); // ������������ʾ��ǰ������������򣬵�һ������Ϊˮƽ���򣬸���Ϊ��������Ϊ�ҡ��ڶ���������ʾ��ֱ����
		boolean doSleep = false; // ��ʾ �Ƿ�˯��
		world = new World(worldAABB, gravity, doSleep);// ��������
		world.setContactListener(listener);//Ϊ�������Ӽ���
		//*************************************************
        //�����±߽��Լ������ؿ�����
		createBorder(0, 320 * density, 480 * density, 10 * density);//�����±߽�
		initCheckPoint(); //�����ؿ�
		
		
        //
		myView = new MyGraphicsView(this);//MyGraphicsView���������������չʾ���Զ���View
		myView.setBackgroundColor(Color.WHITE);
		myView.density = density;
		myView.setOnTouchListener(touchListener);
		timeStep = 1.0f / 30f; // ����Ƶ��
		iterations = 10; // �������
		setContentView(myView);
		mHandler = new Handler();
		mHandler.post(update); // android��֪ʶ���Ͳ�����ʶ��
	}

	private Runnable update = new Runnable() {
		public void run() {
			world.step(timeStep, iterations); // ��ʼģ��
			myView.arrBody = arrBody;
			myView.invalidate();
			mHandler.postDelayed(update, (long) timeStep * 1000);
		}
	};

	public void createBorder(float x, float y, float half_width,
			float half_height) {
		PolygonDef shape = new PolygonDef(); // ��ʶ�������״
		shape.density = 0; // ���ø�����ܶȣ�ӦΪ����ǵױ߽磬�����ܶ���Ϊ0���൱��û�����������岻����
		shape.friction = 0.5f; // Ħ����,ѧ������ɡ�.��,���������˼��
		shape.setAsBox(800 / RATE, half_height / RATE); // ���ø������Ŀ�͸ߣ�Ҫ����android����ת�������統�еĵ�λ
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x / RATE, y / RATE); // ��������λ��
		Body body1 = world.createBody(bodyDef); // �������д����������
		body1.createShape(shape); // ������״
		body1.setMassFromShapes(); // ��������
		body1.m_userData = "border";//Ϊ�����������ƣ�����ײ��⵱�����ǿ��Ը�������������ж���ײ��Ϊ���������� ��������صĲ���
	}


	public void initCheckPoint()
	{
		for (int i = 0; i <iBobyX.length; i++) {
			PolygonDef shape = new PolygonDef();
			shape.density = 5f;
			shape.friction = 0.1f;
			shape.restitution = 0.3f;
			shape.setAsBox(10*density/RATE, 10*density/RATE);
			BodyDef bodyDef = new BodyDef();
			bodyDef.position.set(iBobyX[i]*density / RATE, iBodyY[i]*density / RATE);
			Body body = world.createBody(bodyDef);
			body.createShape(shape);
			body.setMassFromShapes();
			body.setUserData("Things"+i);
			arrBody.add(body);		
		}//��ʼ���ؿ�������Ĺؿ���㶨����һ�����飬���Ǻ����治Ҫ�ƽ�
	}
	
	public void createball(float x,float y,float radius)
	{
		PolygonDef shape = new PolygonDef();
		shape.density = 10f;
		shape.friction = 0.5f;
		shape.restitution = 0.3f;
		shape.setAsBox(10*density/RATE, 10*density/RATE);//�����������ҽ�С����Ϊ4���ͣ������Ӧ�ö���Ϊ�����..�漰������.�ʹ�����
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x / RATE, y / RATE);
		Body body = world.createBody(bodyDef);
		body.m_type = Body.e_dynamicType;
		body.createShape(shape);
		body.setMassFromShapes();
		body.setUserData("FlyBall"+Bodynum);
		Bodynum++;
	    Vec2 gravity = new Vec2(-(myView.singleshot.x - 75) * 30,-(myView.singleshot.y -260*density)*40); 
	    //�������������ǽ�Ҫ�Ӹ�С���ʼ���еĳ���������ݵ��������������������ϻ����³�������һ��ֵΪˮƽ������ֵΪ����
        //�ڶ���Ϊ��ֱ������ֵΪ����
	    body.wakeUp();//�����������
	    body.applyImpulse(gravity, body.getWorldCenter());//�����С��һ����
		/// ��δ����ǳ��Ը�����һ����
		arrBody.add(body);	//�����С����뵽����������
	}
	
	
	
	//ConstactListener����ײ����������Ϊ�������������ײ�����������巢����ײʱ����ص��������
	ContactListener listener = new ContactListener() {
		Body body1;
		Body body2;

		public void result(ContactResult point) {
			// TODO Auto-generated method stub
			// myView.hit();
		}

		public void remove(ContactPoint point) {
			// TODO Auto-generated method stub
			// myView.hit();
			// Log.v("Hit!!", "Hit!!");
		}

		public void persist(ContactPoint point) {
			// TODO Auto-generated method stub
			// myView.hit();
			// Log.v("Hit!!", "Hit!!");
		}

		public void add(ContactPoint point) {//��������ǵ�������ײ����ʱ�����ģ�������Ҫ������������д���
			// TODO Auto-generated method stub
			// myView.hit();
			Log.v("Hit!!", "Hit!!");
			//Log.v("Hit!!", "Hit!!");
			body1 = point.shape1.m_body;
			body2 = point.shape2.m_body;
			Log.v("HitBodyOne", body1.m_userData.toString());
			Log.v("HitBodyTwo", body2.m_userData.toString());		//����򵥵ļ�����һ����ײ���������ײ��������ƣ�����Ҫ��ʲô������ʵ��ʲô����Ч���������Լ����
		}
	};
	
	
	
	//�������Ǽ���Touch�¼�����ʵ�ֵ������϶�Ч��
	OnTouchListener touchListener = new OnTouchListener() {
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			//
			Log.v("x",arg1.getX()+"");
			Log.v("y",arg1.getY()+"");
			MyGraphicsView view = (MyGraphicsView)arg0;
			
			//���϶�ʱ�������������϶������꣬�������ᳬ������Ԥ��ķ�Χ
			if(arg1.getX() > 120*density)
				view.singleshot.x = (int)(100*density);
			else
				view.singleshot.x = (int)arg1.getX();
			if(arg1.getY() < 230*density)
				view.singleshot.y = (int)(230*density);
			else
			view.singleshot.y = (int)arg1.getY();
			view.invalidate();
			//end
			
			//���������ƿ����������ͷ�С��
			if(arg1.getAction() == MotionEvent.ACTION_UP)
			{
				createball(view.singleshot.x,view.singleshot.y,10*density);//���ô���С�񷽷�
				view.singleshot.x = 0;
				view.singleshot.y = 0;
				view.invalidate();
			}
			return true;
		}
	};
}