package com.example.lenovo.bomberman.UI.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.main.Game;
import com.example.lenovo.bomberman.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        newGameActivity=this;

    }


    private int width;
    private int height;
    private int monstersCount;
    private int res;


    public void startListener(View view) {
        try {
            width = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.board_width)).getText()));
            height = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.board_height)).getText()));
            if (!String.valueOf(((EditText) findViewById(R.id.monsters_count)).getText()).equals("Default")) {
                monstersCount = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.monsters_count)).getText()));
            }
        } catch (java.lang.NumberFormatException e1) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Invalid input");
        }

        res = 50 + ((SeekBar)findViewById(R.id.seekBar)).getProgress()*50;


        List<Object> bomberManPowerChangers = new ArrayList<>();
        bomberManPowerChangers.add(40);                               //speed
        bomberManPowerChangers.add(1);                               //bombLimit
        bomberManPowerChangers.add(false);                           //can control bombs
        bomberManPowerChangers.add(1);                               //bomb radius
        bomberManPowerChangers.add(false);                         // goast power
        boolean hasVibration = ((CheckBox)findViewById(R.id.checkBox)).isChecked();
        init(1, width, height, res, monstersCount, bomberManPowerChangers,hasVibration);

    }

    public static void init(final int level, final int width, final int height, final int res, final int monstersCount, final List<Object> bombermanPowerChangers, final boolean hasVibration){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Game game = new Game(level);
                    Board board = new Board(width, height, res,monstersCount);
                    game.setBoard(board);
                    BomberMan bomberMan = new BomberMan(game);
                    bomberMan.setSpeed((Integer) bombermanPowerChangers.get(0));
                    bomberMan.setBombLimit((Integer) bombermanPowerChangers.get(1));
                    bomberMan.setCanControlBombs((Boolean) bombermanPowerChangers.get(2));
                    bomberMan.setBombRadius((Integer) bombermanPowerChangers.get(3));
                    bomberMan.setGhostMood((Boolean) bombermanPowerChangers.get(4));
                    game.setBomberMan(bomberMan);
                    MainActivity.init(game);
                    MainActivity.setVibration(hasVibration);
                    start();


                } catch (InterruptedException | IOException | IllegalAccessException | InstantiationException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        });
        thread.start();
    }
    static NewGameActivity newGameActivity;
    public static void start(){
        Intent intent =new Intent(newGameActivity,MainActivity.class);
        newGameActivity.startActivity(intent);

    }


}
