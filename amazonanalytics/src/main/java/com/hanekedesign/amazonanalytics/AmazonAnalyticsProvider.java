package com.hanekedesign.amazonanalytics;

import android.content.Context;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.hanekedesign.hdanalytics.AnalyticsProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AmazonAnalyticsProvider implements AnalyticsProvider {

    private Context context;
    private String userId;
    private boolean sendUserId;
    private String eventName;
    private String screenEventName;
    private String userIdName = "User ID";
    private String sessionEvent = "Session";
    private String screenView = "Screen View";
    private String exceptionEvent = "Exception";
    private String exception = "Exception";
    private String fatal = "Fatal";
    private HashMap superProperties = new HashMap();
    private HashMap<String, Date> timedEvents = new HashMap<>();

    private MobileAnalyticsManager analyticsManager;

    public AmazonAnalyticsProvider(AmazonAnalyticsBuilder builder) {
        this.context = builder.context;
        this.analyticsManager = builder.analyticsManager;
        this.eventName = builder.defaultEventName;
        this.screenEventName = builder.defaultScreenEvent;
    }

    public void sendUserId(String userId) {
        this.userId = userId;
        sendUserId = true;
    }

    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    public void sendEventWithProperties(String event, HashMap<String, ?> eventMap) {
        String tempEventName = eventName;
        if(event != null)
            tempEventName = event;

        AnalyticsEvent analyticsEvent = analyticsManager.getEventClient().createEvent(tempEventName);
        for(Map.Entry<String, ?> entry : eventMap.entrySet()) {
            if(entry.getValue() instanceof Double)
                analyticsEvent.addMetric(entry.getKey(), (Double)entry.getValue());
            else
                analyticsEvent.addAttribute(entry.getKey(), entry.getValue().toString());
        }
        if(sendUserId)
            analyticsEvent.addAttribute(userIdName, userId);
        if(!superProperties.isEmpty())
            addSuperPropertyToEvent(analyticsEvent, superProperties);

        analyticsManager.getEventClient().recordEvent(analyticsEvent);
    }

    public void sendScreenViewEvent(String screenName) {
        AnalyticsEvent analyticsEvent = analyticsManager.getEventClient().createEvent(screenEventName)
                .withAttribute(screenView, screenName);
        if(sendUserId)
            analyticsEvent.addAttribute(userIdName, userId);
        if(!superProperties.isEmpty())
            addSuperPropertyToEvent(analyticsEvent, superProperties);

        analyticsManager.getEventClient().recordEvent(analyticsEvent);
    }

    public void sendSessionEvent() {
        AnalyticsEvent analyticsEvent = analyticsManager.getEventClient().createEvent(sessionEvent);
        if(sendUserId)
            analyticsEvent.addAttribute(userIdName, userId);
        if(!superProperties.isEmpty())
            addSuperPropertyToEvent(analyticsEvent, superProperties);

        analyticsManager.getEventClient().recordEvent(analyticsEvent);
    }

    public void sendCaughtException(Exception e) {
        sendCaughtException(e, false);
    }

    public void sendCaughtException(Exception e, boolean isFatal) {
        AnalyticsEvent analyticsEvent = analyticsManager.getEventClient().createEvent(exceptionEvent)
                .withAttribute(exception, e.toString());
        if(isFatal)
            analyticsEvent.addAttribute(fatal, "true");
        else
            analyticsEvent.addAttribute(fatal, "false");
        if(sendUserId)
            analyticsEvent.addAttribute(userIdName, userId);
        if(!superProperties.isEmpty())
            addSuperPropertyToEvent(analyticsEvent, superProperties);

        analyticsManager.getEventClient().recordEvent(analyticsEvent);
    }

    public void updateUserProfile(String key, Object value) {
        userId = value.toString();
    }

    @Override
    public void addSuperProperties(HashMap<String, ?> hashMap) {
        for(Map.Entry<String, ?> entry : hashMap.entrySet()) {
            superProperties.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void removeSuperProperty(String propertyName) {
        if(superProperties.containsKey(propertyName))
            superProperties.remove(propertyName);
    }

    @Override
    public void removeAllSuperProperties() {
        superProperties.clear();
    }

    private void addSuperPropertyToEvent(AnalyticsEvent analyticsEvent, HashMap<String, ?> hashMap) {
        for(Map.Entry<String, ?> entry : hashMap.entrySet()) {
            analyticsEvent.addAttribute(entry.getKey(), entry.getValue().toString());
        }
    }

    @Override
    public void startTimedEvent(String eventName) {
        timedEvents.put(eventName, new Date());
    }

    @Override
    public void stopTimedEvent(String eventName) throws RuntimeException {
        if(!timedEvents.containsKey(eventName))
            throw new RuntimeException("Event name does not exist");

        stopTimedEvent(eventName, null);
    }

    @Override
    public void stopTimedEvent(String eventName, HashMap hashMap) throws RuntimeException {
        if(!timedEvents.containsKey(eventName))
            throw new RuntimeException("Event name does not exist");

        Date oldDate = timedEvents.get(eventName);
        Date newDate = new Date();

        long eventTime = newDate.getTime() - oldDate.getTime();
        String eventTimeString = (new SimpleDateFormat("hh:mm:ss")).format(new Date(eventTime));

        hashMap.put("Duration", eventTimeString);
        timedEvents.remove(eventName);

        sendEventWithProperties(eventName, hashMap);
    }
}
