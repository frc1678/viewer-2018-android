package com.example.evan.androidviewertemplates.firebase_classes;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
    public String name;
    public String number;
    public CalculatedTeamData calculatedData;
    public Integer numMatchesPlayed;
    public String pitSelectedImageName;
	public Map <String,String> pitALLImageURLs;
    public Integer pitAvailableWeight;
    public String pitDriveTrain;
	public Map<String,String> pitImageKeys;
    public Boolean pitDidDemonstrateCheesecakePotential;
    public String pitSEALsNotes;
    public String pitProgrammingLanguage;
    public String pitClimberType;
    public Object pitMaxHeight;
}