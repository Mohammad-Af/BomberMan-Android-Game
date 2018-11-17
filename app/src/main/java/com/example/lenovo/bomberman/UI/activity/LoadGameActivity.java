package com.example.lenovo.bomberman.UI.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.main.Game;
import com.example.lenovo.bomberman.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadGameActivity extends AppCompatActivity implements Loadable {


    public LoadGameActivity() throws IOException, InterruptedException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        instance =this;
        start();
    }


    private Game game =new Game(1);
    public static LoadGameActivity instance;

    public void start(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Board board=loadBoard();
                    game.setBoard(board);
                    BomberMan bomberMan=loadBomberman();
                    board.setBomberManPlace(bomberMan.getRealLocation());
                    game.setBomberMan(bomberMan);

                    MainActivity.init(game);
                    MainActivity.setRemainTime(loadLevel());

                    Intent intent = new Intent(LoadGameActivity.this,MainActivity.class);
                    startActivity(intent);

                } catch (InterruptedException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        thread.start();

    }

    @Override
    public int loadLevel() throws FileNotFoundException {
        Scanner scanner = new Scanner(openFileInput("levelSetting.txt"));
        Game.level= scanner.nextInt();
        int remainTime=scanner.nextInt();
        if(scanner.nextInt()==1)
            MainActivity.setVibration(true);
        return remainTime;
    }

    @Override
    public BomberMan loadBomberman() throws FileNotFoundException {
        BomberMan bomberMan = new BomberMan(game);
        bomberMan.load();
        return bomberMan;
    }

    @Override
    public Board loadBoard() throws IOException {
        return new Board();
    }

    @Override
    public void onBackPressed() {

    }
}
