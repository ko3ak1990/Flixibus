package com.umanets.flixibus.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.umanets.flixibus.R;
import com.umanets.flixibus.data.model.TimeTableResult;
import com.umanets.flixibus.injection.ApplicationContext;
import com.umanets.flixibus.ui.base.BaseFragment;
import com.umanets.flixibus.ui.main.adapter.TimeTableResultsAdapter;
import com.umanets.flixibus.util.DialogFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Евгений on 17.09.2016.
 */
public class TimeTableFragment extends BaseFragment implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    TimeTableResultsAdapter mTimeTableResultsAdapter;
    @Inject
    @ApplicationContext
    Context mContext;
    public static final String STATE="state";


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static TimeTableFragment newInstance(int state) {
        Bundle args = new Bundle();
        args.putInt(STATE,state);
        TimeTableFragment fragment = new TimeTableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable, container, false);
        ButterKnife.bind(this, v);
        mRecyclerView.setAdapter(mTimeTableResultsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMainPresenter.attachView(this);
        if(getArguments()!=null){
            mMainPresenter.loadTimeTable(getArguments().getInt(STATE));
        }
        return v;
    }

    @Override
    public void initDiComponent() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMainPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMainPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    /*****
     * MVP View methods implementation
     *****/

    @Override
    public void showResults(List<TimeTableResult> results,int type) {
        mTimeTableResultsAdapter.setTimeTableResults(results,type);
        mTimeTableResultsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
       DialogFactory.createSimpleOkErrorDialog(getContext(),
               R.string.dialog_error_title,R.string.error_loading_results);}

    @Override
    public void showResultsEmpty(int type) {
        mTimeTableResultsAdapter.setTimeTableResults(Collections.<TimeTableResult>emptyList(),type);
        mTimeTableResultsAdapter.notifyDataSetChanged();
        Toast.makeText(mContext, R.string.empty_results, Toast.LENGTH_LONG).show();
    }

}
