package com.hanekedesign.androidanalytics;

import android.content.Context;

import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.androidanalytics.Providers.GoogleAnalyticsBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nthunem on 1/19/17.
 */

public interface AnalyticsProvider {

    void sendUserId(String userId);

    // Send event event to provider
    void sendEvent(String event);

    // Send an event with additional properties and event name
    void sendEventWithProperties(String event, HashMap<?, ?> eventMap);

    // Send screen view event
    void sendScreenViewEvent(String screenName);

    // Send session event
    void sendSessionEvent();

    // Send a caught exception
    void sendCaughtException(Exception e);

    // Send a caught exception
    void sendCaughtException(Exception e, boolean isFatal);

    // Updates user profile
    void updateUserProfile(String key, Object value);
}
