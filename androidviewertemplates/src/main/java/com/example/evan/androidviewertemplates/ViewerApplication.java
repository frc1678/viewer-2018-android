package com.example.evan.androidviewertemplates;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.utils.SpecificConstants;
import com.example.evan.androidviewertools.ViewerApplicationTemplate;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;
import com.example.evan.androidviewertools.services.PhotoSync;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.firebase.FirebaseList;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class ViewerApplication extends ViewerApplicationTemplate {


    @Override
    public void onCreate() {
        super.onCreate();
        //todo
        //instabug is life, hopefully by the time ur reading this u guys have
        // the ultra gold-plated diamonds-studded platinum version, and don't have to use fake emails like me
        new Instabug.Builder(this, "23a9b398eb56dcbf83935c0370994f6a")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();
        startListListeners(getApplicationContext(), com.example.evan.androidviewertemplates.firebase_classes.Match.class, com.example.evan.androidviewertemplates.firebase_classes.Team.class, com.example.evan.androidviewertemplates.firebase_classes.TeamInMatchData.class);
        //setupFirebaseAuth(this);


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
    }


    public static void startListListeners(final Context context, Class<? extends Match> matchClass, Class<? extends Team> teamClass, Class<? extends TeamInMatchData> teamInMatchClass) {
        FirebaseLists.matchesList = new FirebaseList<>(SpecificConstants.MATCHES_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("MATCHES", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(SpecificConstants.MATCHES_UPDATED_ACTION).putExtra("key", key).putExtra("previousValue", previousValue));
            }
        }, matchClass);

        FirebaseLists.teamsList = new FirebaseList<>(SpecificConstants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(SpecificConstants.TEAMS_UPDATED_ACTION).putExtra("key", key));
            }
        }, teamClass);

        FirebaseLists.teamInMatchDataList = new FirebaseList<>(SpecificConstants.TEAM_IN_MATCH_DATAS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS_IN_MATCHES", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(SpecificConstants.TEAM_IN_MATCH_DATAS_UPDATED_ACTION).putExtra("key", key));
            }
        }, teamInMatchClass);
    }


    /*public static void setupFirebaseAuth(final Context context) {
        Firebase firebaseRef = new Firebase(SpecificConstants.ROOT_FIREBASE_PATH);

        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                CharSequence text = "Authenticated!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e("FireBase error", "Failed to auth");
            }
        };

        firebaseRef.authWithCustomToken(SpecificConstants.FIREBASE_KEYS.get(SpecificConstants.ROOT_FIREBASE_PATH), authResultHandler);
    }*/
}
