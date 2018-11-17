package com.example.lenovo.bomberman.UI.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lenovo.bomberman.UI.Images;
import com.example.lenovo.bomberman.R;

import java.io.IOException;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try {
            new Images(getApplicationContext().getResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startNewGame(View view){
        Intent intent =new Intent(this,NewGameActivity.class);
        startActivity(intent);
    }
    public void loadOldGame(View view) throws IOException, InterruptedException {
        Intent intent = new Intent(this,LoadGameActivity.class);
        startActivity(intent);

    }
}
