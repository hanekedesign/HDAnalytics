package com.hanekedesign.androidanalytics;

import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Created by nthunem on 1/19/17.
 */

public interface AnalyticsProvider {

    /**
     * Attaches a userId to each event to be sent
     *
     * @param userId        User Id for each event
     */
    void sendUserId(String userId);

    /**
     * Sends a basic event to the analytics provider's service
     *
     * @param event         Event name
     */
    void sendEvent(String event);

    /**
     * Sends an event with additional properties to the analytics provider's service
     *
     * @param event         Event name
     * @param eventMap      Hashmap of key/value pairs
     */
    void sendEventWithProperties(String event, HashMap<String, ?> eventMap);

    /**
     * Sends a screen view event to the analytics provider's service
     *
     * @param screenName    Screen name
     */
    void sendScreenViewEvent(String screenName);

    /**
     * Sends a session event to the analytics provider's service
     */
    void sendSessionEvent();

    /**
     * Sends an exception event to the analytics provider's service
     *
     * @param e             Exception caught
     */
    void sendCaughtException(Exception e);

    /**
     * Sends an exception to the analytics provider's service
     *
     * @param e             Exception caught
     * @param isFatal       Was the exception fatal
     */
    void sendCaughtException(Exception e, boolean isFatal);

    /**
     * Adds/Updates the user information of the current user attached to all events
     *
     * @param key           Key of new/updated user property
     * @param value         Value of new/updated user property
     */
    void updateUserProfile(String key, Object value);
}
