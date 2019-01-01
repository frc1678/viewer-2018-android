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
}
