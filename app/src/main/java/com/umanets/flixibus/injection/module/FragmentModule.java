package com.umanets.flixibus.injection.module;


import android.support.v4.app.Fragment;

import dagger.Module;

/**
 * Created by ko3ak_zhn on 4/4/16.
 */
@Module
public class FragmentModule {
    private Fragment mFrag;

    public FragmentModule(Fragment fragment) {
        mFrag = fragment;
    }
}
