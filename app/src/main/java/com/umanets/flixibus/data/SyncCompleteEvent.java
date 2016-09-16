package com.umanets.flixibus.data;

/**
 * Created by ko3ak_zhn on 3/21/16.
 */
public class SyncCompleteEvent {
    public String getMessage() {
        return message;
    }

    public SyncCompleteEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    String message;

    public boolean isSuccess() {
        return success;
    }

    boolean success;

}
