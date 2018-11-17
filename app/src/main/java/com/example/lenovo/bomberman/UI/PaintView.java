package com.example.lenovo.bomberman.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.main.Game;


public class PaintView extends View {

    public static Board board;

    public PaintView(Context context) {
        super(context);

    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public static Game game;
    private int x;
    private int y;
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (board.getBomberManPlace().x > canvas.getWidth() / 2) {
            x = board.getBomberManPlace().x - canvas.getWidth() / 2;
        }
        if (board.getBomberManPlace().y > canvas.getHeight() / 2) {
            y = board.getBomberManPlace().y - canvas.getHeight() / 2;
        }
        if (board.getBomberManPlace().x > (2 * board.getHeight() + 3) * Board.cellsSize - canvas.getWidth() / 2) {
            x = (2 * board.getHeight() + 3) * Board.cellsSize - getWidth();
        }
        if (board.getBomberManPlace().y > (2 * board.getWidth() + 3) * Board.cellsSize - canvas.getHeight() / 2) {
            y = (2 * board.getWidth() + 3) * Board.cellsSize - getHeight();
        }


        // for large screens
        if(canvas.getWidth() > (2*board.getHeight()+3)*Board.cellsSize){
            x = -(canvas.getWidth() - (2*board.getHeight()+3)*Board.cellsSize)/2;
        }
        if(canvas.getHeight() > (2*board.getWidth()+3)*Board.cellsSize){
            y = -(canvas.getHeight() - (2*board.getWidth()+3)*Board.cellsSize)/2;
        }


        board.paint(x, y, canvas);

        game.getBomberMan().paint(x, y, canvas);


        game.getGameFrame().updateTimeAndPoint();
    }

    private  int shakeStep=4;
    public  void shake() {

        if (shakeStep == 4) {
            x += 5;
        }
        if (shakeStep == 3) {
            y -= 5;
        }
        if (shakeStep == 2) {
            x -= 5;
        }
        if (shakeStep == 1) {
            y += 5;
        }
        shakeStep--;
        if (shakeStep == 0) {
            shakeStep = 4;
        }

    }


}
