package com.example.asst3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MineManager {

    private int rows = 4;
    private int cols = 7;
    private int mines = 8;
    private Mine[][] gameBoard;

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

        gameBoard = new Mine[rows][cols];

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                gameBoard[r][c]=new Mine(0);

            }
        }

        Random rand = new Random();
        int r, c;

        int count = 0;

        while(count != mines){

            r = rand.nextInt(rows);

            c = rand.nextInt(cols);

            if(gameBoard[r][c].getStatus()!= 1 ){
                gameBoard[r][c].setStatus(1);
                count++;
            }

        }

    }


    public int getMines() {
        return mines;
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
