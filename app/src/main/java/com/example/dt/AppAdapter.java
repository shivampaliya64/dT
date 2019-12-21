package com.example.dt;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter {

    private final LayoutInflater layoutInflater;
    private final int layoutResource;
    private final List<ApplicationInfo> packages;
    private final PackageManager packageManager;

    public AppAdapter(@NonNull Context context, int resource, List<ApplicationInfo> packages,
                      PackageManager packageManager) {
        super(context, resource, packages);
        layoutInflater = LayoutInflater.from(context);
        layoutResource = resource;
        this.packages = packages;
        this.packageManager = packageManager;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ApplicationInfo packageInfo = packages.get(position);

        viewHolder.appNameTV.setText(packageInfo.loadLabel(packageManager));
        viewHolder.appIcon.setImageDrawable(packageInfo.loadIcon(packageManager));

        return convertView;
    }

    private class ViewHolder {
        final TextView appNameTV;
        final ImageView appIcon;

        ViewHolder(View v) {
            this.appNameTV = v.findViewById(R.id.appNameTV);
            this.appIcon = v.findViewById(R.id.appIcon);
        }
    }
}
