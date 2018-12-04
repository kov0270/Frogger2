package com.example.antonio.frogger;

import android.graphics.Canvas;

public class FrogThread extends java.lang.Thread {
    private FroggerView view;
    private boolean running = false;

    public FrogThread(FroggerView view) {
        this.view = view;
    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
