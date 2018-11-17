package com.example.lenovo.bomberman.main;
import com.example.lenovo.bomberman.UI.activity.MainActivity;
import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.Bomb;
import com.example.lenovo.bomberman.components.monster.Monster;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Animation {

    public Animation(final Game game) throws InterruptedException, IOException {


        final Board board = game.getBoard();

        Thread animThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (game.isRunning()) {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (Bomb bomb : board.getBombs()) {
                        bomb.animate();


                        if (bomb.isExploded() && !bomb.isBurned()) {
                            bomb.burn(board);
                            if(game.getGameFrame().hasVibration()) {
                                vibrate();

                                MainActivity.paintView.shake();
                            }

                            if(bomb.getToExplode()== -1 )
                            game.getBomberMan().increaseBombLimit();
                        }



                        if(!bomb.isAutoExplode() && !bomb.isExploded() && !game.getBomberMan().getBombsCanControl().contains(bomb)){             //used for bomberMan bombs after game loaded
                            game.getBomberMan().getBombsCanControl().add(bomb);
                        }

                    }

                    for (Monster monster : board.getMonsters()) {
                        monster.animate();
                        monster.move(board);

                    }


                    game.getBomberMan().increaseImageCounter();
                    game.getBomberMan().allowToMove();

                    game.getBomberMan().increasePoint(game.getBoard().getExplodedWallCells()*10);
                    game.getBomberMan().increasePoint(game.getBoard().getExplodedMonsters()*20);
                    game.getBoard().setExplodedMonsters(0);
                    game.getBoard().setExplodedWallCells(0);


                    MainActivity.paintView.postInvalidate();

                }
            }
        });
        animThread.start();






    }

    private boolean isVibrating=false;
    private synchronized void vibrate() {
        if(!isVibrating) {
            isVibrating=true;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.vibrator.vibrate(10);
                    isVibrating=false;
                }
            });
            thread.start();
        }
    }
}
