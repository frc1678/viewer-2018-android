package com.example.evan.androidviewertemplates.firebase_classes;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Teo on 1/11/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class CalculatedMatchData extends Object {
    //Make sure all variables are public
    public Float predictedBlueScore;
    public Float predictedRedScore;
    public Float predictedBlueRPs;
    public Integer actualBlueRPs;
    public Float predictedRedRPs;
    public Integer actualRedRPs;
    public Float predictedBlueAutoQuest;
    public Float predictedRedAutoQuest;
    public Float redWinChance;
    public Float blueWinChance;
    public Float redPredictedPark;
    public Float bluePredictedPark;
    public Float redLevitateProbability;
    public Float blueLevitateProbability;
    public Float redTeleopExchangeAbility;
    public Float blueTeleopExchangeAbilit;
    public Float redPredictedFaceTheBoss;
    public Float bluePredictedFaceTheBoss;

}
