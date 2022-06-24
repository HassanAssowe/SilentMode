package com.hassanassowe.silentmode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class ScheduleSilentMode extends AppCompatActivity {

    private MaterialDatePicker datePickerStart, datePickerEnd;
    private MaterialTimePicker timePickerStart, timePickerEnd;
    private TextView startDateText, endDateText;
    private Date start, end;


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
        startDateText = findViewById(R.id.start_description);
        endDateText = findViewById(R.id.end_description);

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
                        datePickerStart = MaterialDatePicker.Builder.datePicker()
                                .setTitleText("Select Start Date")
                                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                                .setSelection(Calendar.getInstance().getTimeInMillis())
                                .build();
                        datePickerStart.show(getSupportFragmentManager(), "start_date");

                        datePickerStart.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                            @Override
                            public void onPositiveButtonClick(Long selection) {
                                timePickerStart = new MaterialTimePicker.Builder()
                                        .setTitleText("Select Start Time")
                                        .setTimeFormat(TimeFormat.CLOCK_12H)
                                        .setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                                        .setMinute(Calendar.getInstance().get(Calendar.MINUTE))
                                        .build();
                                timePickerStart.show(getSupportFragmentManager(), "start_time");

                                timePickerStart.addOnPositiveButtonClickListener(dialog -> {
                                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
                                    long time = selection + TimeUnit.HOURS.toMillis(timePickerStart.getHour()) + TimeUnit.MINUTES.toMillis(timePickerStart.getMinute());
                                    long localTime = time - TimeZone.getTimeZone(TimeZone.getDefault().getID()).getOffset(time);
                                    calendar.setTimeInMillis(localTime);
                                    start = calendar.getTime();
                                    //new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(start); NOT WORKING
                                    startDateText.setText(start.toString());

                                });
                            }
                        });


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
                        datePickerEnd.show(getSupportFragmentManager(), "end_date");

                        timePickerEnd = new MaterialTimePicker.Builder()
                                .setTitleText("Select End Time")
                                .setTimeFormat(TimeFormat.CLOCK_12H)
                                .setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                                .setMinute(Calendar.getInstance().get(Calendar.MINUTE))
                                .build();

                        datePickerEnd.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                            @Override
                            public void onPositiveButtonClick(Long selection) {
                                timePickerEnd.addOnPositiveButtonClickListener(dialog -> {
                                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
                                    long time = selection + TimeUnit.HOURS.toMillis(timePickerEnd.getHour()) + TimeUnit.MINUTES.toMillis(timePickerEnd.getMinute());
                                    long localTime = time - TimeZone.getTimeZone(TimeZone.getDefault().getID()).getOffset(time);
                                    calendar.setTimeInMillis(localTime);

                                    end = calendar.getTime();
                                    //new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(end); NOT WORKING
                                    endDateText.setText(end.toString());

                                });

                                timePickerEnd.show(getSupportFragmentManager(), "end_time");
                            }
                        });


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

            }
        });

    }
}