package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.androidanalytics.AnalyticsProvider;

import java.util.HashMap;

/**
 * Created by nthunem on 1/19/17.
 */

public class GoogleAnalyticsProvider implements AnalyticsProvider {

    public static final String EVENT_CATEGORY = "category";
    public static final String EVENT_ACTION = "action";
    public static final String EVENT_LABEL = "label";
    public static final String EVENT_VALUE = "value";
    public static final String EXCEPTION_DESCRIPTION = "exception_description";
    public static final String EXCEPTION_IS_FATAL = "exceptiion_is_fatal";

    private Tracker tracker;

    //required
    private final String providerId;
    private final Context context;

    //optional
    private String category = "default";
    private String action = "default";
    private final int seconds;
    private final boolean sendAdvertising;
    private final boolean sendUncaughtExceptions;
    private final boolean sendUserId;
    private String userId;

    GoogleAnalyticsProvider(GoogleAnalyticsBuilder builder) {
        //required
        this.providerId = builder.providerId;
        this.context = builder.context;
        //optional
        if(builder.defaultCategory != null)
            this.category = builder.defaultCategory;
        if(builder.defaultAction != null)
            this.action = builder.defaultAction;

        this.seconds = builder.dispatchFrequency;
        if(this.seconds != 0)
            setDispatchFrequency(this.seconds);

        this.sendAdvertising = builder.sendAdvertising;
        if(this.sendAdvertising)
            setAdvertisingFeatures(this.sendAdvertising);

        this.sendUncaughtExceptions = builder.sendUncaughtExceptions;
        if(this.sendUncaughtExceptions)
            setUncaughtExceptionEvent();

        this.sendUserId = builder.sendUserId;
        if(this.sendUserId)
            sendUserId();
    }

    public void setupProvider() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
            tracker = analytics.newTracker(providerId);
        }
    }

    public void sendUserId() {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("google_analytics_user_id", "string", packageName);
        this.userId = context.getString(resId);
        tracker.set("&uid", this.userId);
    }

    public void setDispatchFrequency(int seconds) {
        GoogleAnalytics.getInstance(context).setLocalDispatchPeriod(seconds);
    }

    public void setAdvertisingFeatures(boolean showAdvertisements) {
        tracker.enableAdvertisingIdCollection(showAdvertisements);
    }

    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }


    public void sendEventWithProperties(String event, HashMap eventMap) {
        String eventCategory = category;
        String eventAction = action;

        if (eventMap.containsKey(EVENT_CATEGORY))
            eventCategory = eventMap.get(EVENT_CATEGORY).toString();
        if (event != null)
            eventAction = event;
        else if (eventMap.containsKey(EVENT_ACTION))
            eventAction = eventMap.get(EVENT_ACTION).toString();

        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
        eventBuilder.setCategory(eventCategory);
        eventBuilder.setAction(eventAction);

        if (eventMap.containsKey(EVENT_LABEL)) {
            eventBuilder.setLabel(eventMap.get(EVENT_LABEL).toString());
        }
        if (eventMap.containsKey(EVENT_VALUE)) {
            eventBuilder.setValue(Integer.getInteger(eventMap.get(EVENT_VALUE).toString()));
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

    public void sendCaughtException(Exception e) {
        sendCaughtException(e, false);
    }

    public void sendCaughtException(Exception e, boolean isFatal) {
        tracker.send(new HitBuilders.ExceptionBuilder()
                .setDescription(e.getLocalizedMessage())
                .setFatal(isFatal)
                .build()
        );
    }

    public void setUncaughtExceptionEvent() {
        Thread.UncaughtExceptionHandler handler = new ExceptionReporter(
                tracker,
                Thread.getDefaultUncaughtExceptionHandler(),
                context
        );

        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
