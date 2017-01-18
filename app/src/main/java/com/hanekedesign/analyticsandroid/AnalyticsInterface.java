package com.hanekedesign.analyticsandroid;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nthunem on 1/18/17.
 */

public interface AnalyticsInterface {

    // Add a provider
    void setupProvider(String id);

    // Remove a provider
    void removeProvider();

    // Get all providers
    //ArrayList<Object> getAllProviders();

    // Get instance of provider
    // Must setup (add) the provider beforehand
    Object getProviderInstance();

    // Set provider id
    void setProviderId(String id);

    // Set provider tracker
    void setProviderTracker(String name);

    // Get provider-specific analytics object
    //Object getProviderTracker();

    // Send user ID with all events
    void sendUserId(String userId);

    // Set dispatch frequency
    void setDispatchFrequency(int seconds);

    // Set advertising features
    void setAdvertisingFeatures(boolean showAdvertisements);

    // Send event event to provider
    void sendEvent(String event);

    // Send an event with additional properties
    void sendEventWithProperties(String event, HashMap eventMap);

    // Send screen view event
    void sendScreenViewEvent(String screenName);

    // Send session event
    void sendSessionEvent();

    // Send a caught exception
    void sendCaughtException(HashMap exceptionMap);

    // Set true/false to send uncaught exception
    void setUncaughtExceptionEvent(boolean sendUncaughtException);

    // Send an uncaught exception
    //void sendUncaughtException();

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
