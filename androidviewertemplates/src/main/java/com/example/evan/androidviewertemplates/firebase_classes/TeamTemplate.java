package com.example.evan.androidviewertemplates.firebase_classes;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
    public CalculatedTeamData calculatedData;
    public Integer numMatchesPlayed;
    public String pitSelectedImageName;
    public String pitSelectedImage;
    public Map <String,String> pitAllImageURLs;
    public Map <String,String> pitAllImageUrls; //Todo Delete later.
    public Integer picklistPosition;
    public Integer firstPicklistPosition;
    public Integer secondPicklistPosition;

    public Integer pitAvailableWeight;
    public String pitDriveTrain;
    public Map<String,String> pitImageKeys;
    public Map<String,String> imageKeys; //todo Delete later.

    public Boolean pitCanCheesecake;
    public Boolean pitDidDemonstrateCheesecakePotential;
    public String pitSEALsNotes;
    public String SEALSNotes;
    public String pitProgrammingLanguage;
    public String pitClimberType;
    public Float pitMaxHeight;
    public ArrayList<Float> pitAutoRunTimes;
    public Integer pitAutoRunTime;
    public ArrayList<String> totalSuperNotes;
<<<<<<< HEAD
    public Object pitRampTime;
    public Object pitRampTimes;
    public Object pitDriveTime;
    public Object pitDriveTimes;
=======
    public ArrayList<Object> pitRampTime;
    public ArrayList<Object> pitDriveTime;

>>>>>>> 6b8e485cbceeee25e412f4d452f46ef8bc390d41

}

