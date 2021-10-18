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


        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                gameBoard[r][c]=new Mine(0);

            }
        }

        Random rand = new Random();
        int r, c;
        for (int i = 0; i < mines; i++) {
            r = rand.nextInt(rows);
            c = rand.nextInt(cols);
            gameBoard[r][c].setStatus(1);
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
