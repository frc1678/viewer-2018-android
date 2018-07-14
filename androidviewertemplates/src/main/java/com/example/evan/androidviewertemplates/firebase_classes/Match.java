package com.example.evan.androidviewertemplates.firebase_classes;

import com.example.evan.androidviewertools.firebase_classes.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teo on 1/11/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match extends com.example.evan.androidviewertools.firebase_classes.Match {
    public CalculatedMatchData calculatedData;
    //Make sure all variables are public


}
