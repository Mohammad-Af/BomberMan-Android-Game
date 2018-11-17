package com.example.lenovo.bomberman.components.power;

import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.components.cell.Cell;
import com.example.lenovo.bomberman.UI.Images;

import java.io.PrintStream;

public class GhostPower extends PowerChanger{
    public GhostPower(Cell currentCell) {
        super(currentCell);
        image = Images.ghostPower;

    }

    @Override
    public void doJob(BomberMan bomberMan) {
        bomberMan.setGhostMood(true);
    }

    @Override
    public void save(PrintStream printStream, int cellIndex) {
        printStream.print(getClass().getCanonicalName()+" "+cellIndex+" ");
    }
}
