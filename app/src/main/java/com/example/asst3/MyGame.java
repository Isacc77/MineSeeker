package com.example.asst3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.asst3.model.Mine;
import com.example.asst3.model.MineManager;

/**
 * MyGame class provide the game screen
 * It can show the GameBoard and Game data (Number of Game played, Number of total Mines, Number of Mines found, Number of scan used )
 * Citation: control 2D array inspired by https://www.cnblogs.com/thomasbc/p/6907573.html
 */

public class MyGame extends AppCompatActivity {
    MineManager manager;
    TextView tv_Mine;
    TextView tv_Scan;
    TextView tv_totalMines;
    TextView tv_numOfGame;
    Button buttons[][];
    int numScan = 0;
    int minesFound = 0;
    Animation rotateAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //game board
        setContentView(R.layout.activity_my_game);
        checkSharedPreferences();
        manager = MineManager.getInstance();
        tv_totalMines = findViewById(R.id.tv_totalMines);
        tv_totalMines.setText("Total Intruders: " + manager.getMines());
        populateButtons();
        updateUI();
    }

    private void checkSharedPreferences() {
        tv_numOfGame = findViewById(R.id.tv_numGame);
        manager = MineManager.getInstance();
        int savedTotal = getNumGamePlayed(this);
        int savedMines = getMinesNum(this);
        int savedRows = getRowsNum(this);
        int savedCols = getColsNum(this);
        savedTotal++;
        tv_numOfGame.setText("Game Played: " + savedTotal);
        saveTotal(savedTotal);
        manager.setMines(savedMines);
        manager.setCols(savedCols);
        manager.setRows(savedRows);
    }


    static public int getNumGamePlayed(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        return prefs.getInt("Total Game played", 0);
    }


    static public int getMinesNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.defaultNumMines);
        return prefs.getInt("Num of mines choose", defaultValue);
    }

    static public int getRowsNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.defaultNumRows);
        return prefs.getInt("Num of rows choose", defaultValue);
    }

    static public int getColsNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.defaultNumCols);
        return prefs.getInt("Num of columns choose", defaultValue);
    }

    private void saveTotal(int total) {
        SharedPreferences pref = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Total Game played", total);
        editor.apply();
    }


    private void populateButtons() {
        manager = MineManager.getInstance();
        TableLayout table = (TableLayout) findViewById(R.id.tl_table);
        buttons = new Button[manager.getRows()][manager.getCols()];
        manager.putMine();

        for (int r = 0; r < manager.getRows(); r++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int c = 0; c < manager.getCols(); c++) {
                final int FINAL_COL = c;
                final int FINAL_ROW = r;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                button.setPadding(0, 0, 0, 0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[r][c] = button;
            }
        }
    }


    private void gridButtonClicked(int row, int col) {
        Mine temp = manager.getGameBoard(row, col);
        lockButtonSizes();
        //first tap mine
        if (temp.getStatus() == 1) {
            minesFound++;
            numScan++;
            showMine(row, col);
            temp.setStatus(2);
            updateUI();
            //second tap mine
        } else if (temp.getStatus() == 2) {
            showInt(row, col);
            temp.setStatus(3);
            updateUI();
            //more than two times
        } else if (temp.getStatus() > 2) {
        }//not mine
        else {
            numScan++;
            showInt(row, col);
            temp.setStatus(4);
            updateUI();
        }
    }

    private void showInt(int row, int col) {
        Button button = buttons[row][col];
        Button temp = button;
        temp.setText("" + cal_mine(row, col));
        temp.setTextColor(Color.parseColor("#FF0117"));
        temp.setAlpha(1);
    }

    private void showMine(int row, int col) {
        manager = MineManager.getInstance();
        Button button = buttons[row][col];
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_man3);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        if (minesFound < manager.getMines()) {
            button.startAnimation(rotateAnim);
        }
        button.setText("");
    }

    private void updateUI() {
        manager = MineManager.getInstance();
        tv_Mine = findViewById(R.id.tv_numMine);
        tv_Scan = findViewById(R.id.tv_numScan);
        tv_Mine.setText("Intruders Found: " + minesFound);
        tv_Scan.setText("Scans: " + numScan);
        for (int i = 0; i < manager.getRows(); i++) {
            for (int j = 0; j < manager.getCols(); j++) {

                if (manager.getGameBoard(i, j).getStatus() > 2) {
                    Button button = buttons[i][j];
                    button.setText("" + cal_mine(i, j));
                }
            }
        }
        gameOver();
    }

    // Citation: control 2D array inspired by https://www.cnblogs.com/thomasbc/p/6907573.html
    private int cal_mine(int r, int c) {
        manager = MineManager.getInstance();
        int count = 0;
        for (int i = 0; i < manager.getCols(); i++) {
            Mine temp = manager.getGameBoard(r, i);
            if (temp.getStatus() == 1) {
                count++;
            }
        }

        for (int j = 0; j < manager.getRows(); j++) {
            Mine temp = manager.getGameBoard(j, c);
            if (temp.getStatus() == 1) {
                count++;
            }
        }
        return count;
    }

    private void gameOver() {
        if (minesFound == manager.getMines()) {
            FragmentManager fM = getSupportFragmentManager();
            MyDialog dialog = new MyDialog();
            dialog.show(fM, "End Game");
        }
    }

    private void lockButtonSizes() {
        manager = MineManager.getInstance();
        for (int i = 0; i < manager.getRows(); i++) {
            for (int j = 0; j < manager.getCols(); j++) {
                Button button = buttons[i][j];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}