package com.hassanassowe.silentmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.circularreveal.CircularRevealLinearLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Landing extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    CircularRevealLinearLayout menu;
    CoordinatorLayout landing_mainLayout;
    private TextView menu_option1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        FabMenu();
    }

    // Responsible for expanding the FloatingActionButton (FAB) into a Menu when pressed. Also handles menu interaction & Activity Switching.
    private void FabMenu() {
        //Defining necessary items.
        floatingActionButton = findViewById(R.id.fab);
        menu = findViewById(R.id.dial);
        landing_mainLayout = findViewById(R.id.mainLayout);
        menu_option1 = findViewById(R.id.schedule);

        //OnClickListener to act on button presses of the FAB
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if menu is currently visible, list it as expanded and reveal the menu.
                if (menu.getVisibility() == View.INVISIBLE) {
                    menu.setVisibility(View.VISIBLE);
                    floatingActionButton.setExpanded(true);

                    //W.I.P Responsible for dimming background elements. To emphasize the menu
                    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                    layoutParams.dimAmount = 0.75f;
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    getWindow().setAttributes(layoutParams);

                }
            }
        });

        //OnClickListener for exiting the menu. If clicking away, the menu collapses back into the FAB.
        landing_mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floatingActionButton.isExpanded()) {
                    menu.setVisibility(View.VISIBLE);
                    floatingActionButton.setExpanded(false);

                    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                    layoutParams.dimAmount = 0;
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    getWindow().setAttributes(layoutParams);

                }
            }
        });


        //Listener for Menu Option
        menu_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.VISIBLE);
                floatingActionButton.setExpanded(false);
                Intent intent = new Intent(Landing.this, ScheduleSilentMode.class);
                startActivity(intent);
            }
        });
    }

}