package com.example.lenovo.bomberman.components.cell;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.components.Bomb;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.UI.Images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class WallCell extends Cell {

    private boolean destroyed = false;
    private int timeToBurn = 501;


    public WallCell(int xLocation, int yLocation) throws IOException {
        super(Images.wallImage, new Point(xLocation, yLocation));
        destroyable = true;

    }


    @Override
    public void paint(int x, int y, Canvas canvas) {

        if (location.x > x - cellSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - cellSize && location.y < y + canvas.getClipBounds().height()) {
            if (timeToBurn != 501) {
                canvas.drawBitmap(Images.wallBurning[2 - (timeToBurn / 167)], null, new Rect(location.x - x, location.y - y, location.x - x + cellSize, location.y - y + cellSize),null);
            } else
                canvas.drawBitmap(Images.wallImage, null, new Rect(location.x - x, location.y - y, location.x - x + cellSize, location.y - y + cellSize),null);
        }
    }

    @Override
    public synchronized void burn() {
        timeToBurn--;
        if (timeToBurn <=1)
            destroyed = true;


        //for goast Power...........

        List<Object> objectToRemove=new ArrayList<>();
        for (final Object object :insideObjs) {
            if (object.getClass() == Bomb.class) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (((Bomb) object).getToExplode() > 0) {
                            ((Bomb) object).setToExplode(0);
                            ((Bomb) object).setExploded(true);
                            ((Bomb) object).setToFinishBurning(5);
                        }
                    }
                });
                thread.start();

            }

            if (object.getClass() == BomberMan.class) {
                ((BomberMan) object).setAlive(false);
            }
        }
        for (Object object:objectToRemove){
            insideObjs.remove(object);
        }


    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getTimeToBurn() {
        return timeToBurn;
    }

    public void setTimeToBurn(int timeToBurn) {
        this.timeToBurn = timeToBurn;
    }
}
