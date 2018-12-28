package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/11/18.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Match extends com.example.evan.androidviewertools.firebase_classes.Match {
    public CalculatedMatchData calculatedData;

    //Make sure all variables are public

    public Map<String, Integer> blueCubesForPowerup;
    public Map<String, Integer> blueCubesInVaultFinal;
    public Boolean blueDidAutoQuest;
    public Boolean blueDidFaceBoss;
    public Boolean blueDidiFaceBoss; //todo Delete Later.

    public Map<String, Integer> redCubesForPowerup;
    public Map<String, Integer> redCubesInVaultFinal;
    public Boolean redDidAutoQuest;
    public Boolean redDidFaceBoss;
    public Boolean redDidiFaceBoss; //todo Delete Later.
    public Map<String, String> redSwitch;
    public Map<String, String> blueSwitch;
    public Map<String, String> scale;

    public Integer foulsPointsGainedred;
    public Integer foulsPointsGainedblue;


}
