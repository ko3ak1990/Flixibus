package com.umanets.flixibus.ui.main;

import com.umanets.flixibus.data.DataManager;
import com.umanets.flixibus.data.SyncCompleteEvent;
import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.injection.ConfigPersistent;
import com.umanets.flixibus.ui.base.BasePresenter;
import com.umanets.flixibus.util.RxUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;


@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    public static final int ARRIVALS = 111;
    public static final int DEPARTURES = 112;
    private final DataManager mDataManager;
    private final EventBus mEventBus;
    private Subscription mSubscription;
    private int mCurrentSelectedFilter;

    @Inject
    public MainPresenter(DataManager dataManager, EventBus eventBus) {
        mDataManager = dataManager;
        mEventBus = eventBus;
        mCurrentSelectedFilter = ARRIVALS;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
        mEventBus.register(this);
    }

    @Override
    public void detachView() {
        super.detachView();
        mEventBus.unregister(this);
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadTimeTable(int currentSelectedFilter) {
        mCurrentSelectedFilter = currentSelectedFilter;
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        List<TimeTableResult> results = mCurrentSelectedFilter == ARRIVALS
                ? mDataManager.getArrivals() : mDataManager.getDepartures();
        if (results != null) {
            if (results.isEmpty()) {
                getMvpView().showResultsEmpty();
            } else {
                getMvpView().showResults(results);
            }
        } else {
            getMvpView().showError();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSyncCompleted(SyncCompleteEvent event) {
        if (event.isSuccess()) {
            loadTimeTable(mCurrentSelectedFilter);
        }
    }


}
