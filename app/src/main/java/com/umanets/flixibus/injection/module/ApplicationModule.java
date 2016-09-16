package com.umanets.flixibus.injection.module;

import android.app.Application;
import android.content.Context;

import com.umanets.flixibus.data.remote.FlixiBusApiService;
import com.umanets.flixibus.injection.ApplicationContext;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    FlixiBusApiService provideRibotsService() {
        return FlixiBusApiService.Creator.newApiService();
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
