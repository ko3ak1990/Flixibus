package com.umanets.flixibus;

import android.app.Application;
import android.content.Context;


import com.umanets.flixibus.injection.component.ApplicationComponent;
import com.umanets.flixibus.injection.component.DaggerApplicationComponent;
import com.umanets.flixibus.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by ko3ak_zhn on 9/16/16.
 */
public class FlixibusApp extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static FlixibusApp get(Context context) {
        return (FlixibusApp) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}