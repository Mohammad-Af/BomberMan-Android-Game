package com.example.lenovo.bomberman.UI;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lenovo.bomberman.R;

import java.io.IOException;

public class Images {
    public static Bitmap bomberManWU[] = new Bitmap[5];
    public static Bitmap bomberManWD[] = new Bitmap[5];
    public static Bitmap bomberManWR[] = new Bitmap[5];
    public static Bitmap bomberManWL[] = new Bitmap[5];
    public static Bitmap bomberManDeath[] = new Bitmap[5];
    public static Bitmap[] monster1WR = new Bitmap[4];
    public static Bitmap[] monster1WL = new Bitmap[4];
    public static Bitmap[] monster1WU = new Bitmap[4];
    public static Bitmap[] monster1WD = new Bitmap[4];
    public static Bitmap[] monster2WR = new Bitmap[4];
    public static Bitmap[] monster2WL = new Bitmap[4];
    public static Bitmap[] monster2WU = new Bitmap[4];
    public static Bitmap[] monster2WD = new Bitmap[4];
    public static Bitmap[] monster3WR = new Bitmap[4];
    public static Bitmap[] monster3WL = new Bitmap[4];
    public static Bitmap[] monster3WU = new Bitmap[4];
    public static Bitmap[] monster3WD = new Bitmap[4];
    public static Bitmap[] monster4WR = new Bitmap[4];
    public static Bitmap[] monster4WL = new Bitmap[4];
    public static Bitmap[] monster4WU = new Bitmap[4];
    public static Bitmap[] monster4WD = new Bitmap[4];
    public static Bitmap[] monsterDeath = new Bitmap[12];
    public static Bitmap bomb[] = new Bitmap[3];
    public static Bitmap throwBomb;
    public static Bitmap blockImage;
    public static Bitmap wallImage;
    public static Bitmap background;
    public static Bitmap fire;
    public static Bitmap wallBurning[] = new Bitmap[3];
    public static Bitmap bombIncreaser;
    public static Bitmap bombDecrease;
    public static Bitmap bombControl;
    public static Bitmap pointUp;
    public static Bitmap pointDown;
    public static Bitmap radiusUp;
    public static Bitmap radiusDown;
    public static Bitmap speedUp;
    public static Bitmap speedDown;
    public static Bitmap ghostPower;
    public static Bitmap openGate;
    public static Bitmap closeGate;
    public static Bitmap burning[] = new Bitmap[3];


