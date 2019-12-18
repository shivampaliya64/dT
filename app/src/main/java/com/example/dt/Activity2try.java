package com.example.dt;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Activity2try extends Activity {
    private static final String TAG = "Activity2try";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2try);

        ListView appListView = findViewById(R.id.appListView);

        final PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packages = packageManager.
                getInstalledApplications(PackageManager.GET_META_DATA);

        List<ApplicationInfo> userPackages = filterUserPackages(packages);

        AppAdapter appAdapter = new AppAdapter(Activity2try.this, R.layout.list_item,
                userPackages, packageManager);

        appListView.setAdapter(appAdapter);

        /*//Only for debugging purposes.
        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Label: " +packageInfo.loadLabel(packageManager)); //App name for package
            Log.d(TAG, "Installed package :" +packageInfo.packageName); //Package name
            Log.d(TAG, "Source dir : " +packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity : " +packageManager.
                    getLaunchIntentForPackage(packageInfo.packageName));
        }*/
    }

    private List<ApplicationInfo> filterUserPackages(List<ApplicationInfo> packages) {

        List<ApplicationInfo> userApplications = new ArrayList<>(packages);

        for (ApplicationInfo item : packages) {
            if((item.flags & (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_SYSTEM)) > 0) {
                userApplications.remove(item);
            } else {
                //User app. Do nothing.
            }
        }

        return userApplications;
    }
}
