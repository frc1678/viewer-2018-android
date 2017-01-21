package com.example.evan.androidviewertemplates.team_details;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.example.evan.androidviewertemplates.MainActivity;

/**
 * Created by sam on 1/21/17.
 */
public abstract class ViewerActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("ORIENTATION", getResources().getConfiguration().orientation + "");
        if (getResources().getConfiguration().orientation == 1) {
            onCreate();
        }
    }
    public abstract void onCreate();
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
