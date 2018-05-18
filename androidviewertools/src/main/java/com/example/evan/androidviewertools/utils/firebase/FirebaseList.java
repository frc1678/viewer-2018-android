package com.example.evan.androidviewertools.utils.firebase;

import android.util.Log;

import com.example.evan.androidviewertools.utils.Utils;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.gson.JsonParseException;

import java.util.ArrayList;
import java.util.List;

public class FirebaseList<T> {
    private List<String> keys = new ArrayList<>();
    private List<T> values = new ArrayList<>();
    private ChildEventListener listener;
    Firebase firebase;

    public FirebaseList(String url, FirebaseUpdatedCallback firebaseUpdatedCallback, Class<? extends T> firebaseClass) {
        setupFirebaseListening(url, firebaseClass, firebaseUpdatedCallback);
    }

    public void setupFirebaseListening(final String url, final Class<? extends T> firebaseClass, final FirebaseUpdatedCallback firebaseUpdatedCallback) {
        firebase = new Firebase(url);
        listener = firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
           //     Log.e("datapointError", dataSnapshot.getValue().toString());
                T model = dataSnapshot.getValue(firebaseClass);
                String key = dataSnapshot.getKey();

                // Insert into the correct location, based on s
                if (s == null) {
                    values.add(0, model);
                    keys.add(0, key);
                } else {
                    int previousIndex = keys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == values.size()) {
                        values.add(model);
                        keys.add(key);
                    } else {
                        values.add(nextIndex, model);
                        keys.add(nextIndex, key);
                    }
                }

                firebaseUpdatedCallback.execute(key, null);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("FIREBASE ACTIVITY", "CHILD WAS CHANGED");
                String key = dataSnapshot.getKey();
                T newModel = dataSnapshot.getValue(firebaseClass);
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index));
                } catch (JsonParseException jpe) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }
                values.set(index, newModel);

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e("FIREBASE ACTIVITY", "CHILD WAS REMOVED");
                String key = dataSnapshot.getKey();
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index));
                } catch (JsonParseException jpe) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }

                keys.remove(index);
                values.remove(index);

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.e("FIREBASE ACTIVITY", "CHILD WAS MOVED");
                String key = dataSnapshot.getKey();
                T newModel = dataSnapshot.getValue(firebaseClass);
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index));
                } catch (JsonParseException jpe) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }


                values.remove(index);
                keys.remove(index);
                if (s == null) {
                    values.add(0, newModel);
                    keys.add(0, key);
                } else {
                    int previousIndex = keys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == values.size()) {
                        values.add(newModel);
                        keys.add(key);
                    } else {
                        values.add(nextIndex, newModel);
                        keys.add(nextIndex, key);
                    }
                }

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Firebase List", "Listen was cancelled, no more updates will occur on url: " + url);
                Log.e("Firebase Error", firebaseError.getCode() + "\n" + firebaseError.getMessage() + "\n" + firebaseError.getDetails());
            }
        });
    }

    public interface FirebaseUpdatedCallback {
        void execute(String key, String previousValue);
    }

    public T getFirebaseObjectByKey(String key) {
        return values.get(keys.indexOf(key));
    }

    public List<String> getKeys() {
        return keys;
    }

    public List<T> getValues() {
        return values;
    }

    public void cancelListen() {firebase.removeEventListener(listener);}
}
