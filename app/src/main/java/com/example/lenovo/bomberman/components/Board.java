package com.example.lenovo.bomberman.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

import com.example.lenovo.bomberman.main.Game;
import com.example.lenovo.bomberman.main.Side;
import com.example.lenovo.bomberman.UI.activity.LoadGameActivity;
import com.example.lenovo.bomberman.UI.activity.MainActivity;
import com.example.lenovo.bomberman.components.cell.BackgroundCell;
import com.example.lenovo.bomberman.components.cell.BlockCell;
import com.example.lenovo.bomberman.components.cell.WallCell;
import com.example.lenovo.bomberman.components.monster.Monster;
import com.example.lenovo.bomberman.components.monster.Monster1;
import com.example.lenovo.bomberman.components.monster.Monster2;
import com.example.lenovo.bomberman.components.monster.Monster3;
import com.example.lenovo.bomberman.components.monster.Monster4;
import com.example.lenovo.bomberman.components.power.BombController;
import com.example.lenovo.bomberman.components.power.BombDecreaser;
import com.example.lenovo.bomberman.components.power.BombIncreaser;
import com.example.lenovo.bomberman.components.power.GhostPower;
import com.example.lenovo.bomberman.components.power.PointDecreaser;
import com.example.lenovo.bomberman.components.power.PointIncreaser;
import com.example.lenovo.bomberman.components.power.PowerChanger;
import com.example.lenovo.bomberman.components.power.RadiusDecreaser;
import com.example.lenovo.bomberman.components.power.RadiusIncreaser;
import com.example.lenovo.bomberman.components.power.SpeedDecreaser;
import com.example.lenovo.bomberman.components.power.SpeedIncreaser;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

public class Board {
    private int width;
    private int height;
    public static int cellsSize;
    private List<WallCell> wallCells = new ArrayList<>();
    private List<BlockCell> blockCells = new ArrayList<>();
    private List<BackgroundCell> backgroundCells = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<PowerChanger> powerChangers = new ArrayList<>();
    private Point bomberManPlace;
    private int explodedWallCells = 0;
    private int explodedMonsters = 0;
    private int gameLevel;
    private Gate gate;
    private int monstersCount=0;


    public Board() throws IOException {

        loadBoardSize();
        createBlockCells();
        createBackgroundGrassCells();
        loadObjects();
    }


    public Board(int width, int height, int cellsSize, int monstersCount) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        this.width = width;
        this.height = height;
        Board.cellsSize = cellsSize;
        bomberManPlace = new Point(cellsSize, cellsSize);
        gameLevel = Game.level;
        this.monstersCount=monstersCount;

