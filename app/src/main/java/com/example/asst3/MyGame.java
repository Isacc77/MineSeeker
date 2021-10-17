package com.example.asst3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
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

    Animation rotateAnim;
    Animation anima;

    Button buttons[][];

    int numScan=0;
    int minesFound =0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //game board
        setContentView(R.layout.activity_my_game);

        manager = MineManager.getInstance();

    }




    private void populateButtons() {

        manager = MineManager.getInstance();

        TableLayout table = (TableLayout) findViewById(R.id.tl_table);



        buttons = new Button[manager.getRows()][manager.getColumns()];

        manager.populateMines();



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

                        gridButtonClicked(FINAL_ROW, FINAL_COL);

                    }
                });

                tableRow.addView(button);
                buttons[r][c] = button;
            }
        }
    }







    private void gridButtonClicked(int row, int col) {
        lockButtonSizes();

        Mine temp = manager.getGameBoard(row, col);

        if(temp.isMine()){

            if(){  //first tap show image

                minesFound++;
                numScan++;
                updateUI();


            }else{  //second tap show num



            }

        }else{
            //show num
            numScan++;


        }

    }



    private void updateUI() {

        manager = MineManager.getInstance();

        tv_Mine = findViewById(R.id.tv_numMine);

        tv_Scan = findViewById(R.id.tv_numScan);


        tv_Mine.setText("" + tv_Mine);

        tv_Scan.setText("" + numScan);

        for (int r = 0; r < manager.getRows(); r++) {
            for (int c = 0; c < manager.getCols(); c++) {
                updateRowsAndCols(r, c);
            }
        }
    }




    private void updateRowsAndCols(int row, int col) {

        manager = MineManager.getInstance();

        int count = 0;
        for (int i = 0; i < manager.getCols(); i++) {

            Mine temp = manager.getGameBoard(row, i);


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