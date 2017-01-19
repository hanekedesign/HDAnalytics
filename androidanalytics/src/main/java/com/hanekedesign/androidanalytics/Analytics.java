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

    private static HashMap<String, AnalyticsProvider> analyticsProviders;

    public static void addProvider(AnalyticsProvider provider) {
        analyticsProviders.put(provider.getClass().getName(), provider);
        provider.setupProvider();
    }

    public static void removeProvider(String providerName) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(providerName)) {
                analyticsProviders.remove(entry.getKey());
                break;
            }
        }
    }

    public static HashMap<String, AnalyticsProvider> getAllProviders() {
        return analyticsProviders;
    }

    public static AnalyticsProvider getProviderInstance(String providerName) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(providerName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static void sendEvent(AnalyticsProvider provider, String event) {
        provider.sendEvent(event);
    }

    public static void sendEventWithProperties(AnalyticsProvider provider, String event, HashMap eventMap) {
        provider.sendEventWithProperties(event, eventMap);
    }

    public static void sendScreenViewEvent(AnalyticsProvider provider, String screenName) {
        provider.sendScreenViewEvent(screenName);
    }

    public static void sendSessionEvent(AnalyticsProvider provider) {
        provider.sendSessionEvent();
    }

    public static void sendCaughtException(AnalyticsProvider provider, Exception e, boolean isFatal) {
        provider.sendCaughtException(e, isFatal);
    }
}
