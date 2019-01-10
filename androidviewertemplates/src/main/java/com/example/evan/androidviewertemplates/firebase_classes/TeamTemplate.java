
package com.example.evan.androidviewertemplates.firebase_classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teo on 1/10/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamTemplate extends com.example.evan.androidviewertools.firebase_classes.Team {
public CalculatedTeamData calculatedData;
    //Make sure that all variables are public

}
