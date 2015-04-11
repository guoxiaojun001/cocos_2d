package cn.sun;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class MyGraphicsView extends View{
	Canvas canvas;
	public ArrayList<Body> arrBody; 
	public float density;
	public Point singleshot;  //������¼�������϶�����λ��
	public Bitmap bmbox;
	public Bitmap bmbird;
	public MyGraphicsView(Context context) {
		super(context);
		singleshot = new Point();
	}
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		//����С���bitmapͼ�񣬲���ͼ�����ѹ��4��������ֻ���ڵ�һ�δ���ʱnew Bitmapͼ��һ��
		if(bmbird == null ){
			BitmapFactory.Options opt = new BitmapFactory.Options();  
			opt.inPreferredConfig = Bitmap.Config.RGB_565;   
			opt.inPurgeable = true;  
			opt.inInputShareable = true;  
			opt.inSampleSize = 4;
			bmbird = BitmapFactory.decodeResource(getResources(), R.drawable.xx,opt);
			bmbird = Bitmap.createScaledBitmap(bmbird, (int)(20*density), (int)(20*density),true);//��ͼ���С�趨Ϊ20dip
			//end
		}

		//�������ӵ�ͼ��
		if(bmbox == null)
		{
			bmbox = BitmapFactory.decodeResource(getResources(), R.drawable.xxx);
			bmbox = Bitmap.createScaledBitmap(bmbox, (int)(20*density), (int)(20*density),true);
		}

		//������������,�жϸ������Ͳ��ҷֱ���л滭
		if(arrBody != null)
		{
			for(int i = 0;i< arrBody.size();i++)
			{
				Body body = arrBody.get(i);
				Vec2 point = body.getPosition();
				if(body.getUserData().toString().substring(0,7).equals("FlyBall"))
					drawBall(point.x,point.y,10*density);//�滭С��
				else
					drawThings(point.x,point.y);//�滭����

			}
		}
		drawBorder();//�滭�߽�
		drawSlingshot();//�滭����
	}
	private void drawThings(float x,float y)
	{
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.YELLOW);
		x = x*10;//����*10����Ϊ����Ҫ��box2d��������Ļ֮��ı������л��㣬��������������Լ�����ģ������ĵ��е� private final static int RATE = 10;// ��Ļ����ʵ����ı���
		y = y*10;
		canvas.drawBitmap(bmbox, x-10*density, y-10*density, paint);
		//canvas.drawRect(x-10*density, y-10*density, x+10*density, y+10*density, paint);
	}
	private void drawBall(float x,float y,float radius)
	{

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		x = x*10;
		y = y*10;
		//canvas.drawCircle(x, y, radius, paint);
		canvas.drawBitmap(bmbird, x-10*density, y-10*density, paint);
	}
	private void drawBorder()
	{
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setAntiAlias(true);
		canvas.drawRect(0,310*density,550*density,320*density,paint);
	}
	private void drawSlingshot(){
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		canvas.drawLine(50, 260*density, 75, 285*density, paint);
		canvas.drawLine(75, 285*density, 100, 260*density, paint);
		canvas.drawLine(75, 285*density, 75, 310*density, paint);
		if(singleshot.y != 0)
		{
			paint.setColor(Color.RED);
			canvas.drawLine(50, 260*density, singleshot.x, singleshot.y, paint);
			canvas.drawLine(100, 260*density, singleshot.x, singleshot.y, paint);
			canvas.drawBitmap(bmbird, singleshot.x-10*density, singleshot.y-10*density, paint);
		}
	}
}
