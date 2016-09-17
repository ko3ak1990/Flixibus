package com.umanets.flixibus.data;

import android.text.TextUtils;

import com.umanets.flixibus.data.local.PreferencesHelper;
import com.umanets.flixibus.data.model.FlixDate;
import com.umanets.flixibus.data.model.TimeTableResponse;
import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.data.remote.FlixiBusApiService;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;


@Singleton
public class DataManager {

    private final FlixiBusApiService mFlixiBusApiService;
    private final PreferencesHelper mPreferencesHelper;

    public List<TimeTableResult> getArrivals() {
        return mArrivals;
    }

    public void setArrivals(List<TimeTableResult> arrivals) {
        mArrivals = arrivals;
    }

    public List<TimeTableResult> getDepartures() {
        return mDepartures;
    }

    public void setDepartures(List<TimeTableResult> departures) {
        mDepartures = departures;
    }

    private List<TimeTableResult> mArrivals;
    private List<TimeTableResult> mDepartures;

    @Inject
    public DataManager(FlixiBusApiService ribotsService, PreferencesHelper preferencesHelper) {
        mFlixiBusApiService = ribotsService;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


    public Observable<TimeTableResponse> syncTimetable() {
        return mFlixiBusApiService.getTimeTable().map(response -> response.timetable)
                .doOnNext(timeTableResponse -> {
                    setArrivals(decorateTimeTable(timeTableResponse.arrivals));
                    setDepartures(decorateTimeTable(timeTableResponse.departures));
                });
    }

    private List<TimeTableResult> decorateTimeTable(List<TimeTableResult> results) {
        for (TimeTableResult timeTableResult : results) {
            timeTableResult.time = setTime(timeTableResult.dateTime);
        }
        return results;
    }

    private String setTime(FlixDate date) {
        Calendar dateTime = Calendar.getInstance();
        if (date != null && date.timestamp > 0 && !TextUtils.isEmpty(date.tz)) {
            dateTime.setTimeInMillis(date.timestamp);
            dateTime.setTimeZone(TimeZone.getTimeZone(date.tz));
        }
        return dateTime.get(java.util.Calendar.HOUR_OF_DAY)
                + ":" + dateTime.get(java.util.Calendar.MINUTE);
    }

}
