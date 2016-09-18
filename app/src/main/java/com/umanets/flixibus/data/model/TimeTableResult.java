package com.umanets.flixibus.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ko3ak_zhn on 9/16/16.
 */
public class TimeTableResult {
    @SerializedName("line_code")
    public String lineCode;
    @SerializedName("line_direction")
    public String lineDirection;
    public String direction;
    @SerializedName("datetime")
    public FlixDate dateTime;
    public String time;
    @SerializedName("through_the_stations")
    public String throughStations;
    public List<Route> route;
}
