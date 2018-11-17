package com.example.lenovo.bomberman.UI.activity;

import com.example.lenovo.bomberman.components.Board;
import com.example.lenovo.bomberman.components.BomberMan;

import java.io.FileNotFoundException;
import java.io.IOException;

interface Loadable {

    int loadLevel() throws FileNotFoundException;
    BomberMan loadBomberman() throws IOException;
    Board loadBoard() throws IOException;
}
