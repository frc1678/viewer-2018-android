package com.example.evan.androidviewertemplates.firebase_classes;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedTeamData extends Object {
	// 1/16/17
	public Float firstPickAbility;
	public Float overallSecondPickAbility;
	public Float disabledPercentage;
	public Float incapacitatedPercentage;
	public Float avgHighShotsTele;
	public Float avgLowShotsTele;
	public Float avgGearsPlacedTele;
	public Float avgHighShotsAuto;
	public Float avgLowShotsAuto;
	public Float avgGearsPlacedAuto;
	public Float sdGearsPlacedTele;
	public Float sdGearsPlacedAuto;
	public Float sdHighShotsAuto;
	public Float sdHighShotsTele;
	public Float sdLowShotsAuto;
	public Float sdLowShotsTele;
	public Float sdBaselineReachedPercentage;
	public Float avgKeyShotTime;
	public Float avgAgility;
	public Float avgSpeed;
	public Float avgBallControl;
	public Float avgGearControl;
	public Float avgDefense;
	public Float avgDrivingAbility;
	public Float liftoffAbility;
	public Float sdLiftoffAbility;
	public Float liftoffPercentage;
	public Float disfunctionalPercentage;
	public Float predictedNumRPs;
	public Float avgGearsFumbledTele;
	public Float avgGearsEjectedTele;
	public Float avgGearGroundIntakesTele;
	public Float avgLoaderIntakesTele;
	public Float RScoreBallControl;
	public Float RScoreGearControl;
	public Float RScoreAgility;
	public Float RScoreDefense;
	public Float RScoreSpeed;
	public Float avgHoppersOpenedAuto;
	public Float avgHoppersOpenedTele;
	public ArrayList<Map<String,Map<Integer,ArrayList<CalculatedTeamData>>>> firstPickAllRotorsTurningChance;
	public Map<String, Float> hoppersOpenedPercentagesAuto;
	public Map<String, Float> hoppersOpenedPercentagesTele;
	public Integer predictedSeed;
	public Integer actualSeed;
	public Float baselineReachedPercentage;
	public Integer actualNumRPs;
	public Map <String, Integer> avgGearsPlacedByLiftAuto;
	public Map <String, Integer> avgGearsPlacedByLiftTele;
}