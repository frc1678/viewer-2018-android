package com.example.evan.androidviewertools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lockshaw on 6/13/18.
 */

public class LowpassFilterRunnable implements Runnable {
    private Context context;
    private Set<String> queue;
    private Set<String> actions;
    private static final int LOWPASS_DELAY_MILLIS = 5000;

    public LowpassFilterRunnable(Context context, Set<String> actions) {
        this.context = context;
        this.actions = actions;
        this.queue = new HashSet<>(this.actions.size());
    }

    public static String getFilteredAction(String action) {
        return action + "::filtered";
    }

    @Override
    public void run() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.context);

        BroadcastReceiver queuerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                LowpassFilterRunnable.this.queue.add(intent.getAction());
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        for (String action : this.actions) {
            intentFilter.addAction(action);
        }

        localBroadcastManager.registerReceiver(queuerReceiver, intentFilter);

        while (true) {
            try {
                Thread.sleep(LOWPASS_DELAY_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (String action : this.queue) {
                localBroadcastManager.sendBroadcast(new Intent(LowpassFilterRunnable.getFilteredAction(action)));
            }
            this.queue.clear();
        }
    }
}
