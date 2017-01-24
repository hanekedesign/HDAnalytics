package com.hanekedesign.androidanalytics;

import android.content.Context;
import android.text.AndroidCharacter;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nthunem on 1/19/17.
 */

public class Analytics {

    private static HashMap<String, AnalyticsProvider> analyticsProviders = new HashMap<>();

    public static void addProvider(AnalyticsProvider provider) {
        analyticsProviders.put(provider.getClass().getName(), provider);
    }

    public static void removeProvider(AnalyticsProvider provider) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(provider.getClass().getName())) {
                analyticsProviders.remove(entry.getKey());
                break;
            }
        }
    }

    public static HashMap<String, AnalyticsProvider> getAllProviders() {
        return analyticsProviders;
    }

    public static AnalyticsProvider getProviderInstance(AnalyticsProvider provider) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(provider.getClass().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static void sendEvent(String event) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendEvent(event);
        }
    }

    public static void sendEventWithProperties(String event, HashMap eventMap) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendEventWithProperties(event, eventMap);
        }
    }

    public static void sendScreenViewEvent(String screenName) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendScreenViewEvent(screenName);
        }
    }

    public static void sendSessionEvent() {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendSessionEvent();
        }
    }

    public static void sendCaughtException(Exception e) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendCaughtException(e);
        }
    }

    public static void sendCaughtException(Exception e, boolean isFatal) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendCaughtException(e, isFatal);
        }
    }

    public static void updateUserProfile(String key, Object value) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().updateUserProfile(key, value);
        }
    }
}
