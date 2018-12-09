package com.example.antonio.frogger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class FroggerView extends SurfaceView {
    Context context;
    private SurfaceHolder holder;
    private FrogThread frogThread;
    private int slow = 5;
    private int fast = 10;
    boolean toStart = false;
    public static float currentX;
    public static float currentY;
    private int lifesLeft = 5;
    private int score = 0;
    private int level = 1;
    Paint paint = new Paint();


    private List<Log> Logs = new ArrayList<Log>();
    private List<Car> Cars = new ArrayList<Car>();
    int hCar = 0;
    int hLog = 0;

    public void setScore(int score)
    {
       this.score = score;
    }

    public int getScore()
    {
        return this.score;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return this.level;
    }

    private Frog frog;

    public FroggerView(Context context) {
        super(context);
        frogThread = new FrogThread(this);
        holder = getHolder();
        this.context = context;
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                frogThread.setRunning(false);
                while (retry) {
                    try {
                        frogThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                frogThread.setRunning(true);
                frogThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });


        Logs.add(createSpriteLog(R.drawable.log, 900, 180, slow, false));
        hLog = Logs.get(0).getBmp().getHeight();
        Logs.add(createSpriteLog(R.drawable.log, 900, 180 + hLog, fast, true));
        Logs.add(createSpriteLog(R.drawable.log, 450, 180 + hLog, fast, true));
        Logs.add(createSpriteLog(R.drawable.log, 0, 180 + hLog, fast, true));
        Logs.add(createSpriteLog(R.drawable.log, 450, 180 + hLog, fast, true));
        Logs.add(createSpriteLog(R.drawable.log, 0, 180 + hLog, fast, true));
        Logs.add(createSpriteLog(R.drawable.log, 900, 180 + 2*hLog, slow, false));
        Logs.add(createSpriteLog(R.drawable.log, 450, 180 + 2*hLog, slow, false));
        Logs.add(createSpriteLog(R.drawable.log, 0, 180 + 2*hLog, slow, false));

        Cars.add(createSpriteCar(R.drawable.car_right, 800, 1000, slow, false));
        Cars.add(createSpriteCar(R.drawable.car_right, 400, 1000, slow, false));
        hCar = Cars.get(0).getBmp().getHeight();
        Cars.add(createSpriteCar(R.drawable.car_left, 400, 1105, fast, true));
        Cars.add(createSpriteCar(R.drawable.car_left, 800, 1105, fast, true));

        Cars.add(createSpriteCar(R.drawable.racecar_left, 400, 705, slow, true));
        Cars.add(createSpriteCar(R.drawable.racecar_left, 800, 705, slow, true));
        Cars.add(createSpriteCar(R.drawable.racecar_right, 800, 600, fast, false));
        Cars.add(createSpriteCar(R.drawable.racecar_right, 400, 600, fast, false));

        frog = createSpriteFrog(R.drawable.frog);

    }


    private Frog createSpriteFrog(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Frog(this, bmp);
    }

    private Log createSpriteLog(int resource, int x, int y, int speed, boolean left) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Log(this, bmp, x, y, speed, left);
    }

    private Car createSpriteCar(int resource, int x, int y, int speed, boolean left) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Car(this, bmp, x, y, speed, left);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null)
        {
            if(false)
            {
                drawMap(canvas);
                drawCarLog(canvas);
            }
            else
            {
                drawMap(canvas);
                drawCarLog(canvas);
                drawScore(canvas);
                drawLevel(canvas);
                drawLifes(canvas);
                toStart = false;
                checkAllCollision();

                if (toStart)
                {
                    setLifesLeft(getLifesLeft() - 1);
                    if (getLifesLeft() == 0)
                    {
                        Intent intent = new Intent(context, GameOver.class);
                        intent.putExtra("score",score);
                        context.startActivity(intent);
                    }
                    frog.setStartPosition();
                }

                if (frog.getY() < 24)
                {
                    setLevel(getLevel() + 1);
                    setScore(getScore() + 50);
                    frog.setStartPosition();
                }
            }
        }
    }


    private void drawScore(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Score: " + getScore(), 0, 1350, paint);
    }

    private void drawLifes(Canvas canvas)
    {
        Drawable d = getResources().getDrawable(R.drawable.heart);
        for (int i = 0; i < getLifesLeft(); i++)
        {
            d.setBounds(0 + i*37,1450,35 + i*37,1485);
            d.draw(canvas);
        }
    }

    private void drawLevel(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Level: " + getLevel(), 0, 1400, paint);
    }


    public boolean onTouchEvent(MotionEvent event
    ) {
        currentX = event.getX();
        currentY = event.getY();
        frog.update();
        setScore(getScore()+2);
        return false;
    }

    private boolean carCollision(Car car)
    {
        if (abs(frog.getY() - car.getY()) <= car.getBmp().getHeight() &&
                abs(frog.getX() - car.getX()) <= car.getBmp().getWidth()) {
            return true;
        } else
            return false;
    }

    private boolean logCollision(Log log)
    {
        boolean result = false;
        if (abs(frog.getY() - log.getY()) <= log.getBmp().getHeight() &&
                abs(frog.getX() - log.getX()) <= log.getBmp().getWidth()) {
            return true;
        }
        else
            result = false;

        return result;
    }

    private void drawCarLog(Canvas canvas)
    {
        for (Car car : Cars)
        {
            car.onDraw(canvas);
        }
        for (Log log : Logs)
        {
            log.onDraw(canvas);
        }
        frog.onDraw(canvas);

    }

    private void checkAllCollision()
    {
        for (Car car : Cars)
        {
            if (frog.getY() < car.getY() + 48 && frog.getY() > car.getY() - 48)
            {
                if (carCollision(car))
                {
                    toStart = true;
                    break;
                }
                else
                {
                    toStart = false;
                }
            }
        }
        for (Log log : Logs)
        {
            if (frog.getY() <= log.getY() + 100 && frog.getY() >= log.getY() - 20)
            {
                if (logCollision(log))
                {
                    frog.updateWithLog(log);
                    toStart = false;
                    break;
                }
                else
                {
                    toStart = true;
                }
            }
        }
    }

    private int getLifesLeft()
    {
        return lifesLeft;
    }

    private void setLifesLeft(int lifesLeft)
    {
        this.lifesLeft = lifesLeft;
    }

    private void drawMap(Canvas canvas)
    {

        paint.setColor(Color.BLACK);
        canvas.drawRect(0,canvas.getHeight(),getWidth(),0 , paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,600,1080,600 + hCar*2 + 20 , paint);
        canvas.drawRect(0,1000,1080,1000 + hCar*2 + 20 , paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 180, 1080, 180 + 3*hLog, paint);
    }

}
