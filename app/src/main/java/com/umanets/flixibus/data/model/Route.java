package com.umanets.flixibus.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ko3ak_zhn on 9/16/16.
 */
public class Route {
    String name;
    String adress;
    @SerializedName("full_address")
    String fullAddress;
    FlixLatLng cordinates;
}
