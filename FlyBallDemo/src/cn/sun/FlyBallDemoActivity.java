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
	private final static int RATE = 10;// 屏幕到现实世界的比例
										// 10px：1m;这里要注意，当我们根据android当中的坐标去定义刚体的位置时，我们需要将坐标除以这个比例获得世界当中的长度，用这个长度来进行定义。
	private AABB worldAABB; // 创建一个坐标系统
	private World world; // 创建一个世界
	private float timeStep; // 模拟的的频率
	private int iterations; // 迭代越大，模拟约精确，但性能越低
    private int[] iBobyX = {350,371,392,413,360,381,402,371,392,380};// 模拟关卡物体的X坐标,最后一个坐标为弹弓所在位置
    private int[] iBodyY = {310,310,310,310,290,290,290,270,270,250};// 模拟关卡物体的Y坐标,最后一个坐标为弹弓所在位置
    private int Bodynum = 0;
    private ArrayList<Body> arrBody;  //用来记录该世界当中存在的除边界外的刚体
	private MyGraphicsView myView; // 我们自己定义的view,用来绘制出这个世界
	private Handler mHandler;
	private float density;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		density = metric.density;//获得当前屏幕分辨率 320 - 480为1  480 -800为 1.5，我们要通过这个来计算绘画刚体的坐标

		// 创建这个世界的坐标范围,并且设定上下限，这里应该是按世界的长度来算的，也就是说这个范围是足够大的，我们只能在这个范围内创建刚体
		worldAABB = new AABB();
		worldAABB.lowerBound.set(-100.0f, -100.0f);
		worldAABB.upperBound.set(100.0f, 100.0f);
		// end
		arrBody = new ArrayList<Body>(); //用来记录世界当中所有的刚体
		Vec2 gravity = new Vec2(0.0f, 10.0f); // 向量，用来标示当前世界的重力方向，第一个参数为水平方向，负数为做，正数为右。第二个参数表示垂直方向
		boolean doSleep = false; // 标示 是否睡眠
		world = new World(worldAABB, gravity, doSleep);// 创建世界
		world.setContactListener(listener);//为世界增加监听
		//*************************************************
        //创建下边界以及创建关卡环境
		createBorder(0, 320 * density, 480 * density, 10 * density);//创建下边界
		initCheckPoint(); //创建关卡
		
		
        //
		myView = new MyGraphicsView(this);//MyGraphicsView这个类是我们用来展示的自定义View
		myView.setBackgroundColor(Color.WHITE);
		myView.density = density;
		myView.setOnTouchListener(touchListener);
		timeStep = 1.0f / 30f; // 定义频率
		iterations = 10; // 定义迭代
		setContentView(myView);
		mHandler = new Handler();
		mHandler.post(update); // android的知识，就不做标识了
	}

	private Runnable update = new Runnable() {
		public void run() {
			world.step(timeStep, iterations); // 开始模拟
			myView.arrBody = arrBody;
			myView.invalidate();
			mHandler.postDelayed(update, (long) timeStep * 1000);
		}
	};

	public void createBorder(float x, float y, float half_width,
			float half_height) {
		PolygonDef shape = new PolygonDef(); // 标识刚体的形状
		shape.density = 0; // 设置刚体的密度，应为这个是底边界，所以密度设为0，相当于没有质量的物体不受力
		shape.friction = 0.5f; // 摩擦力,学过物理吧….恩,就是这个意思…
		shape.setAsBox(800 / RATE, half_height / RATE); // 设置刚体刚体的宽和高，要根据android坐标转换成世界当中的单位
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x / RATE, y / RATE); // 定义刚体的位置
		Body body1 = world.createBody(bodyDef); // 在世界中创建这个刚体
		body1.createShape(shape); // 刚体形状
		body1.setMassFromShapes(); // 计算质量
		body1.m_userData = "border";//为刚体增加名称，在碰撞检测当中我们可以根据这个名称来判断相撞的为哪两个物体 并进行相关的操作
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
		}//初始化关卡，这里的关卡随便定义了一个数组，不是很正规不要计较
	}
	
	public void createball(float x,float y,float radius)
	{
		PolygonDef shape = new PolygonDef();
		shape.density = 10f;
		shape.friction = 0.5f;
		shape.restitution = 0.3f;
		shape.setAsBox(10*density/RATE, 10*density/RATE);//这里我们暂且将小鸟定义为4边型，正规的应该定义为多边形..涉及到计算.就带过了
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x / RATE, y / RATE);
		Body body = world.createBody(bodyDef);
		body.m_type = Body.e_dynamicType;
		body.createShape(shape);
		body.setMassFromShapes();
		body.setUserData("FlyBall"+Bodynum);
		Bodynum++;
	    Vec2 gravity = new Vec2(-(myView.singleshot.x - 75) * 30,-(myView.singleshot.y -260*density)*40); 
	    //这里的这个向量是将要加给小鸟初始飞行的冲量，会根据弹弓拉扯后的坐标计算向上或向下冲力，第一个值为水平方向正值为向右
        //第二个为垂直方向，正值为向下
	    body.wakeUp();//唤醒这个刚体
	    body.applyImpulse(gravity, body.getWorldCenter());//给这个小鸟一个力
		/// 这段代码是尝试给刚体一个力
		arrBody.add(body);	//将这个小鸟加入到刚体链表当中
	}
	
	
	
	//ConstactListener是碰撞监听，我们为这个世界增加碰撞监听，当刚体发生碰撞时，会回调这个方法
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

		public void add(ContactPoint point) {//这个方法是当物体碰撞发生时触发的，我们主要对这个方法进行处理
			// TODO Auto-generated method stub
			// myView.hit();
			Log.v("Hit!!", "Hit!!");
			//Log.v("Hit!!", "Hit!!");
			body1 = point.shape1.m_body;
			body2 = point.shape2.m_body;
			Log.v("HitBodyOne", body1.m_userData.toString());
			Log.v("HitBodyTwo", body2.m_userData.toString());		//这里简单的监听了一下碰撞，输出了碰撞物体的名称，具体要做什么操作，实现什么动画效果，可以自己添加
		}
	};
	
	
	
	//这里我们监听Touch事件，来实现弹弓的拖动效果
	OnTouchListener touchListener = new OnTouchListener() {
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			//
			Log.v("x",arg1.getX()+"");
			Log.v("y",arg1.getY()+"");
			MyGraphicsView view = (MyGraphicsView)arg0;
			
			//当拖动时，我们来处理拖动的坐标，让他不会超出我们预设的范围
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
			
			//监听触碰移开，创建并释放小鸟
			if(arg1.getAction() == MotionEvent.ACTION_UP)
			{
				createball(view.singleshot.x,view.singleshot.y,10*density);//调用创建小鸟方法
				view.singleshot.x = 0;
				view.singleshot.y = 0;
				view.invalidate();
			}
			return true;
		}
	};
}