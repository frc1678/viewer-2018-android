package com.example.evan.androidviewertemplates.utils;

import android.content.Intent;

import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.utils.Utils;

public class ViewerDataPoints {
    //todo
    //add methods here for data points local to viewer
    //if u can understand it, follow this example:

    //for data point matchesUntilNextMatchForTeam
    public static Integer getMatchesUntilNextMatchForTeam(Team team, Intent args) {
        Integer currentMatch = Utils.getLastMatchPlayed();
        for (Integer matchNumber : Utils.getMatchNumbersForTeamNumber(team.number)) {
            if (matchNumber > currentMatch) {
                return matchNumber - currentMatch;
            }
        }
        return null;
    }
    //btw args is whatever u want it to be
    public static String getDefenseCrossingTeamDetailsTitle(Team team, Intent args) {
        //this method combines 2 data points into one title for the user
        String ending = args.getStringExtra("defense");
        return "A: " + Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.avgSuccessfulTimesCrossedDefensesAuto." + ending), 1, "???")
                + " T: " + Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.avgSuccessfulTimesCrossedDefensesTele." + ending), 1, "???");
    }
    //this is if u want the data point to have a rank
    public static String getDefenseCrossingTeamDetailsTitleRankingsValue(Intent args) {
        return "calculatedData.avgSuccessfulTimesCrossedDefenses." + args.getStringExtra("defense");
    }
}
