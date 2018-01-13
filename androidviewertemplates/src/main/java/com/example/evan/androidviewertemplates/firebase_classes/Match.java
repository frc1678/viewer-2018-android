package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.example.evan.androidviewertemplates.firebase_classes.CalculatedTeamData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teo on 1/11/18.
 */
public class Match extends com.example.evan.androidviewertools.firebase_classes.Match {
    public CalculatedMatchData calculatedData;
    //Make sure all variables are public
    public int number;
    public ArrayList<String> redAllianceTeamNumbers;
    public ArrayList<String> blueAllianceTeamNumbers;
    public Map<String, String> blueCubesForPowerup;
    public Map<String, String> blueCubesInVaultFinal;
    public Boolean blueDidAutoQuest;
    public Boolean blueDidFaceBoss;


    public Map<String, String> redCubesForPowerup;
    public Map<String, String> redCubesInVaultFinal;
    public Boolean redDidAutoQuest;
    public Boolean redDidFaceBoss;
    public Map<String, String> redSwitch;
    public Map<String, String> scale;
    public int redScore;

    public int blueScore;
    public int foulPointsGainedRed;
    public int foulPointsGainedBlue;

}
