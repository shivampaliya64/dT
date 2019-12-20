package com.example.dt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    int Dev_count;
    EditText Device_count;//To count times,the device has been turned on

    Button submitButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dev_count=Integer.valueOf(Device_count.getText().toString());//type casting to integer ,
                Toast.makeText(Settings.this,"Unlock Count set to:"+Dev_count ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    }

