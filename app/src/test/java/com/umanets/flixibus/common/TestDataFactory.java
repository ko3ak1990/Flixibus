package com.umanets.flixibus.common;

import com.umanets.flixibus.data.model.TimeTableResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 18.09.2016.
 */
public class TestDataFactory {
    public static TimeTableResult makeResult(int direction) {
        TimeTableResult timeTableResult = new TimeTableResult();
        timeTableResult.direction = String.valueOf(direction);
        return timeTableResult;
    }
    public static List<TimeTableResult> makeListResults(int number){
        List<TimeTableResult> list = new ArrayList<>();
        for (int i=0;i<number;i++){
            list.add(makeResult(i));
        }
        return list;
    }
}
