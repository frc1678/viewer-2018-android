package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;

import java.util.List;

/**
 * Created by sam on 1/16/17.
 */
public class Competition extends Object {
    public Integer currentMatchNum;
    public List<Match> matches;
    public List<Team> teams;
    public List<TeamInMatchData> TIMDs;
    public String code;
}
