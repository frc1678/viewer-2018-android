package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.firebase_classes.Team;
import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by sam on 1/16/17.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Competition extends Object {
    public String code;
    public List<Team> teams;
    public List<Match> matches;
    public List<TeamInMatchData> TIMDs;
    public Integer currentMatchNum;



}
