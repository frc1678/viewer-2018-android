package com.example.evan.androidviewertools;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;


public abstract class ViewerActivity extends ActionBarActivity {
    //ALL UR ACTIVITIES SHOULD IMPLEMENT THIS!!
    //this prevents headerlistview from crashing with rotations, and enables long presses
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getResources().getConfiguration().orientation == 1) {
            onCreate();
        }
    }
    public abstract void onCreate();
    public abstract Intent getMainActivityIntent();
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(getMainActivityIntent());
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
