package com.example.dt;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    int c = 0;
    Button bla;//represents button at beginning Getting Started.
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bla = findViewById(R.id.button);
        intent = new Intent(this , Activity2try.class);


        bla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!hasPermission(getApplicationContext()))
                {
                    requestPermission(getApplicationContext());

                    if(!hasPermission(getApplicationContext())) {
                       if (c == 1) {
                           finish();
                            System.exit(0);
                       }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Access granted",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }

    public boolean hasPermission (Context context)
    {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        if (appOps != null) {
            int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), context.getPackageName());
            return mode == AppOpsManager.MODE_ALLOWED;
        }
        return false;
    }
    public void requestPermission(Context context) {
        c =1 ;
        context.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));

    }

}





