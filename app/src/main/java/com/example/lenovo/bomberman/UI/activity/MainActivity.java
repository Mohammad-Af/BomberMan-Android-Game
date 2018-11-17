package com.example.lenovo.bomberman.UI.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bomberman.IO.SaveGame;
import com.example.lenovo.bomberman.R;
import com.example.lenovo.bomberman.UI.PaintView;
import com.example.lenovo.bomberman.UI.RepeatListener;
import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.Bomb;
import com.example.lenovo.bomberman.components.BomberMan;
import com.example.lenovo.bomberman.main.Animation;
import com.example.lenovo.bomberman.main.Game;
import com.example.lenovo.bomberman.main.Side;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static PaintView paintView;
    public static Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        initButtons();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        paintView = (PaintView) findViewById(R.id.paint_view);
        PaintView.game = game;
        PaintView.board = game.getBoard();

        instance = this;
        try {
            game.setGameFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new Animation(game);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        activePointCounter();


    }


    private void initButtons() {
        Button right = (Button) findViewById(R.id.right_button);

        int time = 10;
        int time2 = 10;

        right.setOnTouchListener(new RepeatListener(time, time2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getBomberMan().shouldMove(Side.RIGHT_SIDE);
            }
        }));
        Button left = (Button) findViewById(R.id.left_button);
        left.setOnTouchListener(new RepeatListener(time, time2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getBomberMan().shouldMove(Side.LEFT_SIDE);
            }
        }));
        Button up = (Button) findViewById(R.id.up_button);
        up.setOnTouchListener(new RepeatListener(time, time2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getBomberMan().shouldMove(Side.UP_SIDE);
            }
        }));
        Button down = (Button) findViewById(R.id.down_button);
        down.setOnTouchListener(new RepeatListener(time, time2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getBomberMan().shouldMove(Side.DOWN_SIDE);
            }
        }));


    }


    private static Game game;
    private static Board board;
    private static int cellsSize;
    public static MainActivity instance;

    public static void init(Game game) throws IOException, InterruptedException {
        MainActivity.game = game;
        board = game.getBoard();
        cellsSize = Board.cellsSize;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }

    public void saveClickListener(View view) throws IOException {
        if (game.getBomberMan().isAlive()) {
            game.endGame();

            new SaveGame(game);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Game saved");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(intent);
                }
            });
            alert.show();

        }
    }


    public void throwBomb(View view) {

        BomberMan bm = game.getBomberMan();
        Point loc = bm.getRealLocation();
        if (BomberMan.alive && bm.getBombLimit() != 0
                && !board.wallInLocation((loc.x / cellsSize) * cellsSize, (loc.y / cellsSize) * cellsSize)
                && !board.blockInLocation((loc.x / cellsSize) * cellsSize, (loc.y / cellsSize) * cellsSize)) {
            bm.decreaseBombLimit();
            bm.setLastMove(null);
            int x = cellsSize * ((bm.getRealLocation().x + cellsSize / 2) / cellsSize);
            int y = cellsSize * ((bm.getRealLocation().y + cellsSize / 2) / cellsSize);
            if (!board.bombInLocation(x, y)) {
                try {
                    Bomb bomb = new Bomb(x, y, bm.getBombRadius(), !bm.canControlBombs());
                    if (bm.canControlBombs()) {
                        bm.addBomb(bomb);
                    }
                    if (bm.getCurrentCell() == null)
                        bomb.setCurrentCell(board.getBackgroundCells().get(2 * game.getBoard().getHeight() + 4));
                    else
                        bomb.setCurrentCell(bm.getCurrentCell());


                    board.addBomb(bomb);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void explodeBomb(View view) {
        if (BomberMan.alive) {
            int response = game.getBomberMan().explodeBomb();
            if (response == 0)
                Toast.makeText(getApplicationContext(), "You don't have this power !", Toast.LENGTH_SHORT).show();
            else if (response == 2)
                Toast.makeText(getApplicationContext(), "You don't have any bomb to explode !", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateTimeAndPoint() {
        TextView timeLabel = (TextView) findViewById(R.id.time_txt);
        TextView pointLabel = (TextView) findViewById(R.id.point_txt);
        TextView monsterLabel = (TextView) findViewById(R.id.monster_txt);
        TextView levelLabel = (TextView) findViewById(R.id.level_txt);

        timeLabel.setText("Remain Time :   " + getTimeFormat(remainTime));
        pointLabel.setText("Point :  " + game.getBomberMan().getPoint());
        monsterLabel.setText("Remain Monsters : " + board.getMonsters().size() + " ");
        levelLabel.setText("Level : " + Game.level);
    }


    public int getCellsSize() {
        return cellsSize;
    }

    private String getTimeFormat(int time) {
        String s = "0";
        s += (time / 60);
        s += ":";
        if (time % 60 < 10) {
            s += "0";
        }
        s += (time % 60);
        return s;
    }


    private static int remainTime = 300;
    private static Timer timer;
    private static boolean vibration = false;

    private static void activePointCounter() {
        remainTime = 300;
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {                             //while ( gameisRunning ) !!!!!!!!!!!!!!
            @Override
            public void run() {
                if (remainTime > 0)
                    remainTime--;
                if (remainTime == 0)
                    game.getBomberMan().decreasePoint(1);
            }
        };
        timer.schedule(timerTask, 1000, 1000);

    }

    public int getRemainTime() {
        return remainTime;
    }

    public static void setRemainTime(int remainTime) {
        MainActivity.remainTime = remainTime;
    }

    public boolean hasVibration() {
        return vibration;
    }

    public static void setVibration(boolean vibration) {
        MainActivity.vibration = vibration;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this,StartActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.show();
    }

    public static Timer getTimer() {
        return timer;
    }

    public static void setTimer(Timer timer) {
        MainActivity.timer = timer;
    }
}
