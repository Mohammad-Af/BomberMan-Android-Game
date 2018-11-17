package com.example.lenovo.bomberman.components.monster;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.main.Side;
import com.example.lenovo.bomberman.components.cell.Cell;

import java.io.PrintStream;

public abstract class Monster {

    protected Point location;
    boolean alive = true;
    int speed;
    Bitmap[] lastArrayImage;
    int lastIndexImage = 0;
    Side lastMove = Side.UP_SIDE;
    protected Cell currentCell;
    private int size;
    int movementSize = BomberMan.movementSize;

    Monster(int x, int y) {
        location = new Point(x, y);
        size = Board.cellsSize;
    }

    int imageCounter = 0;

    public void animate() {
        imageCounter++;
        if (alive) {
            //chage size every time and move
            if (imageCounter % 10 == 0) {
//            if (size == Board.cellsSize)
//                size -= 5;
//            else
//                size += 5;
            }

        }
    }

    int toDeadCompeletly = 13;

    public abstract void move(Board board);


    void killBomberman() {
        for (int i = currentCell.getInsideObjs().size()-1; i >-1 ; i--) {
            if (currentCell.getInsideObjs().get(i).getClass() == BomberMan.class) {
                ((BomberMan) currentCell.getInsideObjs().get(i)).setAlive(false);
                ((BomberMan) currentCell.getInsideObjs().get(i)).getCurrentCell()
                        .removeInsideObj(currentCell.getInsideObjs().get(i));
            }
        }
      
    }


    void setImageIndex() {
        lastIndexImage++;
        if (lastIndexImage == 4) {
            lastIndexImage = 0;
        }
    }


    protected void setCurrentCell(Board board) {

        if (currentCell != null) {
            currentCell.removeInsideObj(this);
        }
        currentCell = board.getBackgroundCells().get(((location.y + Board.cellsSize / 2)
                / Board.cellsSize) * (2 * board.getHeight() + 3)
                + (location.x + Board.cellsSize / 2) / Board.cellsSize);
        currentCell.addInsideObj(this);


    }

    public void paint(int x, int y, Canvas canvas) {
        if (location.x > x - Board.cellsSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - Board.cellsSize && location.y < y + canvas.getClipBounds().height()) {
            canvas.drawBitmap(lastArrayImage[lastIndexImage], null,
                    new Rect(location.x - x, location.y - y, location.x - x + size, location.y - y + size),null);

        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean deadCompeletly() {
        if (toDeadCompeletly == 0) {
            currentCell.removeInsideObj(this);
            return true;
        }
        return false;
    }

    public abstract void save(PrintStream printStream, int cellIndex);

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setLastMove(Side lastMove) {
        this.lastMove = lastMove;
    }
}
