package com.example.dt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Activity2try extends Activity {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private AppAdapter listadapter = null;


    boolean mIncludeSystemApps;//a flag to indicate weather to include system apps in the list
    Intent intent2;//for moving to settings activity tab
    ImageButton Settings_button;
    ImageButton Refresh_Button;
    ImageButton Sort_Button;
    String[] sort_options;//variable to store availabe options ,to be viewed when clicking the sort button,
    //Button mBtn;
    TextView mTextView;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2try);

        Settings_button = findViewById(R.id.imageButton);
        Refresh_Button = findViewById(R.id.imageButton2);
        Sort_Button = findViewById(R.id.imageButton3);
        listView = findViewById(R.id.list);
        intent2 = new Intent(this, Settings.class);

        new LoadApplications().execute();

        Settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        Sort_Button.setOnClickListener(new View.OnClickListener() {//to genrate dialog box when sort icon is clicked
            @Override
            public void onClick(View v) {
                sort_options = new String[]{"Alphabetically", "Most Used In terms of Time", "Most Used in terms of Count"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Activity2try.this);//alert box is genrated in Activity2try tab

                mBuilder.setTitle("Choose the sorting");//to set title of AlertDialog
                mBuilder.setIcon(R.drawable.icon);//to set icon of AlertDialog
                mBuilder.setSingleChoiceItems(sort_options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTextView.setText(sort_options[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }

        });


    }

/*    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);
        try {
            Intent intent3 = packageManager.getLaunchIntentForPackage(app.packageName);
            if (intent3 != null) {
                startActivity(intent3);
            }
        }
        catch (ActivityNotFoundException e){
            Toast.makeText(Activity2try.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(Activity2try.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }*/

    private List<ApplicationInfo>checkForLaunchIntent(List<ApplicationInfo>list){
        ArrayList<ApplicationInfo> appList=new ArrayList<ApplicationInfo>();
        for(ApplicationInfo info:list){
            try {
                if(packageManager.getLaunchIntentForPackage(info.packageName)!=null){
                    appList.add(info);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return appList;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(packageManager.GET_META_DATA));
            listadapter = new AppAdapter(Activity2try.this, R.layout.list_item, applist);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listView.setAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        /*protected void onPreExecute() {
            progress = ProgressDialog.show(Activity2try.this, null, "Loading apps info...");
            super.onPreExecute();
        }*/
    }
}
