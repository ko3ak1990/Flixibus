package com.umanets.flixibus.injection.component;

import com.umanets.flixibus.injection.PerActivity;
import com.umanets.flixibus.injection.module.ActivityModule;
import com.umanets.flixibus.ui.main.MainActivity;

import dagger.Subcomponent;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
