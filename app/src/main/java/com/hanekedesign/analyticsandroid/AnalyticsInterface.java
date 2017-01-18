package com.hanekedesign.analyticsandroid;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nthunem on 1/18/17.
 */

public interface AnalyticsInterface {

    // Add a provider
    void setupProvider();

    // Remove a provider
    void removeProvider();

    // Get all providers
    ArrayList<Object> getAllProviders();

    // Get instance of provider
    // Must setup (add) the provider beforehand
    Object getProviderInstance(Object provider);

    // Set provider id
    void setProviderId(String id);

    // Set provider tracker
    void setProviderTracker(String name);

    // Get provider-specific analytics object
    Object getProviderTracker(Object provider);

    // Send user ID with all events
    void sendUserId(String userId);
    void sendUserId(int userId);
    void sendUserId(long userId);

    // Set dispatch frequency
    void setDispatchFrequency(int minutes);

    // Set advertising features
    void setAdvertisingFeatures(boolean showAdvertisements);

    // Send event event to provider
    void sendEvent(String event);

    // Send an event with additional properties
    void sendEventWithProperties(String event, HashMap eventMap);

    // Send screen view event
    void sendScreenViewEvent(String screenName);

    // Send session event
    void sendSessionEvent(String screenName);

    // Send a caught exception
    void sendCaughtException(HashMap exceptionMap);

    // Set true/false to send uncaught exception
    void setUncaughtExceptionEvent(boolean sendUncaughtException);

    // Send an uncaught exception
    void sendUncaughtException(HashMap exceptionMap);

    /* Google Analytics */

//    // Send a non-interaction event
//    void sendEventNonInteraction(Object analyticsProvider);
//
//    // Send campaign event
//    void sendCampaignEvent(String campaignData);
//
//    // Send custom Dimension/Metric value
//    void sendCustomMetric(boolean valueBoolean, String value);
//
//    // Send social interaction event
//    void sendSocialInteractionEvent(AnalyticsProvider provider);
//
//    // Send user timing event
//    void sendUserTimingEvent(AnalyticsProvider provider);
}
