package com.example.lenovo.bomberman.UI;


import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class RepeatListener implements View.OnTouchListener {

    private Handler handler = new Handler();
    private int initialInterval;
    private final int normalInterval;
    private final View.OnClickListener clickListener;
    private View touchedView;
    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            if (touchedView.isEnabled()) {
                handler.postDelayed(this, normalInterval);
                clickListener.onClick(touchedView);
            } else {
                handler.removeCallbacks(handlerRunnable);
                touchedView.setPressed(false);
                touchedView = null;
            }
        }
    };

    public RepeatListener(int initialInterval, int normalInterval, View.OnClickListener clickListener) {
        this.initialInterval = initialInterval;
        this.normalInterval = normalInterval;
        this.clickListener = clickListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacks(handlerRunnable);
                handler.postDelayed(handlerRunnable, initialInterval);
                touchedView = v;
                touchedView.setPressed(true);
                clickListener.onClick(v);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                handler.removeCallbacks(handlerRunnable);
                touchedView.setPressed(false);
                touchedView=null;
                return true;
        }
        return false;
    }
}
