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

    /**
     * Hashmap of analytics providers to be used
     */
    private static HashMap<String, AnalyticsProvider> analyticsProviders = new HashMap<>();

    /**
     * Add an analytics provider to the service
     *
     * @param provider      Analytics provider to be used
     */
    public static void addProvider(AnalyticsProvider provider) {
        analyticsProviders.put(provider.getClass().getName(), provider);
    }

    /**
     * Remove an analytics provider by class
     *
     * @param provider      Analytics provider to be removed
     */
    public static void removeProvider(AnalyticsProvider provider) {
        if(analyticsProviders.containsKey(provider.getClass().getName()))
            analyticsProviders.remove(provider.getClass().getName());
    }

    /**
     * Return all providers being used
     * @return              Hashmap of all provider objects
     */
    public static HashMap<String, AnalyticsProvider> getAllProviders() {
        return analyticsProviders;
    }

    /**
     * Return AnalyitcsProvider instance of given provider object
     *
     * @param provider      Analytics provider
     * @return              Class instance of given provider
     */
    public static AnalyticsProvider getProviderInstance(AnalyticsProvider provider) {
        if(analyticsProviders.containsKey(provider.getClass().getName()))
                return analyticsProviders.get(provider.getClass().getName());

        return null;
    }

    /**
     * Attach a userId to each event sent
     *
     * @param userId        User Id for each event
     */
    public static void sendUserId(String userId) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendUserId(userId);
        }
    }

    /**
     * Send a basic event to the analytics provider's service
     *
     * @param event         Event name
     */
    public static void sendEvent(String event) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendEvent(event);
        }
    }

    /**
     * Send an event with additional properties to the analytics provider's service
     *
     * @param event         Event name
     * @param eventMap      Additional properties for the event
     */
    public static void sendEventWithProperties(String event, HashMap eventMap) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendEventWithProperties(event, eventMap);
        }
    }

    /**
     * Send a screen view event to the analytics provider's service
     *
     * @param screenName    Screen name
     */
    public static void sendScreenViewEvent(String screenName) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendScreenViewEvent(screenName);
        }
    }

    /**
     * Send a session event to the analytics provider's service
     */
    public static void sendSessionEvent() {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendSessionEvent();
        }
    }

    /**
     * Send an exception event to the analytics provider's service
     *
     * @param e
     */
    public static void sendCaughtException(Exception e) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendCaughtException(e);
        }
    }

    /**
     * Send an exception to the analytics provider's service
     *
     * @param e
     * @param isFatal       Was the exception fatal
     */
    public static void sendCaughtException(Exception e, boolean isFatal) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().sendCaughtException(e, isFatal);
        }
    }

    /**
     * Add/Update the user information of the current user attached to all events
     *
     * @param key           Key of new/updated user property
     * @param value         Value of new/updated user property
     */
    public static void updateUserProfile(String key, Object value) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().updateUserProfile(key, value);
        }
    }
}
