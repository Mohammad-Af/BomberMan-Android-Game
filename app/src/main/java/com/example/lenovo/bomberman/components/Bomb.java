package com.example.lenovo.bomberman.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.lenovo.bomberman.main.Side;
import com.example.lenovo.bomberman.UI.Images;
import com.example.lenovo.bomberman.components.cell.Cell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Bomb {
    private Point location;
    private Bitmap currentImage;
    private int currentSize;
    private Cell currentCell;
    private int toExplode = 3500;
    private int toFinishBurning = 4;
    private int speed = 100;                             //change speed here!!!
    private boolean burned;
    private boolean exploded = false;

    private int bombRadius = 1;
    private boolean autoExplode = true;


    public Bomb(int x, int y, int bombRadius, boolean autoExplode) throws IOException {
        location = new Point(x, y);
        currentImage = Images.bomb[0];
        currentSize = Board.cellsSize - 20;
        this.bombRadius = bombRadius;
        this.autoExplode = autoExplode;
        if (!autoExplode)
            toFinishBurning = 5;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
        currentCell.addInsideObj(this);
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void save(PrintStream printStream) throws FileNotFoundException {

        if (autoExplode)
            printStream.print(1 + " ");
        else
            printStream.print(0 + " ");

        printStream.print(location.x + " " + location.y + " " + bombRadius + " ");


        if (exploded)
            printStream.print(1 + " ");
        else
            printStream.print(0+" ");

        printStream.print(toExplode+" ");

        printStream.print(toFinishBurning+" ");

        printStream.print(currentSize+" ");

        if (Images.bomb[0].equals(currentImage)) {
            printStream.print(0 + " ");
        } else {
            printStream.print(2 + " ");
        }




    }

    void load(Scanner scanner) {
        currentCell.addInsideObj(this);

        boolean exploded=false;
        if(scanner.nextInt()==1)
            exploded=true;

        this.exploded=exploded;

        toExplode=scanner.nextInt();

        toFinishBurning=scanner.nextInt();

        currentSize=scanner.nextInt();

        currentImage=Images.bomb[scanner.nextInt()];
    }

    public void paint(int x, int y, Canvas canvas) {
        if (location.x > x - Board.cellsSize && location.x < x + canvas.getClipBounds().width()
                && location.y > y - Board.cellsSize && location.y < y + canvas.getClipBounds().height()) {
            if (!exploded)
            canvas.drawBitmap(currentImage, null, new Rect(location.x - x, location.y - y, location.x - x + currentSize, location.y - y + currentSize),null);
        }
    }

    public void animate() {


        if (toExplode % (speed) == 0) {
            if (!exploded) {
                if (currentSize >= Board.cellsSize) {
                    currentSize -= Board.cellsSize/10;
                    currentImage = Images.bomb[2];
                } else {
                    currentSize += Board.cellsSize/10;
                }
                if (toExplode == 0) {
                    if (autoExplode) {
                        exploded = true;
                    } else {
                        toExplode = 3500;
                    }
                }
            } else if (toFinishBurning != 0) {
                toFinishBurning--;
            } else {
                burned = true;
            }
        }

        toExplode--;

    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }


    public boolean isBurned() {
        return burned;
    }


    public void burn(Board board) {

        currentCell.burn();

        Cell cell = currentCell;
        for (int i = 0; i < bombRadius; i++) {
            cell = cell.getNearbyCell(board, Side.RIGHT_SIDE);
            if (!board.blockInLocation(cell.getLocation().x, cell.getLocation().y))
                cell.burn();
            else
                break;
            if (board.wallInLocation(cell.getLocation().x, cell.getLocation().y))
                break;
        }

        cell = currentCell;
        for (int i = 0; i < bombRadius; i++) {
            cell = cell.getNearbyCell(board, Side.LEFT_SIDE);
            if (!board.blockInLocation(cell.getLocation().x, cell.getLocation().y))
                cell.burn();
            else
                break;
            if (board.wallInLocation(cell.getLocation().x, cell.getLocation().y))
                break;
        }

        cell = currentCell;
        for (int i = 0; i < bombRadius; i++) {
            cell = cell.getNearbyCell(board, Side.UP_SIDE);
            if (!board.blockInLocation(cell.getLocation().x, cell.getLocation().y))
                cell.burn();
            else
                break;
            if (board.wallInLocation(cell.getLocation().x, cell.getLocation().y))
                break;
        }

        cell = currentCell;
        for (int i = 0; i < bombRadius; i++) {
            cell = cell.getNearbyCell(board, Side.DOWN_SIDE);
            if (!board.blockInLocation(cell.getLocation().x, cell.getLocation().y))
                cell.burn();
            else
                break;
            if (board.wallInLocation(cell.getLocation().x, cell.getLocation().y))
                break;
        }


    }

    public int getToExplode() {
        return toExplode;
    }

    public void setToExplode(int toExplode) {
        this.toExplode = toExplode;
    }

    public void setToFinishBurning(int toFinishBurning) {
        this.toFinishBurning = toFinishBurning;
    }

    public boolean isAutoExplode() {
        return autoExplode;
    }

}
