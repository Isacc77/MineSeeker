package com.example.asst3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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

        manager = MineManager.getInstance();

        tv_totalMines = findViewById(R.id.tv_total);
        tv_totalMines.setText("Total Intruders: " + manager.getMines());

        populateButtons();


        updateUI();




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
/////////////////////////////////////////////////////////////////////////////////////////////////
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //    java.lang.NullPointerException: Attempt to invoke virtual method 'int android.graphics.Bitmap.getWidth()' on a null object reference
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
            temp.setStatus(2);
            showMine(row,col);
            updateUI();

        //second tap mine
        }else if(temp.getStatus() == 2){
            temp.setStatus(3);
            updateUI();

        //more than two times
        }else if(temp.getStatus() >2){


        }//not mine
        else{
            numScan++;
            temp.setStatus(3);
            updateUI();

        }

    }



    private void showMine(int row, int col) {
        manager = MineManager.getInstance();

        Button button = buttons[row][col];


        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_man);
        //java.lang.NullPointerException: Attempt to invoke virtual method 'int android.graphics.Bitmap.getWidth()' on a null object reference
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

//        button.setAlpha(1);

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

        tv_Mine.setText("intruders Found: " + tv_Mine);
        tv_Scan.setText("Scans: " + numScan);

        for (int r = 0; r < manager.getRows(); r++) {
            for (int c = 0; c < manager.getCols(); c++) {
                rowAndColResponse(r, c);
            }
        }
    }


    private void rowAndColResponse(int row, int col) {

        manager = MineManager.getInstance();

        int count = 0;
        //////////////////////////////////////////////////////////////////////

        for (int i = 0; i < manager.getCols(); i++) {

            Mine temp = manager.getGameBoard(row, i);

            if (temp.getStatus()== 1) {
                count++;
            }
        }


        for (int j = 0; j < manager.getRows(); j++) {
            Mine temp = manager.getGameBoard(j, col);
            if (temp.getStatus()== 1) {
                count++;
            }
        }



        if (minesFound >= manager.getMines()) {
            if (manager.getGameBoard(row, col).getStatus()==2) {
                Button button = buttons[row][col];
                button.setText("" + count);
                button.setAlpha(1);
            }

            gameOver();

        } else {
            if (manager.getGameBoard(row, col).getStatus()==2) {
                Button button = buttons[row][col];
                button.setText("" + count);
                button.setTextSize(10);
                if (manager.getGameBoard(row, col).getStatus()==2) {
                    button.setTextColor(Color.parseColor("#FFA652"));
                }
            }
        }


//////////////////////////////////////////////////////////////////////

    }

    private void gameOver() {
        FragmentManager fM = getSupportFragmentManager();
        MyDialog dialog = new MyDialog();
        dialog.show(fM, "End Game");
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