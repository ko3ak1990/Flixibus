package com.umanets.flixibus.injection.component;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;

import com.umanets.flixibus.data.DataManager;
import com.umanets.flixibus.data.SyncService;
import com.umanets.flixibus.data.local.PreferencesHelper;
import com.umanets.flixibus.data.remote.FlixiBusApiService;
import com.umanets.flixibus.injection.ApplicationContext;
import com.umanets.flixibus.injection.module.ApplicationModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    EventBus eventBus();
    DataManager dataManager();

    ActivityComponent activityComponent();
    FragmentComponent fragmentComponent();
}