    public Images(Resources resources) throws IOException {



        bomberManWU[0] = BitmapFactory.decodeResource(resources, R.drawable.wu1);
        bomberManWU[1] = BitmapFactory.decodeResource(resources, R.drawable.wu2);
        bomberManWU[2] = BitmapFactory.decodeResource(resources, R.drawable.wu3);
        bomberManWU[3] = BitmapFactory.decodeResource(resources, R.drawable.wu4);
        bomberManWU[4] = BitmapFactory.decodeResource(resources, R.drawable.wu5);

        bomberManWD[0] = BitmapFactory.decodeResource(resources, R.drawable.wd1);
        bomberManWD[1] = BitmapFactory.decodeResource(resources, R.drawable.wd2);
        bomberManWD[2] = BitmapFactory.decodeResource(resources, R.drawable.wd3);
        bomberManWD[3] = BitmapFactory.decodeResource(resources, R.drawable.wd4);
        bomberManWD[4] = BitmapFactory.decodeResource(resources, R.drawable.wd5);

        bomberManWR[0] = BitmapFactory.decodeResource(resources, R.drawable.wr1);
        bomberManWR[1] = BitmapFactory.decodeResource(resources, R.drawable.wr2);
        bomberManWR[2] = BitmapFactory.decodeResource(resources, R.drawable.wr3);
        bomberManWR[3] = BitmapFactory.decodeResource(resources, R.drawable.wr4);
        bomberManWR[4] = BitmapFactory.decodeResource(resources, R.drawable.wr5);

        bomberManWL[0] = BitmapFactory.decodeResource(resources, R.drawable.wl1);
        bomberManWL[1] = BitmapFactory.decodeResource(resources, R.drawable.wl2);
        bomberManWL[2] = BitmapFactory.decodeResource(resources, R.drawable.wl3);
        bomberManWL[3] = BitmapFactory.decodeResource(resources, R.drawable.wl4);
        bomberManWL[4] = BitmapFactory.decodeResource(resources, R.drawable.wl5);


        bomb[0] = BitmapFactory.decodeResource(resources, R.drawable.bomb1);
        bomb[1] = BitmapFactory.decodeResource(resources, R.drawable.bomb2);
        bomb[2] = BitmapFactory.decodeResource(resources, R.drawable.bomb3);

        bomberManDeath[0] = BitmapFactory.decodeResource(resources,R.drawable.bdeath1);
        bomberManDeath[1] = BitmapFactory.decodeResource(resources,R.drawable.bdeath2);
        bomberManDeath[2] = BitmapFactory.decodeResource(resources,R.drawable.bdeath3);
        bomberManDeath[3] = BitmapFactory.decodeResource(resources,R.drawable.bdeath4);
        bomberManDeath[4] = BitmapFactory.decodeResource(resources,R.drawable.bdeath5);

//        //monster1..........................................

        monster1WR[0] = BitmapFactory.decodeResource(resources,R.drawable.monsterwr1);
        monster1WR[1] = BitmapFactory.decodeResource(resources,R.drawable.monsterwr2);
        monster1WR[2] = BitmapFactory.decodeResource(resources,R.drawable.monsterwr3);
        monster1WR[3] = BitmapFactory.decodeResource(resources,R.drawable.monsterwr4);

        monster1WL[0] = BitmapFactory.decodeResource(resources,R.drawable.monsterwl1);
        monster1WL[1] = BitmapFactory.decodeResource(resources,R.drawable.monsterwl2);
        monster1WL[2] = BitmapFactory.decodeResource(resources,R.drawable.monsterwl3);
        monster1WL[3] = BitmapFactory.decodeResource(resources,R.drawable.monsterwl4);

        monster1WU[0] = BitmapFactory.decodeResource(resources,R.drawable.monsterwu1);
        monster1WU[1] = BitmapFactory.decodeResource(resources,R.drawable.monsterwu2);
        monster1WU[2] = BitmapFactory.decodeResource(resources,R.drawable.monsterwu3);
        monster1WU[3] = BitmapFactory.decodeResource(resources,R.drawable.monsterwu4);

        monster1WD[0] = BitmapFactory.decodeResource(resources,R.drawable.monsterwd1);
        monster1WD[1] = BitmapFactory.decodeResource(resources,R.drawable.monsterwd2);
        monster1WD[2] = BitmapFactory.decodeResource(resources,R.drawable.monsterwd3);
        monster1WD[3] = BitmapFactory.decodeResource(resources,R.drawable.monsterwd4);


//        //monster2..........................................

        monster2WR[0] = BitmapFactory.decodeResource(resources,R.drawable.m1wr1);
        monster2WR[1] = BitmapFactory.decodeResource(resources,R.drawable.m1wr2);
        monster2WR[2] = BitmapFactory.decodeResource(resources,R.drawable.m1wr3);
        monster2WR[3] = BitmapFactory.decodeResource(resources,R.drawable.m1wr4);

        monster2WL[0] = BitmapFactory.decodeResource(resources,R.drawable.m1wl1);
        monster2WL[1] = BitmapFactory.decodeResource(resources,R.drawable.m1wl2);
        monster2WL[2] = BitmapFactory.decodeResource(resources,R.drawable.m1wl3);
        monster2WL[3] = BitmapFactory.decodeResource(resources,R.drawable.m1wl4);

        monster2WU[0] = BitmapFactory.decodeResource(resources,R.drawable.m1wu1);
        monster2WU[1] = BitmapFactory.decodeResource(resources,R.drawable.m1wu2);
        monster2WU[2] = BitmapFactory.decodeResource(resources,R.drawable.m1wu3);
        monster2WU[3] = BitmapFactory.decodeResource(resources,R.drawable.m1wu4);

        monster2WD[0] = BitmapFactory.decodeResource(resources,R.drawable.m1wd1);
        monster2WD[1] = BitmapFactory.decodeResource(resources,R.drawable.m1wd2);
        monster2WD[2] = BitmapFactory.decodeResource(resources,R.drawable.m1wd3);
        monster2WD[3] = BitmapFactory.decodeResource(resources,R.drawable.m1wd4);

//        //monster3..........................................
//
        monster3WR[0] = BitmapFactory.decodeResource(resources,R.drawable.m2wr1);
        monster3WR[1] = BitmapFactory.decodeResource(resources,R.drawable.m2wr2);
        monster3WR[2] = BitmapFactory.decodeResource(resources,R.drawable.m2wr3);
        monster3WR[3] = BitmapFactory.decodeResource(resources,R.drawable.m2wr4);

        monster3WL[0] = BitmapFactory.decodeResource(resources,R.drawable.m2wl1);
        monster3WL[1] = BitmapFactory.decodeResource(resources,R.drawable.m2wl2);
        monster3WL[2] = BitmapFactory.decodeResource(resources,R.drawable.m2wl3);
        monster3WL[3] = BitmapFactory.decodeResource(resources,R.drawable.m2wl4);

        monster3WU[0] = BitmapFactory.decodeResource(resources,R.drawable.m2wu1);
        monster3WU[1] = BitmapFactory.decodeResource(resources,R.drawable.m2wu2);
        monster3WU[2] = BitmapFactory.decodeResource(resources,R.drawable.m2wu3);
        monster3WU[3] = BitmapFactory.decodeResource(resources,R.drawable.m2wu4);

        monster3WD[0] = BitmapFactory.decodeResource(resources,R.drawable.m2wd1);
        monster3WD[1] = BitmapFactory.decodeResource(resources,R.drawable.m2wd2);
        monster3WD[2] = BitmapFactory.decodeResource(resources,R.drawable.m2wd3);
        monster3WD[3] = BitmapFactory.decodeResource(resources,R.drawable.m2wd4);
//
//
//        //monster4..............................
        monster4WR[0] = BitmapFactory.decodeResource(resources,R.drawable.m3wr1);
        monster4WR[1] = BitmapFactory.decodeResource(resources,R.drawable.m3wr2);
        monster4WR[2] = BitmapFactory.decodeResource(resources,R.drawable.m3wr3);
        monster4WR[3] = BitmapFactory.decodeResource(resources,R.drawable.m3wr4);

        monster4WL[0] = BitmapFactory.decodeResource(resources,R.drawable.m3wl1);
        monster4WL[1] = BitmapFactory.decodeResource(resources,R.drawable.m3wl2);
        monster4WL[2] = BitmapFactory.decodeResource(resources,R.drawable.m3wl3);
        monster4WL[3] = BitmapFactory.decodeResource(resources,R.drawable.m3wl4);

        monster4WU[0] = BitmapFactory.decodeResource(resources,R.drawable.m3wu1);
        monster4WU[1] = BitmapFactory.decodeResource(resources,R.drawable.m3wu2);
        monster4WU[2] = BitmapFactory.decodeResource(resources,R.drawable.m3wu3);
        monster4WU[3] = BitmapFactory.decodeResource(resources,R.drawable.m3wu4);

        monster4WD[0] = BitmapFactory.decodeResource(resources,R.drawable.m3wd1);
        monster4WD[1] = BitmapFactory.decodeResource(resources,R.drawable.m3wd2);
        monster4WD[2] = BitmapFactory.decodeResource(resources,R.drawable.m3wd3);
        monster4WD[3] = BitmapFactory.decodeResource(resources,R.drawable.m3wd4);
//
//
        monsterDeath[0] = BitmapFactory.decodeResource(resources,R.drawable.death1);
        monsterDeath[1] = BitmapFactory.decodeResource(resources,R.drawable.death2);
        monsterDeath[2] = BitmapFactory.decodeResource(resources,R.drawable.death3);
        monsterDeath[3] = BitmapFactory.decodeResource(resources,R.drawable.death4);
        monsterDeath[4] = BitmapFactory.decodeResource(resources,R.drawable.death5);
        monsterDeath[5] = BitmapFactory.decodeResource(resources,R.drawable.death6);
        monsterDeath[6] = BitmapFactory.decodeResource(resources,R.drawable.death7);
        monsterDeath[7] = BitmapFactory.decodeResource(resources,R.drawable.death8);
        monsterDeath[8] = BitmapFactory.decodeResource(resources,R.drawable.death9);
        monsterDeath[9] = BitmapFactory.decodeResource(resources,R.drawable.death10);
        monsterDeath[10] = BitmapFactory.decodeResource(resources,R.drawable.death11);
        monsterDeath[11] = BitmapFactory.decodeResource(resources,R.drawable.death12);


//
//        //powerChangers................................
        bombIncreaser = BitmapFactory.decodeResource(resources,R.drawable.bombincrease);
        bombDecrease = BitmapFactory.decodeResource(resources,R.drawable.bombdecrease);
        bombControl = BitmapFactory.decodeResource(resources,R.drawable.bombcontrol);
        pointUp = BitmapFactory.decodeResource(resources,R.drawable.pointup);
        pointDown = BitmapFactory.decodeResource(resources,R.drawable.pointdown);
        radiusDown = BitmapFactory.decodeResource(resources,R.drawable.radiusdown);
        radiusUp = BitmapFactory.decodeResource(resources,R.drawable.radiusup);
        speedDown = BitmapFactory.decodeResource(resources,R.drawable.speeddown);
        speedUp = BitmapFactory.decodeResource(resources,R.drawable.speedup);
        ghostPower = BitmapFactory.decodeResource(resources,R.drawable.ghostpower);


        openGate = BitmapFactory.decodeResource(resources,R.drawable.open);
        closeGate = BitmapFactory.decodeResource(resources,R.drawable.close);

        throwBomb = BitmapFactory.decodeResource(resources,R.drawable.throwbomb);

        blockImage = BitmapFactory.decodeResource(resources,R.drawable.blockgrass);

        background = BitmapFactory.decodeResource(resources,R.drawable.grass);

        wallImage = BitmapFactory.decodeResource(resources,R.drawable.wallgrass2);
        wallBurning[0] = BitmapFactory.decodeResource(resources,R.drawable.wallburning1);
        wallBurning[1] = BitmapFactory.decodeResource(resources,R.drawable.wallburning2);
        wallBurning[2] = BitmapFactory.decodeResource(resources,R.drawable.wallburning3);

        burning[0] = BitmapFactory.decodeResource(resources,R.drawable.burn1);
        burning[1] = BitmapFactory.decodeResource(resources,R.drawable.burn2);
        burning[2] = BitmapFactory.decodeResource(resources,R.drawable.burn3);

        fire = BitmapFactory.decodeResource(resources,R.drawable.fire2);

    }


}
