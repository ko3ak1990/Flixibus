package com.umanets.flixibus.data.model;

import java.util.List;

/**
 * Created by ko3ak_zhn on 9/16/16.
 */
public class TimeTableResponse {
    public List<TimeTableResult> arrivals;
    public List<TimeTableResult> departures;
    public String message;
}
