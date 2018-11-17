package com.example.lenovo.bomberman.components.power;

import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.components.cell.Cell;
import com.example.lenovo.bomberman.UI.Images;

import java.io.PrintStream;

public class BombController extends PowerChanger {

    public BombController(Cell currentCell) {
        super(currentCell);
        image= Images.bombControl;
    }

    @Override
    public void doJob(BomberMan bomberMan) {
        bomberMan.setCanControlBombs(true);
        currentCell.removeInsideObj(this);
    }

    @Override
    public void save(PrintStream printStream, int cellIndex) {
        printStream.print(getClass().getCanonicalName()+" "+cellIndex+" ");
    }


}
