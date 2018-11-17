package com.example.lenovo.bomberman.IO;

import android.content.Context;

import com.example.lenovo.bomberman.UI.activity.MainActivity;
import com.example.lenovo.bomberman.main.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class SaveGame implements Saveable{

   public SaveGame(Game game){
       this.game=game;
       save();
   }




    private Game game;

    private void save() {
        try {
            saveBoard();
            saveBomberman();
            saveLevel();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void saveLevel() throws FileNotFoundException {

        PrintStream printStream=new PrintStream(MainActivity.instance.openFileOutput("levelSetting.txt", Context.MODE_PRIVATE));
        printStream.print(Game.level+" "+game.getGameFrame().getRemainTime()+" ");
        if(game.getGameFrame().hasVibration()){
            printStream.print(1+" ");
        }else
            printStream.print(0+" ");
    }

    @Override
    public void saveBomberman() throws IOException {
        game.getBomberMan().save();
    }

    @Override
    public void saveBoard() throws IOException {
        game.getBoard().save();
    }


}
