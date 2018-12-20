
package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evan on 6/18/16.
 */
/*
@JsonIgnoreProperties(ignoreUnknown = true)
*/
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public CalculatedTeamInMatchData calculatedData;

    public Integer rankSpeed;
    public Integer rankDefense;
    public Integer rankAgility;
    public Integer numGoodDecisions;
    public Integer numBadDecisions;
    public String color;
    public Boolean didCrossAutoLine;
    public Integer position;
    public List<String> scoutName;
    public Integer startedWithCube;
    public List<List<String>> timeline;


}