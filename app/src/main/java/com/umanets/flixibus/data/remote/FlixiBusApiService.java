package com.umanets.flixibus.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umanets.flixibus.data.model.TimeTableDomainResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface FlixiBusApiService {

    String ENDPOINT = "http://api.mobile.staging.mfb.io";
    String HEADER_VAL = "X-Api-Authentication: intervIEW_TOK3n";

    @Headers(HEADER_VAL)
    @GET("mobile/v1/network/station/1/timetable")
    Observable<TimeTableDomainResponse> getTimeTable();

    /********
     * Helper class that sets up a new service
     *******/
    class Creator {

        public static FlixiBusApiService newApiService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FlixiBusApiService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FlixiBusApiService.class);
        }
    }
}
