package com.example.lenovo.bomberman.components.cell;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.components.Bomb;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.components.Gate;
import com.example.lenovo.bomberman.UI.Images;
import com.example.lenovo.bomberman.components.monster.Monster;
import com.example.lenovo.bomberman.components.power.PowerChanger;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class BackgroundCell extends Cell {

    private boolean burning = false;

    public BackgroundCell(int xLocation, int yLocation) {
        super(Images.background, new Point(xLocation, yLocation));

        destroyable = false;

    }


    public void paint(int x, int y, Canvas canvas) {


        if (location.x > x - cellSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - cellSize && location.y < y + canvas.getClipBounds().height()) {
            canvas.drawBitmap(Images.background, null, new Rect(location.x - x, location.y - y, location.x - x + cellSize, location.y - y + cellSize),null);

            if (burning) {
                canvas.drawBitmap(Images.fire, null, new Rect(location.x - x, location.y - y, location.x - x + cellSize, location.y - y + cellSize),null);

                burning = false;
            }

        }
    }


    @Override
    public void burn() {

        burning = true;

        List<Object> objectToRemove = new ArrayList<>();
        for (final Object object : insideObjs) {
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
            if (object instanceof Monster) {
                ((Monster) object).setAlive(false);
            }
            if (object instanceof PowerChanger) {
                ((PowerChanger) object).burn();
            }

            if (!(object instanceof Gate))
                objectToRemove.add(object);
        }

        for (Object object : objectToRemove) {
            insideObjs.remove(object);
        }


    }


}
