package com.example.asst3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MyDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.activity_my_dialog,null);

        ImageView iv_1 = v.findViewById(R.id.iv_1);
        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.eat_apple2));

        //Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };

        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over!")
                .setView(v)
                .setPositiveButton(android.R.string.ok,listener)
                .create();
    }
}