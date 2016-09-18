package com.umanets.flixibus.ui.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.umanets.flixibus.FlixibusApp;

import com.umanets.flixibus.injection.component.FragmentComponent;


/**
 * Created by Евгений on 17.09.2016.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initDiComponent();
    }


    protected void initDiComponent() {
    }

    public void setFragmentComponent(FragmentComponent fragmentComponent) {
        this.mFragmentComponent = fragmentComponent;
    }

    private FragmentComponent mFragmentComponent;

    public FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent =FlixibusApp.get(getActivity()).getComponent().fragmentComponent();
        }
        return mFragmentComponent;
    }


}
