package com.example.evan.androidviewertemplates.drawer_fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evan.androidviewertemplates.R;
import com.example.evan.androidviewertools.services.StarManager;
import com.example.evan.androidviewertools.utils.Constants;
import com.example.evan.androidviewertools.utils.firebase.FirebaseList;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;


public class FunctionFragment extends Fragment {
    Context context;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View myLayout = inflater.inflate(R.layout.functionfragment, null);
        final Button teamsOfPicklist = (Button) myLayout.findViewById(R.id.teamsOfPicklist);
        final Button clearedStarredTeams = (Button) myLayout.findViewById(R.id.clearStarredTeams);
        final Button clearHighlightedTeams = (Button) myLayout.findViewById(R.id.clearHighlightedTeams);

        teamsOfPicklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog teamsOfPicklistDialog = new Dialog(getContext());
                teamsOfPicklistDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                teamsOfPicklistDialog.setContentView(R.layout.teamsofpicklist);
                final EditText teamsOfPicklistEditText = (EditText) teamsOfPicklistDialog.findViewById(R.id.teamsOfPicklistEditText);
                Button confirmButton = (Button) teamsOfPicklistDialog.findViewById(R.id.confirmButton);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(teamsOfPicklistEditText.getText().toString()) < FirebaseLists.teamsList.getKeys().size() && teamsOfPicklistEditText.getText()!= null)   {
                            Constants.teamsFromPicklist = Integer.parseInt(teamsOfPicklistEditText.getText().toString());
                            teamsOfPicklistDialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Please make sure the team you've inputed is lower than the amount of teams at the event!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                teamsOfPicklistDialog.show();
            }
        });
        clearedStarredTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StarManager.starredTeams.clear();
                Toast.makeText(getActivity(), "Starred Teams have been cleared.",
                        Toast.LENGTH_LONG).show();
            }
        });
        clearHighlightedTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.highlightedTeams.clear();
                Toast.makeText(getActivity(), "Highlighted Teams have been cleared.",
                        Toast.LENGTH_LONG).show();

            }
        });
        return myLayout;
    }
}

