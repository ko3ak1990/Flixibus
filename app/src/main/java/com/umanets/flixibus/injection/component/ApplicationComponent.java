package com.umanets.flixibus.injection.component;

import android.app.Application;
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


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    FlixiBusApiService ribotsService();
    PreferencesHelper preferencesHelper();
    DataManager dataManager();
    EventBus eventBus();

}
