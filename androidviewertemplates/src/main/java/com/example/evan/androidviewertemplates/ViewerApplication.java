package com.example.evan.androidviewertemplates;

import android.content.Intent;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.firebase_classes.TeamTemplate;
import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.LowpassFilterRunnable;
import com.example.evan.androidviewertools.ViewerApplicationTemplate;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;
import com.example.evan.androidviewertools.services.PhotoSync;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.firebase.FirebaseList;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ViewerApplication extends ViewerApplicationTemplate {

    final Thread.UncaughtExceptionHandler originalUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    @Override
    public void onCreate() {
        super.onCreate();
        //todo
        startListListeners(com.example.evan.androidviewertemplates.firebase_classes.Match.class, TeamTemplate.class, com.example.evan.androidviewertemplates.firebase_classes.TeamInMatchData.class);
        //setupFirebaseAuth(this);
        new Instabug.Builder(this, "8acf9a491975145b686561255f5e3410")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();
        restoreFromSharedPreferences();
        startService(new Intent(this, new StarManager() {
            @Override
            public Class<? extends Match> getMatchClass() {
                return com.example.evan.androidviewertemplates.firebase_classes.Match.class;
            }

            @Override
            public Class<?> getMainActivityClass() {
                return MainActivity.class;
            }
        }.getClass()));
        startService(new Intent(this, PhotoSync.class));

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                handleUncaughtException(thread, e);
            }
        });
    }


    public void startListListeners(Class<? extends Match> matchClass, Class<? extends Team> teamClass, Class<? extends TeamInMatchData> teamInMatchClass) {
        FirebaseLists.matchesList = new FirebaseList<>(SpecificConstants.MATCHES_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("MATCHES", key);
                LocalBroadcastManager.getInstance(ViewerApplication.this.getApplicationContext()).sendBroadcast(new Intent(SpecificConstants.RAW_MATCHES_UPDATED_ACTION).putExtra("key", key).putExtra("previousValue", previousValue));
            }
        }, matchClass);

        FirebaseLists.teamsList = new FirebaseList<>(SpecificConstants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS", key);
                LocalBroadcastManager.getInstance(ViewerApplication.this.getApplicationContext()).sendBroadcast(new Intent(SpecificConstants.RAW_TEAMS_UPDATED_ACTION).putExtra("key", key));
            }
        }, teamClass);

        FirebaseLists.teamInMatchDataList = new FirebaseList<>(SpecificConstants.TEAM_IN_MATCH_DATAS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS_IN_MATCHES", key);
                LocalBroadcastManager.getInstance(ViewerApplication.this.getApplicationContext()).sendBroadcast(new Intent(SpecificConstants.RAW_TEAM_IN_MATCH_DATAS_UPDATED_ACTION).putExtra("key", key));
            }
        }, teamInMatchClass);

        Set<String> firebaseUpdatedActions = new HashSet<>();
        firebaseUpdatedActions.add(SpecificConstants.RAW_MATCHES_UPDATED_ACTION);
        firebaseUpdatedActions.add(SpecificConstants.RAW_TEAMS_UPDATED_ACTION);
        firebaseUpdatedActions.add(SpecificConstants.RAW_TEAM_IN_MATCH_DATAS_UPDATED_ACTION);
        LowpassFilterRunnable firebaseUpdatedRunnable = new LowpassFilterRunnable(this.getApplicationContext(), firebaseUpdatedActions);
        new Thread(firebaseUpdatedRunnable).start();
    }

    private void handleUncaughtException(Thread thread, Throwable e) {
        // The following shows what I'd like, though it won't work like this.
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            printWriter.close();
            Log.e("UI thread", "CRASHED!");
            originalUncaughtExceptionHandler.uncaughtException(thread, e);
        } else {
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            String stacktrace = result.toString();
            Log.e("Background thread", "CRASHED");
            Toast.makeText(this, "Background Thread ERROR", Toast.LENGTH_LONG).show();
        }

    }
}
