package com.hassanassowe.silentmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Main extends AppCompatActivity implements Adapter.ItemClickListener {


    FloatingActionButton floatingActionButton;
    private ArrayList<SilentMode> instances = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instances = new ArrayList<>();
        new Load(instances, this).execute();
        FabMenu();
        initializeRecyclerView();
    }

    // Responsible for expanding the FloatingActionButton (FAB) into a Menu when pressed. Also handles menu interaction & Activity Switching.
    private void FabMenu() {
        //Defining necessary items.
        floatingActionButton = findViewById(R.id.fab);

        //OnClickListener to act on button presses of the FAB
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(Main.this)
                        .setView(R.layout.alert_dialog)
                        .show();

                ConstraintLayout schedule;
                schedule = (ConstraintLayout) dialog.findViewById(R.id.schedule);
                schedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Main.this, ScheduleSilentMode.class);
                        finish();
                        startActivity(intent);

                    }
                });

                ConstraintLayout location;
                location = (ConstraintLayout) dialog.findViewById(R.id.location);
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

            }
        });
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        adapter = new com.hassanassowe.silentmode.Adapter(instances);
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}