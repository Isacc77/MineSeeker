package com.example.asst3;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.asst3.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


/**
 * This class provide menu and three button to go to different activities (game,setting,help)
 *
 */


public class MainActivity extends AppCompatActivity {


    private Button btnGame;
    private Button btnSetting;
    private Button btnHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGame = findViewById(R.id.btn_play);
        btnSetting = findViewById(R.id.btn_setting);
        btnHelp = findViewById(R.id.btn_help);
        setListeners();
    }


    private void setListeners() {
        OnClick onClick = new OnClick();
        btnGame.setOnClickListener(onClick);
        btnSetting.setOnClickListener(onClick);
        btnHelp.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener {

        Intent intent = null;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_play:
                    intent = new Intent(MainActivity.this, MyGame.class);
                    break;

                case R.id.btn_setting:
                    intent = new Intent(MainActivity.this, MyOption.class);
                    break;

                case R.id.btn_help:
                    intent = new Intent(MainActivity.this, MyHelp.class);
                    break;

            }
            startActivity(intent);
        }

    }


}







































