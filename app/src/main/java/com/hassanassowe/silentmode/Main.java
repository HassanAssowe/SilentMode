package com.hassanassowe.silentmode;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hassanassowe.silentmode.SharedPreferences.Load;

import java.util.ArrayList;

public class Main extends AppCompatActivity implements Adapter.ItemClickListener {
    //AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

    private final int DO_NOT_DISTURB_PERMISSION_CODE = 1;
    FloatingActionButton floatingActionButton;
    private ArrayList<SilentMode> instances = new ArrayList<>();
    private Adapter adapter;
    private TSnackbar snackbar;
    private View snackbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instances = new ArrayList<>();
        new Load(instances, this).execute();

        snackBar();
        doNotDisturb();
        FabMenu();
        initializeRecyclerView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DO_NOT_DISTURB_PERMISSION_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                if (!(checkPermission())) {
                    snackbar.show();

                }
            }
        }
    }

    private void doNotDisturb() {
        if (!checkPermission()) {
            new MaterialAlertDialogBuilder(this).setTitle("Permission Required")
                    .setMessage("Hush requires access to your devices Do Not Disturb Settings.")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                        startActivityForResult(intent, DO_NOT_DISTURB_PERMISSION_CODE);
                    })
                    .setNegativeButton("Disable Hush", (dialog, which) -> snackbar.show()).show();
        }
    }

    private Boolean checkPermission() {
        NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        return n.isNotificationPolicyAccessGranted();

    }

    private void snackBar() {
        snackbar = TSnackbar.make(findViewById(R.id.constraintlayout), "Hush is currently disabled.", TSnackbar.LENGTH_INDEFINITE);
        snackbarView = snackbar.getView();
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        snackbar.setIconLeft(R.drawable.ic_alert_circle_outline, 24);
        snackbar.setIconPadding(24);
        snackbar.setAction("FIX", view -> {
            snackbar.dismiss();
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult(intent, DO_NOT_DISTURB_PERMISSION_CODE);
        });
        snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#AA0000"));
    }


    // Responsible for expanding the FloatingActionButton (FAB) into a Menu when pressed. Also handles menu interaction & Activity Switching.
    private void FabMenu() {
        //Defining necessary items.
        floatingActionButton = findViewById(R.id.fab);

        //OnClickListener to act on button presses of the FAB
        floatingActionButton.setOnClickListener(v -> {
            AlertDialog dialog = new MaterialAlertDialogBuilder(Main.this)
                    .setView(R.layout.alert_dialog)
                    .show();

            ConstraintLayout schedule;
            schedule = dialog.findViewById(R.id.schedule);
            schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Main.this, ScheduleSilentMode.class);
                    finish();
                    startActivity(intent);

                }
            });

            ConstraintLayout location;
            location = dialog.findViewById(R.id.location);
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        });
    }


    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnTouchListener((v, event) -> true);

        adapter = new com.hassanassowe.silentmode.Adapter(instances);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}