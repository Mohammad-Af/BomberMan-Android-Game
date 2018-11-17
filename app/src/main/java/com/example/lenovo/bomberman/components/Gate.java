package com.example.lenovo.bomberman.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.UI.Images;
import com.example.lenovo.bomberman.components.cell.Cell;


public class Gate {
    private Cell gateCell;
    private Bitmap image;
    private boolean open = false;

    Gate(Cell cell){
        gateCell=cell;
        image= Images.closeGate;
        gateCell.addInsideObj(this);

    }

    public void paint(int x, int y, Canvas canvas, boolean levelCompeleted) {
        if(levelCompeleted) {
            image= Images.openGate;
            open=true;
        }
        Point loc = gateCell.getLocation();
        if (loc.x > x - Board.cellsSize && loc.x < x + canvas.getClipBounds().width()
                && loc.y > y - Board.cellsSize && loc.y < y + canvas.getClipBounds().height()) {
            canvas.drawBitmap(image, null,
                    new Rect(loc.x - x, loc.y - y, loc.x - x + Board.cellsSize, loc.y - y + Board.cellsSize),null);

        }
    }

    Cell getGateCell() {
        return gateCell;
    }

    boolean isOpen() {
        return open;
    }

}
