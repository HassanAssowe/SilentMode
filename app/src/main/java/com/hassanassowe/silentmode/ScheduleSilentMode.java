package com.hassanassowe.silentmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ScheduleSilentMode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_silent_mode);

        cancel();
        dateSelection();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScheduleSilentMode.this, Main.class);
        finish();
        startActivity(intent);
    }


    //This method implements a MATERIAL Date & time picker that represents the start date to begin silent mode.
    private void dateSelection() {


    }

    //Responsible for confirming selections and creating silent mode instance. Also issues errors
    private void confirm() {
        Button confirm = findViewById(R.id.confirm_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void cancel() {
        Button cancel = findViewById(R.id.cancel_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleSilentMode.this, Main.class);
                finish();
                startActivity(intent);
            }
        });
    }
}