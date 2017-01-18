package com.hanekedesign.analyticsandroid.Providers;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.analyticsandroid.AnalyticsInterface;
import com.hanekedesign.analyticsandroid.R;

import java.util.HashMap;

/**
 * Created by nthunem on 1/18/17.
 */

public class GoogleAnalyticsProvider implements AnalyticsInterface {

    public static final String EVENT_CATEGORY = "category";
    public static final String EVENT_ACTION = "action";
    public static final String EVENT_LABEL = "label";
    public static final String EVENT_VALUE = "value";
    public static final String EXCEPTION_DESCRIPTION = "description";
    public static final String EXCEPTION_IS_FATAL = "is-fatal";

    private Tracker tracker;
    private Context sharedContext;
    private String providerId;

    //events
    private String category;
    private String action;
    private String label;
    private long value;

    //exceptions
    private String description;
    private boolean isFatal;

    public GoogleAnalyticsProvider(Context sharedContext) {
        this.sharedContext = sharedContext;

        category = "default";
        action = "default";
        label = null;
        value = 0;

        description = "caught_exception";
        isFatal = false;
    }

    public void setupProvider(String propertyId) {
        if(tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(sharedContext);
            tracker = analytics.newTracker(propertyId);
        }
    }

    public void removeProvider() {
        GoogleAnalytics.getInstance(sharedContext).setAppOptOut(true);
    }

    public GoogleAnalyticsProvider getProviderInstance() {
        return GoogleAnalyticsProvider.this;
    }

    public void setProviderId(String id) {
        providerId = id;
    }

    public void setProviderTracker(String name) {}

    public void sendUserId(String id) {
        tracker.set("&uid", id);
    }

    public void setDispatchFrequency(int seconds) {
        GoogleAnalytics.getInstance(sharedContext).setLocalDispatchPeriod(seconds);
    }

    public void setAdvertisingFeatures(boolean showAdvertisements) {
        tracker.enableAdvertisingIdCollection(showAdvertisements);
    }

    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    public void sendEventWithProperties(String event, HashMap eventMap) {
        if(eventMap.containsKey(EVENT_CATEGORY))
            category = eventMap.get(EVENT_CATEGORY).toString();
        if(eventMap.containsKey(EVENT_ACTION))
            action = eventMap.get(EVENT_ACTION).toString();

        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
        eventBuilder.setCategory(category);
        eventBuilder.setAction(action);

        if(eventMap.containsKey(EVENT_LABEL)) {
            eventBuilder.setLabel(label);
        }
        if(eventMap.containsKey(EVENT_VALUE)) {
            eventBuilder.setValue(value);
        }

        tracker.send(eventBuilder.build());
    }

    public void sendScreenViewEvent(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendSessionEvent() {
        tracker.send(new HitBuilders.EventBuilder()
                .setNewSession()
                .build()
        );
    }

    public void sendCaughtException(HashMap exceptionEvent) {
        if(exceptionEvent.containsKey(EXCEPTION_DESCRIPTION))
            description = exceptionEvent.get(EXCEPTION_DESCRIPTION).toString();
        if(exceptionEvent.containsKey(EXCEPTION_IS_FATAL))
            isFatal = exceptionEvent.get(EXCEPTION_IS_FATAL).toString().equalsIgnoreCase("true");

        tracker.send(new HitBuilders.ExceptionBuilder()
            .setDescription(description)
            .setFatal(isFatal)
            .build()
        );
    }

    public void setUncaughtExceptionEvent(boolean sendUncaughtException) {
        Thread.UncaughtExceptionHandler handler = new ExceptionReporter(
                tracker,
                Thread.getDefaultUncaughtExceptionHandler(),
                sharedContext
        );

        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
