package com.example.evan.androidviewertools.utils;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import com.example.evan.androidviewertools.firebase_classes.Match;
import com.example.evan.androidviewertools.firebase_classes.TeamInMatchData;
import com.example.evan.androidviewertools.utils.firebase.FirebaseLists;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static Object getObjectField(Object object, String field) {
        try {
            List<String> fields = Arrays.asList(field.split("\\."));
            if (fields.size() == 1) {
                return getDirectField(object, field);
            } else {
                Object parent = getObjectField(object, field.substring(0, field.indexOf("." + fields.get(fields.size() - 1))));
                return getDirectField(parent, fields.get(fields.size() - 1));
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static Object getDirectField(Object object, String field) throws Exception {
        if (object instanceof List) {return ((List)object).get(Integer.parseInt(field));}
        return findFieldInInheritedFields(object.getClass(), field).get(object);
    }

    private static Field findFieldInInheritedFields(Class clazz, String field) throws Exception {
        try {
            Field value = clazz.getDeclaredField(field);
            value.setAccessible(true);
            return value;
        } catch (NoSuchFieldException nsfe) {
            return findFieldInInheritedFields(clazz.getSuperclass(), field);
        }
    }

    public static boolean fieldIsNotNull(Object object, String fieldName) {
        return getObjectField(object, fieldName) != null;
    }

    public static Integer getRankOfObject(Object o, List<Object> os, String fieldName) {
        return getRankOfObject(o, os, fieldName, true);
    }

    public static Integer getRankOfObject(Object o, List<Object> os, String fieldName, boolean isReversed) {
        if (Utils.getObjectField(o, fieldName) == null) {
            return null;
        }
        try {
            Collections.sort(os, new ObjectFieldComparator(fieldName, isReversed));
            return os.indexOf(o);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    public static String dataPointToPercentage(Float dataPoint, int decimalPlaces) {
        if (dataPoint != null) {
            return roundDataPoint(dataPoint * 100, decimalPlaces, "??") + "%";
        } else {
            return "???";
        }
    }

    public static String getMatchDisplayValue(Match match, String key) {
        return roundDataPoint(getObjectField(match, key), 2, "???");
    }

    public static String getDisplayValue(Object object, String key) {
        return roundDataPoint(getObjectField(object, key), 2, "???");
    }

    public static String roundDataPoint(Object dataPoint, int decimalPlaces, String unkownValue) {
        if (dataPoint == null) {
            return unkownValue;
        }
        int decimalPointIndex = dataPoint.toString().indexOf(".");
        if (decimalPointIndex < 0) {
            return  dataPoint.toString();
        }
        int substringIndex = dataPoint.toString().indexOf(".") + 1 + decimalPlaces;
        if (decimalPlaces < 1) {
            substringIndex--;
        }
        return dataPoint.toString().substring(0, Math.min(substringIndex, dataPoint.toString().length()));
    }

    public static Integer getLastMatchPlayed() {
        Integer lastMatch = 0;
        for (Match match : FirebaseLists.matchesList.getValues()) {
            Integer redScore = (Integer)(Utils.getObjectField(match,"redScore"));
            Integer blueScore = (Integer)(Utils.getObjectField(match,"blueScore"));
            if(redScore != null || blueScore != null) {
                lastMatch = ((Integer)(Utils.getObjectField(match,"number")));
            }
        }

        return lastMatch;
    }

    public static SpannableString highlightTextInString(String fullString, String toHighlight) {
        SpannableString spannableString = new SpannableString(fullString);
        int index = fullString.indexOf(toHighlight);
        if (index == 0) {
            spannableString.setSpan(new BackgroundColorSpan(Color.GREEN), fullString.indexOf(toHighlight), fullString.indexOf(toHighlight) + toHighlight.length(), 0);
        }
        return spannableString;
    }

    public static List<TeamInMatchData> getTeamInMatchDatasForTeamNumber(Integer teamNumber) {
        List<TeamInMatchData> teamInMatchDatas = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : FirebaseLists.teamInMatchDataList.getValues()) {
            Log.e("teamNumber", Integer.toString(teamNumber));
            Integer number = (Integer) Utils.getObjectField(teamInMatchData,"teamNumber");
            //Log.e("number", Integer.toString(number));
            //DRINK BLEACH
            try {
                if (number.equals(teamNumber)) {
                    teamInMatchDatas.add(teamInMatchData);
                }
            }catch (NullPointerException NPE){
                Log.e("team Number", "NULL");
            }
        }

        Collections.sort(teamInMatchDatas, new ObjectFieldComparator("matchNumber", true));
        return teamInMatchDatas;
    }//DRINK BLEACH//DRINK BLEACH

    public static List<Integer> getMatchNumbersForTeamNumber(Integer teamNumber) {
        List<Integer> matchNumbers = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : getTeamInMatchDatasForTeamNumber(teamNumber)) {
            Integer matchNumber = (Integer) Utils.getObjectField(teamInMatchData,"matchNumber");
            matchNumbers.add(matchNumber);
        }

        return matchNumbers;
    }


    //gson is a literal god, tasteless
    private static final Gson gson = new Gson();
    public static String serializeClass(Object object) throws JsonParseException {return gson.toJson(object);}
    public static Object deserializeClass(String serializedClass, Class<?> clazz) throws  JsonParseException {
        return gson.fromJson(serializedClass, clazz);
    }
    //do I use reflection too much? probably
    //anyway this method is used to display data points that aren't on firebase.  Basically it calls a getter method for the field on the utils class
    public static Object getViewerObjectField(Object object, String fieldName, Intent args, Class<?> viewerDataPointsClass) {
        try {
            Method method = viewerDataPointsClass.getMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1), object.getClass(), Intent.class);
            return method.invoke(viewerDataPointsClass.newInstance(), object, args);
        } catch (Exception e) {
            Log.e("Method error", "Requested viewer object that doesn't exist!");
            return null;
        }
    }
    public static String getViewerObjectFieldRank(String fieldName, Intent args, Class<?> viewerDataPointsClass) {
        try {
            Method method = viewerDataPointsClass.getMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "RankingsValue", Intent.class);
            return (String)method.invoke(viewerDataPointsClass.newInstance(), args);
        } catch (Exception e) {
            Log.e("Method error", "Requested viewer object that doesn't exist!");
            return null;
        }
    }
}
