package com.example.evan.androidviewertemplates.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;
import com.example.evan.androidviewertemplates.MainActivity;
import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.ViewerActivity;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.Utils;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;


import java.io.File;
import java.io.FileInputStream;


public class TeamDetailsActivity extends TeamActivity {
    BroadcastReceiver photoDownloadedReceiver;
    Bitmap bitmap;

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("Team " + teamNumber + " Details");

        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));

        photoDownloadedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer intentTeamNumber = intent.getIntExtra("teamNumber", 0);
                if (intentTeamNumber.equals(teamNumber)) {
                    reloadTeamImage();
                }
            }
        };


        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(photoDownloadedReceiver, new IntentFilter(Constants.NEW_TEAM_PHOTO_ACTION));

        reload();
        reloadTeamImage();
    }


    public void reloadTeamImage() {
        Log.e("reload", "team image");
        try {
            File file = new File(getApplicationContext().getFilesDir(), "image_" + teamNumber.toString());

            Bitmap tmpBitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            if (tmpBitmap == null) {
                Log.e("Picture Error", "Failed to parse bitmap from file");
                return;
            }
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            Float scale =  (float) metrics.widthPixels / (float)tmpBitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            bitmap = Bitmap.createBitmap(tmpBitmap, 0, 0,tmpBitmap.getWidth(), tmpBitmap.getHeight(), matrix, true);

            ImageView imageView = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(imageParams);
            imageView.setImageBitmap(bitmap);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            headerPhotoLinearLayout.addView(imageView, params);
        } catch (Exception e) {
            Log.e("test", "ERROR: " + e.getMessage());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(photoDownloadedReceiver);

        if (bitmap != null) {
            bitmap.recycle();
        }

    }

}