        createBlockCells();
        createWallCells();
        createBackgroundGrassCells();
        createMonsters();
        createPowerChangers();


    }


    private void createBackgroundGrassCells() {

        int x = 0;
        int y = 0;
        for (int i = 0; i < 2 * width + 3; i++) {
            for (int j = 0; j < 2 * height + 3; j++) {
                backgroundCells.add(new BackgroundCell(x, y));
                x += cellsSize;
            }
            x = 0;
            y += cellsSize;
        }
    }


    private void createWallCells() throws IOException {
        int x = cellsSize;
        int y = cellsSize;
        int size;
        for (int i = 0; i < getWidth() + 1; i++) {
            for (int j = 0; j < getHeight() + 1; j++) {
                if (Math.random() > 0.7) {
                    wallCells.add(new WallCell(x, y));
                }
                x += 2 * cellsSize;

            }
            x = cellsSize;
            y += 2 * cellsSize;
        }
        size = wallCells.size();

        x = 2 * cellsSize;
        y = cellsSize;
        for (int i = 0; i < getWidth() + 1; i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (Math.random() > 0.7) {
                    wallCells.add(new WallCell(x, y));
                }
                x += cellsSize * 2;

            }
            x = 2 * cellsSize;
            y += cellsSize * 2;
        }

        if (wallCells.size() > size)
            wallCells.remove(size);
        size = wallCells.size();

        x = cellsSize;
        y = 2 * cellsSize;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight() + 1; j++) {
                if (Math.random() > 0.7) {
                    wallCells.add(new WallCell(x, y));
                }
                x += cellsSize * 2;

            }
            x = cellsSize;
            y += cellsSize * 2;
        }

        if (wallCells.size() > size)
            wallCells.remove(size);
        if (wallCells.size() == 0)
            createWallCells();
        else
            wallCells.remove(0);


    }

    private void createBlockCells() {
        int x = 2 * cellsSize;
        int y = 2 * cellsSize;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                blockCells.add(new BlockCell(x, y));
                x += cellsSize * 2;

            }
            x = 2 * cellsSize;
            y += cellsSize * 2;
        }

        x = 0;
        y = 0;

        for (int i = 0; i < 2 * height + 2; i++) {
            blockCells.add(new BlockCell(x, 0));
            blockCells.add(new BlockCell(x, cellsSize * (2 * width + 2)));
            x += cellsSize;
        }
        for (int i = 0; i < 2 * width + 2; i++) {
            blockCells.add(new BlockCell(0, y));
            blockCells.add(new BlockCell(cellsSize * (2 * height + 2), y));
            y += cellsSize;
        }
        blockCells.add(new BlockCell(cellsSize * (2 * height + 2), cellsSize * (2 * width + 2)));

    }


    private void createMonsters() throws IllegalAccessException, InvocationTargetException, InstantiationException {


        if(monstersCount==0)
        monstersCount = Math.min(width, height);

        if (gameLevel > 4) {
            monstersCount = (int) (monstersCount * Math.pow(1.05, gameLevel - 4));
        }
        List<Class> avilableMonsters = new ArrayList<>();
        if (gameLevel >= 1) {
            avilableMonsters.add(Monster1.class);
        }
        if (gameLevel >= 2) {
            avilableMonsters.add(Monster2.class);
        }
        if (gameLevel >= 3) {
            avilableMonsters.add(Monster3.class);
        }
        if (gameLevel >= 4) {
            avilableMonsters.add(Monster4.class);
        }

        List<Point> monstersLocation = new ArrayList<>();
        for (int i = 0; i < monstersCount; i++) {
            for (BackgroundCell backgroundCell : backgroundCells) {
                Point location = backgroundCell.getLocation();
                if (!wallInLocation(location.x, location.y)
                        && !blockInLocation(location.x, location.y)
                        && !bomberManInLocation(location.x, location.y)) {
                    monstersLocation.add(new Point(location.x, location.y));
                }
            }
        }
        Collections.shuffle(monstersLocation);


        for (int i = 0; i < monstersCount; i++) {
            Collections.shuffle(avilableMonsters);
            Class c = avilableMonsters.get(0);

            monsters.add((Monster) c.getConstructors()[0].newInstance(monstersLocation.remove(0).x, monstersLocation.remove(0).y));

        }


    }

    private void createPowerChangers() throws IllegalAccessException, InvocationTargetException, InstantiationException {


        int powerChangersCount = Math.min(2 * monsters.size(), wallCells.size() / 3);
        Class[] classes = {GhostPower.class,BombController.class, BombIncreaser.class,
                PointIncreaser.class, RadiusIncreaser.class, SpeedIncreaser.class};
        List<Class> increasers = new ArrayList<>(Arrays.asList(classes));
        classes = new Class[]{BombDecreaser.class, PointDecreaser.class,
                RadiusDecreaser.class, SpeedDecreaser.class};
        List<Class> decreasers = new ArrayList<>(Arrays.asList(classes));

        List<BackgroundCell> cells = new ArrayList<>();
        for (BackgroundCell backgroundCell : backgroundCells) {
            if (wallInLocation(backgroundCell.getLocation().x, backgroundCell.getLocation().y)) {
                cells.add(backgroundCell);
            }
        }
        Collections.shuffle(cells);

        //creates gate here
        gate = new Gate(cells.remove(0));

        for (int i = 0; i < Math.min(powerChangersCount / 2, cells.size()); i++) {
            Collections.shuffle(increasers);
            Class c = increasers.get(0);
            powerChangers.add((PowerChanger) c.getDeclaredConstructors()[0].newInstance(cells.remove(0)));
            Collections.shuffle(decreasers);
            Class c2 = decreasers.get(0);
            powerChangers.add((PowerChanger) c2.getDeclaredConstructors()[0].newInstance(cells.remove(0)));          //chera getConstructors null mide???????
        }


    }


    public void save() throws IOException {
        saveBoardSize();
        saveWallPoints();
        saveBombs();
        saveMonsters();
        savePowerChangers();
        saveGate();
    }

    private void saveGate() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput( "gate.txt", Context.MODE_PRIVATE));
        printStream.print(backgroundCells.indexOf(gate.getGateCell()));
    }

    private void savePowerChangers() throws IOException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput( "powerChangers.txt",Context.MODE_PRIVATE));
        for (PowerChanger powerChanger : powerChangers) {
            powerChanger.save(printStream, backgroundCells.indexOf(powerChanger.getCurrentCell()));

        }
    }

    private void saveMonsters() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput("monsters.txt",Context.MODE_PRIVATE));
        for (Monster monster : monsters) {
            monster.save(printStream, backgroundCells.indexOf(monster.getCurrentCell()));
        }
    }

    private void saveBombs() throws IOException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput("bombs.txt",Context.MODE_PRIVATE));
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).save(printStream);
        }
    }

    private void saveWallPoints() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput( "wallPoints.txt",Context.MODE_PRIVATE));
        for (int i = 0; i < wallCells.size(); i++) {
            printStream.print(wallCells.get(i).getLocation().x + " " +
                    wallCells.get(i).getLocation().y + " " + wallCells.get(i).getTimeToBurn() + " ");
        }
    }

    private void saveBoardSize() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(MainActivity.instance.openFileOutput("board.txt",Context.MODE_PRIVATE));
        printStream.print(width + " " + height + " " + cellsSize);
    }


    private void loadObjects() throws IOException {

        loadWallPoints();
        loadBombs();
        loadPowerChangers();
        loadGate();
        loadMonsters();

    }

    private void loadGate() throws FileNotFoundException {
        Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput( "gate.txt"));
        gate = new Gate(backgroundCells.get(scanner.nextInt()));
    }

    private void loadMonsters() {
        try {
            Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput("monsters.txt"));
            while (scanner.hasNext()) {
                Class c = Class.forName(scanner.next());
                Monster monster = (Monster) c.getDeclaredConstructors()[0].newInstance(scanner.nextInt(), scanner.nextInt());
                monster.setLastMove(Side.values()[scanner.nextInt()]);
                monsters.add(monster);
            }
        } catch (FileNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void loadPowerChangers() {
        try {
            Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput("powerChangers.txt"));
            while (scanner.hasNext()) {
                Class c = Class.forName(scanner.next());
                powerChangers.add((PowerChanger) c.getDeclaredConstructors()[0].newInstance(backgroundCells.get(scanner.nextInt())));
            }
        } catch (FileNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ignored) {
        }
    }

    private void loadBombs() throws IOException {
        Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput( "bombs.txt"));
        while (scanner.hasNextInt()) {
            boolean autoExplode = false;
            if (scanner.nextInt() == 1)
                autoExplode = true;
            Bomb bomb = new Bomb(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), autoExplode);
            bomb.setCurrentCell(backgroundCells.get((bomb.getLocation().y / cellsSize) * (2 * height + 3) + (bomb.getLocation().x / cellsSize)));
            bomb.load(scanner);
            bombs.add(bomb);

        }
    }

    private void loadWallPoints() throws IOException {
        Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput("wallPoints.txt"));
        while (scanner.hasNextInt()) {
            WallCell wallCell = new WallCell(scanner.nextInt(), scanner.nextInt());
            wallCell.setTimeToBurn(scanner.nextInt());
            wallCells.add(wallCell);

        }
    }

    private void loadBoardSize() throws FileNotFoundException {
        Scanner scanner = new Scanner(LoadGameActivity.instance.openFileInput("board.txt"));
        width = scanner.nextInt();
        height = scanner.nextInt();
        cellsSize = scanner.nextInt();
    }


    public boolean wallInLocation(int x, int y) {
        for (int i = 0; i < wallCells.size(); i++) {
            if (wallCells.get(i).getLocation().x == x && wallCells.get(i).getLocation().y == y)
                return true;
        }
        return false;
    }

    public boolean blockInLocation(int x, int y) {
        for (BlockCell blockCell : blockCells) {
            if (blockCell.getLocation().x == x && blockCell.getLocation().y == y)
                return true;
        }
        return false;
    }

    public boolean bombInLocation(int x, int y) {
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getLocation().x == x && bombs.get(i).getLocation().y == y)
                return true;
        }
        return false;
    }

    private boolean bomberManInLocation(int x, int y) {
        return (x == bomberManPlace.x) && (y == bomberManPlace.y);
    }


    public void paint(int x, int y, Canvas canvas) {


        for (int i = 0; i < backgroundCells.size(); i++) {
            backgroundCells.get(i).paint(x, y, canvas);
        }
        for (int i = 0; i < blockCells.size(); i++) {
            blockCells.get(i).paint(x, y, canvas);
        }
        for (int i = powerChangers.size() - 1; i >= 0; i--) {
            if (!powerChangers.get(i).isUsed()) {
                powerChangers.get(i).paint(x, y, canvas);
            } else {
                powerChangers.remove(powerChangers.get(i));
            }
        }


        gate.paint(x, y, canvas, monsters.size() == 0);                          //paints gate here


        for (int i = wallCells.size() - 1; i >= 0; i--) {
            if (wallCells.get(i).isDestroyed()) {
                wallCells.remove(wallCells.get(i));
                explodedWallCells++;                                              //used to calculate point in bomberman class
            } else {
                wallCells.get(i).paint(x, y, canvas);
            }
        }

        for (int i = bombs.size() - 1; i >= 0; i--) {
            if (!bombs.get(i).isBurned()) {
                bombs.get(i).paint(x, y, canvas);
            } else {
                bombs.remove(bombs.get(i));
            }
        }

        for (int i = monsters.size() - 1; i >= 0; i--) {
            if (!monsters.get(i).deadCompeletly()) {
                monsters.get(i).paint(x, y, canvas);
            } else {
                if (monsters.get(i).getClass() == Monster1.class)                           //used to calculate point in bomberman class
                    explodedMonsters += 1;
                if (monsters.get(i).getClass() == Monster2.class)
                    explodedMonsters += 2;
                if (monsters.get(i).getClass() == Monster3.class)
                    explodedMonsters += 3;
                if (monsters.get(i).getClass() == Monster4.class)
                    explodedMonsters += 4;

                monsters.remove(monsters.get(i));
            }
        }


    }

    public int getExplodedWallCells() {
        return explodedWallCells;
    }

    public int getExplodedMonsters() {
        return explodedMonsters;
    }

    public void setExplodedWallCells(int explodedWallCells) {
        this.explodedWallCells = explodedWallCells;
    }

    public void setExplodedMonsters(int explodedMonsters) {
        this.explodedMonsters = explodedMonsters;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<WallCell> getWallCells() {
        return wallCells;
    }

    public Point getBomberManPlace() {
        return bomberManPlace;
    }

    public void setBomberManPlace(Point bomberManPlace) {
        this.bomberManPlace = bomberManPlace;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public List<Bomb> getBombs() {
        return bombs;
    }


    public int getCellsSize() {
        return cellsSize;
    }

    public List<BlockCell> getBlockCells() {
        return blockCells;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setCellsSize(int cellsSize) {
        Board.cellsSize = cellsSize;
    }

    public List<BackgroundCell> getBackgroundCells() {
        return backgroundCells;
    }

}
