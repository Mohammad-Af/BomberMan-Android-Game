package com.example.lenovo.bomberman.components.power;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.components.cell.Cell;
import com.example.lenovo.bomberman.UI.Images;

import java.io.PrintStream;

public abstract class PowerChanger {
    protected Bitmap image ;
    protected Cell currentCell;
    protected Point location;
    private boolean used=false;

    protected PowerChanger(Cell currentCell){
        this.currentCell=currentCell;
        location=currentCell.getLocation();
        currentCell.addInsideObj(this);

    }
    public abstract void doJob(BomberMan bomberMan);
    public abstract void save(PrintStream printStream, int cellIndex);

    public void paint(int x, int y, Canvas canvas) {

        if (location.x > x - Board.cellsSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - Board.cellsSize && location.y < y + canvas.getClipBounds().height()) {

            canvas.drawBitmap(image, null, new Rect(location.x - x, location.y - y, location.x - x + Board.cellsSize, location.y - y + Board.cellsSize),null);

            if(imageCounter!=4)
            canvas.drawBitmap(Images.burning[3-imageCounter], null, new Rect(location.x - x, location.y - y, location.x - x + Board.cellsSize, location.y - y + Board.cellsSize),null);


        }
    }


    private int imageCounter = 4;
    public void burn() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (imageCounter > 0){
                    try {
                        Thread.sleep(200);
                        imageCounter--;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                imageCounter=3;
                used=true;
            }
        });
        thread.start();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
