
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
    //Make sure that all variables are public
}
