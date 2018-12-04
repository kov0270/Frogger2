package com.example.antonio.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Frog {
    private FroggerView froggerView;  // reference to GameView
    private int speed;

    public int startX=500;
    public int startY=1450;
    private int y=startY;
    private int x=startX ; // sprite coordinate
    private int distanceOfJump=24;
    public static Bitmap bmp;         // sprite Bitmap



    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }


    public void setStartPosition()
    {
        setX(startX);
        setY(startY);
    }


    public Frog(FroggerView froggerView, Bitmap bmp)
    {
        this.froggerView=froggerView;
        this.bmp=bmp;
        this.x=startX;
        this.y=startY;
    }

    public void update()
    {
        System.out.println(getX());
        if(FroggerView.currentX>900)
            if(getX()+distanceOfJump>froggerView.getWidth()-bmp.getWidth())
                return;
            else
                setX(getX()+distanceOfJump);

        else if(FroggerView.currentX<180)
            if(getX()-distanceOfJump<0)
                return;
            else
                setX(getX()-distanceOfJump);

        if(FroggerView.currentY>y && FroggerView.currentX<900 && FroggerView.currentX>180)
            if(getY()==startY)
                return;
            else
                setY(getY()+distanceOfJump);

        else if(FroggerView.currentY<y && FroggerView.currentX<900 && FroggerView.currentX>180)
            setY(getY()-distanceOfJump);
    }

    public void updateWithLog(Log log) {

        if (!log.getLeft())
        {
            if (getX() > froggerView.getWidth())
            {
                setX(-58);
            }
            else
                setX(log.getX()+(int)log.getSpeed());
        }
        else
        {
            if (getX() < 0)
            {
                setX(froggerView.getWidth() + 58);
            }
            else
                setX(log.getX()+(int)log.getSpeed());
        }
    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawBitmap(bmp, x , y, null);
    }
}
