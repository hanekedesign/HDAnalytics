package com.hanekedesign.hdanalytics;

import java.util.HashMap;

public interface AnalyticsProvider {

    /**
     * Attach a userId to each event sent
     *
     * @param userId        User Id for each event
     */
    void sendUserId(String userId);

    /**
     * Send a basic event to the analytics provider's service
     *
     * @param event         Event name
     */
    void sendEvent(String event);

    /**
     * Send an event with additional properties to the analytics provider's service
     *
     * @param event         Event name
     * @param eventMap      Additional properties for the event
     */
    void sendEventWithProperties(String event, HashMap<String, ?> eventMap);

    /**
     * Send a screen view event to the analytics provider's service
     *
     * @param screenName    Screen name
     */
    void sendScreenViewEvent(String screenName);

    /**
     * Send a session event to the analytics provider's service
     */
    void sendSessionEvent();

    /**
     * Send an exception event to the analytics provider's service
     *
     * @param e
     */
    void sendCaughtException(Exception e);

    /**
     * Send an exception to the analytics provider's service
     *
     * @param e
     * @param isFatal       Was the exception fatal
     */
    void sendCaughtException(Exception e, boolean isFatal);

    /**
     * Add/Update the user information of the current user attached to all events
     *
     * @param key           Key of new/updated user property
     * @param value         Value of new/updated user property
     */
    void updateUserProfile(String key, Object value);

    /**
     * Add/update super properties for each event
     *
     * @param hashMap
     */
    void addSuperProperties(HashMap<String, ?> hashMap);

    /**
     * Remove a super property for the current user
     *
     * @param propertyName  Name of the intended super property to be removed
     */
    void removeSuperProperty(String propertyName);

    /**
     * Remove all super properties for a given user
     */
    void removeAllSuperProperties();

    /**
     * Start a timed event
     *
     * @param eventName     Event name to start timer on
     */
    void startTimedEvent(String eventName);

    /**
     * Stop a timed event and send the event
     *
     * @param eventName     Event name to be stopped and sent
     */
    void stopTimedEvent(String eventName) throws TimedEventException;

    /**
     * Stop a timed event and send the event with properties
     *
     * @param eventName     Event name to be stopped and sent
     * @param hashMap
     */
    void stopTimedEvent(String eventName, HashMap hashMap) throws TimedEventException;
}
