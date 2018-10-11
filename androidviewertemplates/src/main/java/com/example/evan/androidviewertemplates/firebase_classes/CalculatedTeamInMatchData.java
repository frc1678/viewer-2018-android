
package com.example.evan.androidviewertemplates.firebase_classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculatedTeamInMatchData extends Object {
    public ArrayList<Boolean> alliancePlatformIntakeAuto;
    public ArrayList<Boolean> alliancePlatformIntakeTele;
    public ArrayList<Boolean> opponentPlatformIntakeTele;
    public ArrayList<Map<String, Object>> scaleAttemptAuto;
    public ArrayList<Map<String, Object>> scaleAttemptTele;
    public ArrayList<Map<String, Object>> opponentSwitchAttemptTele;
    public ArrayList<Map<String, Map<String, Object>>> climb;
    public ArrayList<Map<String, Object>> allianceSwitchAttemptAuto;
    public ArrayList<Map<String, Object>> allianceSwitchAttemptTele;
    public Boolean canScoreOppositeSwitchAuto;
    public Boolean didClimb;
    public Boolean didConflictWithAuto;
    public Boolean didGetDisabled;
    public Boolean didGetIncapacitated;
    public Boolean didMakeAutoRun;
    public Boolean didPark;
    public Boolean didThreeExchangeInput;
    public Boolean isDysfunctional;
    public Boolean switchIsOpposite;
    public Float avgAllianceSwitchTimeAuto;
    public Float avgAllianceSwitchTimeTele;
    public Float avgOpponentSwitchTimeAuto;
    public Float avgOpponentSwitchTimeTele;
    public Float avgScaleTimeAuto;
    public Float avgScaleTimeTele;
    public Float climbTime;
    public Float drivingAbility;
    public Float timeToOwnAllianceSwitchAuto;
    public Float timeToOwnScaleAuto;
    public Float avgVaultTime;
    public Integer switchOwnership;
    public Integer numCubesScaleAt110s;
    public Integer numCubesScaleAt120s;
    public Integer numCubesScaleAt100s;
    public Integer totalCubesPlaced;
    public Integer cubeAblility;
    public Integer numAlliancePlatformIntakeAuto;
    public Integer numAlliancePlatformIntakeTele;
    public Integer numAllianceSwitchFailedAuto;
    public Integer numAllianceSwitchFailedTele;
    public Integer numAllianceSwitchSuccessAuto;
    public Integer numAllianceSwitchSuccessTele;
    public Integer numBadDecisions;
    public Integer numClimbAttempts;
    public Integer numCubesFumbledAuto;
    public Integer numCubesFumbledTele;
    public Integer numCubesPlacedAuto;
    public Integer numCubesPlacedTele;
    public Integer numElevatedPyramidIntakeAuto;
    public Integer numElevatedPyramidIntakeTele;
    public Integer numExchangeInput;
    public Integer numGoodDecisions;
    public Integer numGroundIntakeTele;
    public Integer numGroundPortalIntakeTele;
    public Integer numGroundPyramidIntakeAuto;
    public Integer numGroundPyramidIntakeTele;
    public Integer numHumanPortalIntakeTele;
    public Integer numOpponentPlatformIntakeAuto;
    public Integer numOpponentPlatformIntakeTele;
    public Integer numOpponentSwitchFailedAuto;
    public Integer numOpponentSwitchFailedTele;
    public Integer numOpponentSwitchSuccessAuto;
    public Integer numOpponentSwitchSuccessTele;
    public Integer numRPs;
    public Integer numReturnIntake;
    public Integer numRobotsLifted;
    public Integer numScaleFailedAuto;
    public Integer numScaleFailedTele;
    public Integer numScaleSuccessAuto;
    public Integer numScaleSuccessTele;
    public Integer numSpilledCubesAuto;
    public Integer numSpilledCubesTele;
    public Integer rankAgility;
    public Integer rankDefense;
    public Integer rankSpeed;
    public Object startingPosition;
    public String scoutName;
    public String superNotes;
    public Float exchangeCycleTime;
    public Float allianceSwitchCycleTimeAuto;
    public Float allianceSwitchCycleTimeTele;
    public Float opponentSwitchCycleTimeTele;
    public Float scaleCycleTimeAuto;
    public Float scaleCycleTimeTele;
}
