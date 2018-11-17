package com.example.lenovo.bomberman.components.power;

import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.components.cell.Cell;
import com.example.lenovo.bomberman.UI.Images;

import java.io.PrintStream;

public class BombIncreaser extends PowerChanger {


    public BombIncreaser(Cell currentCell) {
        super(currentCell);
        image = Images.bombIncreaser;

    }

    @Override
    public void doJob(BomberMan bomberMan) {
        bomberMan.increaseBombLimit();
        currentCell.removeInsideObj(this);
    }

    @Override
    public void save(PrintStream printStream, int cellIndex) {
        printStream.print(getClass().getCanonicalName()+" "+cellIndex+" ");
    }


}
