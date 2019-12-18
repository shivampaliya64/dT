package com.example.dt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getStarted = findViewById(R.id.getStarted);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAppListActivity();
            }
        });
    }

    private void startAppListActivity() {
        Intent intent = new Intent(MainActivity.this, Activity2try.class);
        startActivity(intent);
    }
}





