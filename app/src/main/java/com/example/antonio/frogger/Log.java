package com.example.antonio.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Log {
    private int x = 10; // sprite coordinate
    private int y=10;
    private int speed = 10 ; // sprite x speed
    private boolean left;
    private boolean right;
    private FroggerView froggerView;  // reference to GameView
    private Bitmap bmp;         // sprite Bitmap


    public Bitmap getBmp()
    {
        return this.bmp;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean getRight()
    {
        return right;
    }

    public boolean getLeft()
    {
        return left;
    }

    public Log(FroggerView froggerView, Bitmap bmp, int x, int y, int s, boolean left)
    {
        if(left)
        {
            s = s*(-1);
        }
        this.froggerView = froggerView;
        this.bmp=bmp;
        this.x=x;
        this.y=y;
        this.left= left;
        this.right= !left;
        this.setSpeed(s);
    }


    // now we separated screen update from drawing a bitmap
    private void update()
    {
        if (right)
        {
            if (x > froggerView.getWidth()) // - bmp.getWidth() - xSpeed) {
            {
                x = -58;
            }
            else
                x = x + speed;
        }
        else if (left)
        {
            if (x < 0)
            {
                x = froggerView.getWidth() + 58;
            }
            else
                x = x + speed;
        }
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x , y, null);
    }
}
