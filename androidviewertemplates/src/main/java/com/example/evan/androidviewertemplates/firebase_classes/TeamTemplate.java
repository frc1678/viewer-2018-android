package com.example.evan.androidviewertemplates.firebase_classes;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/11/2018.
 */

public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team{
    //Make sure all variables are public
    public String name;
    public int Number;
    public CalculatedTeamData calculatedData;
    public int numMatchesPlayed;
    public String pitSelectedImageName;
    public ArrayList<Map<String,String>> pitAllImageURLs;
    public int pitAvailableWeight;
    public String pitDriveTrain;
    public ArrayList<Map<String, String>> pitImageKeys; //Could be String?
    public boolean pitDidDemonstrateCheesecakePotential;
    public String pitSEALSnote;
    public String pitProgrammingLanguage;
    public Map<String, String> pitClimberType;
    public Float pitMaxHeight;
}



