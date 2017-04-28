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


public class TeamDetailsActivity extends ViewerActivity {

    LinearLayout headerPhotoLinearLayout;
    Integer teamNumber;
    BroadcastReceiver teamUpdatedReceiver;
    BroadcastReceiver photoDownloadedReceiver;
    BroadcastReceiver starReceiver;
    Bitmap bitmap;


    @Override
    public void onCreate() {
        setContentView(R.layout.activity_section_listview);
        setActionBarColor();
        teamNumber = getIntent().getIntExtra("teamNumber", 1678);
        setTitle("Team " + teamNumber + " Details");

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);

        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        View teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.getListView().addHeaderView(teamDetailsHeaderView, null, false);

        teamUpdatedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(teamUpdatedReceiver, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));

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

        headerPhotoLinearLayout = (LinearLayout)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamPhotoLinearLayout);

        reload();
        reloadTeamImage();
    }
    public void setActionBarColor(){
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#65C423"));
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    public void reload() {
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        View teamDetailsHeaderView = teamDetailsHeaderListView.getChildAt(0);
        if (StarManager.isStarredTeam(teamNumber)) {
            teamDetailsHeaderView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            teamDetailsHeaderView.setBackgroundColor(Color.TRANSPARENT);
        }

        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

        TextView teamDetailsTeamNumberTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        teamDetailsTeamNumberTextView.setText(Utils.getDisplayValue(team, "number"));
        teamDetailsTeamNumberTextView.setOnLongClickListener(new StarLongClickListener());

        TextView teamDetailsTeamNameTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        teamDetailsTeamNameTextView.setText(Utils.getDisplayValue(team, "name"));

        TextView teamDetailsSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        teamDetailsSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.actualSeed")) ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.actualSeed"), 2, "???") : "???");

        TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.predictedSeed")) ? Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.predictedSeed"), 2, "???") : "???");
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

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(teamUpdatedReceiver);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(photoDownloadedReceiver);

        if (bitmap != null) {
            bitmap.recycle();
        }

    }

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView teamNumberTextView = (TextView)v;
            Integer teamNumber = Integer.parseInt(teamNumberTextView.getText().toString());
            if (StarManager.isStarredTeam(teamNumber)) {
                StarManager.removeStarredTeam(teamNumber);
            } else {
                StarManager.addStarredTeam(teamNumber);
            }
            reload();
            return true;
        }
    }

    @Override
    public Intent getMainActivityIntent() {
        return new Intent(this, MainActivity.class);
    }
}
