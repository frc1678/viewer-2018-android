package com.example.evan.androidviewertemplates.utils;

import android.content.Intent;
import android.util.Log;

import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.utils.Utils;

import org.jcodec.common.DictionaryCompressor;

public class ViewerDataPoints {
    //todo
    //add methods here for data points local to viewer
    //if u can understand it, follow this example:
    //for data point matchesUntilNextMatchForTeam
    public static Integer getMatchesUntilNextMatchForTeam(Team team, Intent args) {
        Integer currentMatch = Utils.getLastMatchPlayed();
        Integer teamNumber = (Integer) Utils.getObjectField(team,"number");
        Log.e("teamNumber", teamNumber + " ");
        for (Integer matchNumber : Utils.getMatchNumbersForTeamNumber((Integer) Utils.getObjectField(team,"number"))) {
            if (matchNumber > currentMatch) {
                Log.e("REACHED", "HERE");
                Integer num = matchNumber - currentMatch;
                return num;
            }
        }
        return null;
    }
}
