package com.umanets.flixibus.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umanets.flixibus.FlixibusApp;
import com.umanets.flixibus.injection.component.ActivityComponent;


/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent injector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector = retrieveInjectorOrCreateNew();
    }

    private ActivityComponent retrieveInjectorOrCreateNew() {
        Object lastNonConfInstance = getLastCustomNonConfigurationInstance();
        if (lastNonConfInstance == null) {
            return FlixibusApp.get(this).getComponent().activityComponent();
        } else {
            return (ActivityComponent) lastNonConfInstance;
        }
    }

    protected ActivityComponent getInjector() {
        return injector;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return injector;
    }

}
