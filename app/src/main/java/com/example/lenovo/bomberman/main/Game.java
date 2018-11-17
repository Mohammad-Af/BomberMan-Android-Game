package com.example.lenovo.bomberman.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.lenovo.bomberman.UI.activity.MainActivity;
import com.example.lenovo.bomberman.UI.activity.StartActivity;
import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;

import java.io.IOException;

public class Game {
    private Board board;
    private BomberMan bomberMan;
    private MainActivity gameFrame;
    private boolean running = true;
    public static int level;


    public Game(int level) throws InterruptedException, IOException {

        Game.level = level;


    }

    public Board getBoard() {
        return board;
    }

    boolean isRunning() {
        return running;
    }

    public void endGame() {
        this.running = false;
        MainActivity.setTimer(null);

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BomberMan getBomberMan() {
        return bomberMan;
    }

    public void setBomberMan(BomberMan bomberMan) {
        this.bomberMan = bomberMan;
    }

    public MainActivity getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame() throws IOException {
        this.gameFrame = MainActivity.instance;
    }


}
