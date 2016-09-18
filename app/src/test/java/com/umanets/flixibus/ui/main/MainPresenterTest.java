package com.umanets.flixibus.ui.main;

import com.umanets.flixibus.common.TestDataFactory;
import com.umanets.flixibus.data.DataManager;
import com.umanets.flixibus.data.SyncCompleteEvent;
import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.util.RxSchedulersOverrideRule;

import org.greenrobot.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Евгений on 18.09.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    MainMvpView mMockMainMvpView;
    @Mock
    EventBus mEventBus;
    @Mock
    DataManager mDatamanager;
    private MainPresenter mainPresenter;
    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulerRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp(){
        mainPresenter = new MainPresenter(mDatamanager,mEventBus);
        mainPresenter.attachView(mMockMainMvpView);
    }
    @After
    public void tearDown(){
        mainPresenter.detachView();
    }

    @Test
    public void loadTimeTable(){
        List<TimeTableResult> timeTableResults = TestDataFactory.makeListResults(10);
        when(mDatamanager.getArrivals()).thenReturn(timeTableResults);
        mainPresenter.loadTimeTable(MainPresenter.ARRIVALS);
        verify(mMockMainMvpView).showResults(timeTableResults,MainPresenter.ARRIVALS);
        verify(mMockMainMvpView,never()).showResults(timeTableResults,MainPresenter.DEPARTURES);
        verify(mMockMainMvpView,never()).showError();
        verify(mMockMainMvpView,never()).showResultsEmpty(MainPresenter.DEPARTURES);
    }

    @Test
    public void loadTimeTableReturnEmpty(){
        when(mDatamanager.getArrivals()).thenReturn(Collections.emptyList());
        mainPresenter.loadTimeTable(MainPresenter.DEPARTURES);
        verify(mMockMainMvpView,never()).showResultsEmpty(MainPresenter.ARRIVALS);
        verify(mMockMainMvpView).showResultsEmpty(MainPresenter.DEPARTURES);
        verify(mMockMainMvpView,never()).showResults(anyListOf(TimeTableResult.class)
                ,anyInt());
    }
    @Test
    public void loadTimeTableReturnError(){
        mainPresenter.onSyncCompleted(new SyncCompleteEvent(false,"error"));
        verify(mMockMainMvpView,never()).showResultsEmpty(MainPresenter.ARRIVALS);
        verify(mMockMainMvpView,never()).showResultsEmpty(MainPresenter.DEPARTURES);
        verify(mMockMainMvpView,never()).showResults(anyListOf(TimeTableResult.class)
                ,anyInt());
        verify(mMockMainMvpView).showError();
    }



}
