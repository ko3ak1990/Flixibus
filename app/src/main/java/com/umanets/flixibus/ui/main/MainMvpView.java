package com.umanets.flixibus.ui.main;


import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showResults(List<TimeTableResult> results,int type);

    void showResultsEmpty(int type);

    void showError();

}
