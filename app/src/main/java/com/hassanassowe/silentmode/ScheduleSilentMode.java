package com.hassanassowe.silentmode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleSilentMode extends AppCompatActivity {

    private MaterialDatePicker datePickerStart, datePickerEnd;
    private MaterialTimePicker timePickerStart, timePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_silent_mode);

        topAppBar();
        dateSelection();
    }

    //Top App Bar management
    private void topAppBar() {
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);

        setSupportActionBar(topAppBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        topAppBar.setNavigationOnClickListener(
                v -> finish()
        );
    }

    //This method implements a MATERIAL Date & time picker that represents the start date to begin silent mode.
    private void dateSelection() {
        ConstraintLayout startDate = findViewById(R.id.start_date);
        ConstraintLayout endDate = findViewById(R.id.end_date);

        //Set OnClickListern for the layout, when pressed inflate Date & Time Picker.
        startDate.setOnClickListener(v -> new Thread() {
            public void run() {
                try {
                    runOnUiThread(() -> {
                        datePickerStart = MaterialDatePicker.Builder.datePicker().setTitleText("Select Start Date")
                                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                                .build();
                        datePickerStart.show(getSupportFragmentManager(), "start_date");

                        timePickerStart = new MaterialTimePicker.Builder()
                                .setTitleText("Select Start Time")
                                .setTimeFormat(TimeFormat.CLOCK_12H)
                                .setHour(12)
                                .setMinute(10)
                                .build();

                        timePickerStart.show(getSupportFragmentManager(), "start_time");
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start());

        endDate.setOnClickListener(v -> new Thread() {
            public void run() {
                try {
                    runOnUiThread(() -> {
                        datePickerEnd = MaterialDatePicker.Builder.datePicker().setTitleText("Select Start Date")
                                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                                .build();
                        datePickerEnd.show(getSupportFragmentManager(), "start_date");

                        timePickerEnd = new MaterialTimePicker.Builder()
                                .setTitleText("Select Start Time")
                                .setTimeFormat(TimeFormat.CLOCK_12H)
                                .setHour(12)
                                .setMinute(10)
                                .build();

                        timePickerEnd.show(getSupportFragmentManager(), "start_time");
                    });
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start());

    }

    //Responsible for confirming selections and creating silent mode instance. Also issues errors
    private void confirmation() {
        Button confirm = findViewById(R.id.confirmButton);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date selectedDates = new Date((Long) datePickerStart.getSelection());
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMM yyyy");
                simpleFormat.format(selectedDates);


            }
        });

    }
}