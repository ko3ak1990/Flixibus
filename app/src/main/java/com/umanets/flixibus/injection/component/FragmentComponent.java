package com.umanets.flixibus.injection.component;

import com.umanets.flixibus.injection.ConfigPersistent;
import com.umanets.flixibus.ui.main.TimeTableFragment;

import dagger.Subcomponent;

/**
 * Created by Евгений on 17.09.2016.
 */
@ConfigPersistent
@Subcomponent
public interface FragmentComponent {
    void inject(TimeTableFragment tableFragment);
}