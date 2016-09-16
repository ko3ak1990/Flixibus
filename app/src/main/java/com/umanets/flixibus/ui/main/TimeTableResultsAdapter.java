package com.umanets.flixibus.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umanets.flixibus.R;
import com.umanets.flixibus.data.model.TimeTableResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimeTableResultsAdapter extends RecyclerView.Adapter<TimeTableResultsAdapter.TimeTableResultViewHolder> {

    private List<TimeTableResult> mTimeTableResults;

    @Inject
    public TimeTableResultsAdapter() {
        mTimeTableResults = new ArrayList<>();
    }

    public void setTimeTableResults(List<TimeTableResult> results) {
        mTimeTableResults = results;
    }

    @Override
    public TimeTableResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timetable, parent, false);
        return new TimeTableResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TimeTableResultViewHolder holder, int position) {
        TimeTableResult result = mTimeTableResults.get(position);
        holder.mRouteTextView.setText(result.lineCode);
        holder.mDirectionTextView.setText(result.lineDirection);
        holder.mDateTimeTextView.setText(result.time);
    }

    @Override
    public int getItemCount() {
        return mTimeTableResults.size();
    }

    class TimeTableResultViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.route_text_view)
        TextView mRouteTextView;
        @BindView(R.id.text_direction)
        TextView mDirectionTextView;
        @BindView(R.id.text_datetime)
        TextView mDateTimeTextView;

        public TimeTableResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
