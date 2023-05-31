package com.example.fragmentpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ACTIVITY_ONE = 1;
    private static final int REQUEST_ACTIVITY_TWO = 2;
    private static final int REQUEST_ACTIVITY_THREE = 3;
    // Add more constants for other activities if needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        // Add three more buttons for other activities

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        // Set click listeners for other buttons

        // Load the common fragment initially
        loadFragment("DEFAULT FRAGMENT");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String data;
        int requestCode;

        if (v.getId() == R.id.button1) {
            intent = new Intent(MainActivity.this, ActivityOne.class);
            data = "Activity One >> CAR";
            requestCode = REQUEST_ACTIVITY_ONE;
        } else if (v.getId() == R.id.button2) {
            intent = new Intent(MainActivity.this, ActivityTwo.class);
            data = "Activity Two >> BIKE";
            requestCode = REQUEST_ACTIVITY_TWO;
        } else if (v.getId() == R.id.button3) {
            intent = new Intent(MainActivity.this, ActivityThree.class);
            data = "Activity Three >> TRUCK";
            requestCode = REQUEST_ACTIVITY_THREE;
        } else {
            return; // Return if the button ID doesn't match any case
        }

        // Pass the data to the activity
        intent.putExtra("data", data);
        startActivityForResult(intent, requestCode);
    }

    private void loadFragment(String data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}