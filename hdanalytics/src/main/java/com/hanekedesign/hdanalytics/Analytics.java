package com.hanekedesign.hdanalytics;

import java.util.HashMap;
import java.util.Map;

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
    public static void removeProvider(Class provider) {
        if(analyticsProviders.containsKey(provider.getName()))
            analyticsProviders.remove(provider.getName());
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

    /**
     * Add/update super proerties that are sent with every event
     * @param hashMap
     */
    public static void addSuperProperties(HashMap<String, ?> hashMap) {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().addSuperProperties(hashMap);
        }
    }

    /**
     * Remove all superproperties for a given user
     */
    public static void removeAllSuperProperties() {
        for(Map.Entry<String, AnalyticsProvider> entry : analyticsProviders.entrySet()) {
            entry.getValue().removeAllSuperProperties();
        }
    }
}
