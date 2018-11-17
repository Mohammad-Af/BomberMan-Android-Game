package com.example.lenovo.bomberman.components.cell;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.UI.Images;

public class BlockCell extends Cell {

    public BlockCell(int xLocation, int yLocation) {
        super(Images.blockImage, new Point(xLocation, yLocation));

        destroyable = false;
    }

    @Override
    public void paint(int x, int y, Canvas canvas) {
        if (location.x > x - cellSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - cellSize && location.y < y + canvas.getClipBounds().height()) {
            canvas.drawBitmap(Images.blockImage, null, new Rect(location.x - x, location.y - y, location.x - x + cellSize, location.y - y + cellSize),null);
        }
    }

    @Override
    public void burn() {

    }
}
