package com.umanets.flixibus.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.umanets.flixibus.R;
import com.umanets.flixibus.data.SyncService;
import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.ui.base.BaseActivity;
import com.umanets.flixibus.util.DialogFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.umanets.flixibus.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    TimeTableResultsAdapter mTimeTableResultsAdapter;


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setAdapter(mTimeTableResultsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        if (savedInstanceState == null && getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    /*****
     * MVP View methods implementation
     *****/

    @Override
    public void showResults(List<TimeTableResult> results) {
        mTimeTableResultsAdapter.setTimeTableResults(results);
        mTimeTableResultsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_results))
                .show();
    }

    @Override
    public void showResultsEmpty() {
        mTimeTableResultsAdapter.setTimeTableResults(Collections.<TimeTableResult>emptyList());
        mTimeTableResultsAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_results, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.arrivals_btn)
    public void onArrivalsClick() {
        mMainPresenter.loadTimeTable(MainPresenter.ARRIVALS);
    }

    @OnClick(R.id.departures_btn)
    public void onDeparturesClick() {
        mMainPresenter.loadTimeTable(MainPresenter.DEPARTURES);
    }

}
