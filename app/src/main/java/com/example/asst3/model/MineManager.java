package com.example.asst3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MineManager {

    private int rows = 4;
    private int cols = 7;
    private int mines = 8;
    private Mine[][] gameBoard = new Mine[rows][cols];

    private static MineManager instance;


    public MineManager() {
    }


    public static MineManager getInstance() {
        if (instance == null) {
            instance = new MineManager();
        }
        return instance;
    }


    public void putMine() {


        Random rand = new Random();
        int r,c;
        for (int i = 0; i < mines;i++){
            r = rand.nextInt(rows);
            c = rand.nextInt(cols);

            if ( !gameBoard[r][c].isMine() ) {
                gameBoard[r][c].setMine(true);
            }

        }


    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }


    public int getGameBoard() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }


    public Mine getGameBoard(int rows, int cols) {
        return gameBoard[rows][cols];
    }


    public void setGameBoard(Mine[][] gameBoard) {
        this.gameBoard = gameBoard;
    }


}
