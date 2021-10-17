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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playGame();

    }



    private void playGame() {
        final Button game=findViewById(R.id.btn_play);

        game.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MyGame.class);
                startActivity(intent);
            }



        });
    }









}







































