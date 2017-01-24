package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.androidanalytics.AnalyticsProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/19/17.
 */

public class GoogleAnalyticsProvider implements AnalyticsProvider {

    private static final String TAG = "GoogleAnalyticsProvider";

    public static final String EVENT_CATEGORY = "category";
    public static final String EVENT_ACTION = "action";
    public static final String EVENT_LABEL = "label";
    public static final String EXCEPTION_DESCRIPTION = "exception_description";
    public static final String EXCEPTION_IS_FATAL = "exceptiion_is_fatal";

    private Tracker tracker;
    GoogleAnalytics analytics;

    //required
    private String providerId;
    private Context context;

    //optional
    private String category = "Default";
    private String action = "Default";

    GoogleAnalyticsProvider(GoogleAnalyticsBuilder builder) {
//        //required
//        providerId = "UA-90565556-1";
        providerId = builder.providerId;
        this.context = builder.context;

        Log.e(TAG, "PROVIDER ID = " + providerId);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this.context);
        if(builder.dispatchFrequency != 0)
            analytics.setLocalDispatchPeriod(builder.dispatchFrequency);

        if(tracker == null) {
            tracker = analytics.newTracker(providerId);
            tracker.enableAutoActivityTracking(true);

            if(builder.defaultCategory != null)
                this.category = builder.defaultCategory;

            if(builder.defaultAction != null)
                this.action = builder.defaultAction;

            tracker.enableExceptionReporting(true);
            if(builder.sendAdvertising)
                tracker.enableAdvertisingIdCollection(true);

            if(!builder.userId.equalsIgnoreCase(""))
                tracker.set("&uid", builder.userId);

            if(builder.sendUncaughtExceptions) {
                Thread.UncaughtExceptionHandler handler = new ExceptionReporter(
                        tracker,
                        Thread.getDefaultUncaughtExceptionHandler(),
                        context
                );
                Thread.setDefaultUncaughtExceptionHandler(handler);
            }
        }
    }

    @Override
    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    @Override
    public void sendEventWithProperties(String event, HashMap<?, ?> eventMap) {
        String eventCategory;
        if(event == null)
            eventCategory = category;
        else
            eventCategory = event;

        String eventAction = action;

        if(eventMap != null) {
            if (eventMap.containsKey(EVENT_CATEGORY))
                eventCategory = eventMap.get(EVENT_CATEGORY).toString();
            if (event != null)
                eventAction = event;
            else if (eventMap.containsKey(EVENT_ACTION))
                eventAction = eventMap.get(EVENT_ACTION).toString();
        }

        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
        eventBuilder.setCategory(eventCategory);
        eventBuilder.setAction(eventAction);

        if(eventMap != null) {
            //add label
            if (eventMap.containsKey(EVENT_LABEL)) {
                eventBuilder.setLabel(eventMap.get(EVENT_LABEL).toString());
            }
            //add other key/value pairs
            for(Map.Entry<?, ?> entry : eventMap.entrySet()) {
                if(!entry.getKey().toString().equalsIgnoreCase(EVENT_CATEGORY) &&
                        !entry.getKey().toString().equalsIgnoreCase(EVENT_ACTION) &&
                        !entry.getKey().toString().equalsIgnoreCase(EVENT_LABEL)) {
                    //send custom dimension
                    if(entry.getKey() instanceof Integer) {
                        tracker.send(eventBuilder
                                .setCustomDimension(Integer.parseInt((entry.getKey()).toString()), entry.getValue().toString())
                                .build());
                    }
                    //eventBuilder.set(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }



        tracker.send(eventBuilder.build());
        Log.e(TAG, "sendEventWithProperties");
    }

    @Override
    public void sendScreenViewEvent(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        Log.e(TAG, "sendScreenViewEvent");
    }

    @Override
    public void sendSessionEvent() {
        tracker.send(new HitBuilders.EventBuilder()
                .setNewSession()
                .build()
        );
        Log.e(TAG, "sendSessionEvent");
    }

    @Override
    public void sendCaughtException(Exception e) {
        sendCaughtException(e, false);
    }

    @Override
    public void sendCaughtException(Exception e, boolean isFatal) {
        tracker.send(new HitBuilders.ExceptionBuilder()
                .setDescription(e.getLocalizedMessage())
                .setFatal(isFatal)
                .build()
        );
        Log.e(TAG, "sendCaughtException");
    }
}
