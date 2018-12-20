
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
@JsonIgnoreProperties(ignoreUnknown = false)
public class TeamInMatchData extends com.example.evan.androidviewertools.firebase_classes.TeamInMatchData {
    public CalculatedTeamInMatchData calculatedData;

    public Integer rankSpeed;

    public Object startingPosition; //todo Change V


    // blue
    public Boolean switchIsOpposite;

    public ArrayList<Map<String, Object>> opponentSwitchAttemptTele;
    public ArrayList<Map<String, Object>> scaleAttemptAuto;
    public ArrayList<Map<String, Object>> scaleAttemptTele;
    public Integer numGoodDecision;
    public Integer numBadDecision;
    public Integer numSpeed;
    public Integer numAgility;
    public Integer numDefense;
    public String color;
    public ArrayList<ArrayList<Object>> timeline;
    public Boolean didCrossAutoLine;
    public Integer position;
    public Integer startedWithCube;




}
