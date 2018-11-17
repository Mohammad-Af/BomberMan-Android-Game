package com.example.lenovo.bomberman.IO;

import java.io.FileNotFoundException;
import java.io.IOException;

interface Saveable {

    void saveLevel() throws FileNotFoundException;
    void saveBomberman() throws IOException;
    void saveBoard() throws IOException;


}
