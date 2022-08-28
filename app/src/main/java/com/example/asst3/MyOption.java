package com.example.asst3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asst3.model.MineManager;
/**
 * This class uses SharedPreferences to store the option on the setting screen.
 * Finally, this data will be transmitted to My Game class.
 * User will get new Game based on the Number of Mines and Game Size he/she selected
 *
 *
 */
public class MyOption extends AppCompatActivity {
    private MineManager manager;
    private TextView tv_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_option);
        manager = MineManager.getInstance();
        tv_notice = findViewById(R.id.tv_notice);
        tv_notice.setSelected(true);
        int savedNumOfMines = getMinesNum(this);
        manager.setMines(savedNumOfMines);
        int savedNumOfRows = getRowsNum(this);
        int savedNumOfCols = getColsNum(this);
        manager.setCols(savedNumOfCols);
        manager.setRows(savedNumOfRows);
        manager.putMine();
        createRadioNumMines();
        createRadioSizes();
        clearMethod();
    }

    private void clearMethod() {
        Button button = findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyOption.this, "Clear successfully", Toast.LENGTH_SHORT).show();
                saveTotal(0);
            }
        });
    }

    private void saveTotal(int total) {
        SharedPreferences pref = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Total Game played", total);
        editor.apply();
    }


    private void createRadioSizes() {
        manager = MineManager.getInstance();
        RadioGroup sizes = findViewById(R.id.rg_size);
        int[] rowOptions = getResources().getIntArray(R.array.gameBoardRows);
        int[] colOptions = getResources().getIntArray(R.array.gameBoardCols);
        // Create the buttons
        for (int i = 0; i < rowOptions.length; i++) {
            final int rowOption = rowOptions[i];
            final int colOption = colOptions[i];
            final RadioButton button = new RadioButton(this);
            button.setText(rowOption + " Rows X " + colOption + " Columns");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setRows(rowOption);
                    manager.setCols(colOption);
                    saveCols(colOption);
                    saveRows(rowOption);
                    manager.putMine();
                }
            });
            // Add to radio group
            sizes.addView(button);
            // Select default button:
            if (rowOption == getRowsNum(this)) {
                button.setChecked(true);
            }
        }
    }


    private void createRadioNumMines() {
        manager = MineManager.getInstance();
        RadioGroup numMines = findViewById(R.id.rg_numMines);
        int[] mineOptions = getResources().getIntArray(R.array.mineNum);
        // Create the buttons
        for (int i = 0; i < mineOptions.length; i++) {
            final int mineOption = mineOptions[i];
            RadioButton button = new RadioButton(this);
            button.setText(mineOption + " mines");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setMines(mineOption);
                    saveMines(mineOption);
                    manager.putMine();
                }
            });
            // Add to radio group
            numMines.addView(button);
            // Select default button:
            if (mineOption == getMinesNum(this)) {
                button.setChecked(true);
            }
        }
    }

    private void saveMines(int mineOption) {
        SharedPreferences pref = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Num of mines choose", mineOption);
        editor.apply();
    }

    private void saveRows(int rowOption) {
        SharedPreferences pref = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Num of rows choose", rowOption);
        editor.apply();
    }

    private void saveCols(int colOption) {
        SharedPreferences pref = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Num of columns choose", colOption);
        editor.apply();
    }

    static public int getMinesNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        return prefs.getInt("Num of mines choose", context.getResources().getInteger(R.integer.defaultNumMines));
    }

    static public int getRowsNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        return prefs.getInt("Num of rows choose", context.getResources().getInteger(R.integer.defaultNumRows));
    }

    static public int getColsNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        return prefs.getInt("Num of columns choose", context.getResources().getInteger(R.integer.defaultNumCols));
    }
}