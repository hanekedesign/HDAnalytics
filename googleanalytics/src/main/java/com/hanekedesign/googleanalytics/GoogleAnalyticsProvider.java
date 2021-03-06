package com.hanekedesign.googleanalytics;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.hdanalytics.AnalyticsProvider;
import com.hanekedesign.hdanalytics.TimedEventException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GoogleAnalyticsProvider implements AnalyticsProvider {

    public static final String EVENT_CATEGORY = "category";
    public static final String EVENT_ACTION = "action";
    public static final String EVENT_LABEL = "label";

    private Tracker tracker;
    GoogleAnalytics analytics;

    //required
    private String providerId;
    private Context context;

    //optional
    private String category = "Default";
    private String action = "Default";
    private HashMap<String, String> timedEvents = new HashMap<>();

    GoogleAnalyticsProvider(GoogleAnalyticsBuilder builder) {
        //required
        providerId = builder.providerId;
        this.context = builder.context;

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

    public void sendUserId(String userId) {
        tracker.set("&uid", userId);
    }

    @Override
    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    @Override
    public void sendEventWithProperties(String event, HashMap<String, ?> eventMap) {
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
    }

    @Override
    public void sendScreenViewEvent(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void sendSessionEvent() {
        tracker.send(new HitBuilders.EventBuilder()
                .setNewSession()
                .build()
        );
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
    }

    @Override
    public void updateUserProfile(String key, Object value) {

    }

    @Override
    public void addSuperProperties(HashMap<String, ?> hashMap) {

    }

    @Override
    public void removeSuperProperty(String propertyName) {

    }

    @Override
    public void removeAllSuperProperties() {

    }

    @Override
    public void startTimedEvent(String eventName) {
        timedEvents.put(eventName, (new Date()).toString());
    }

    @Override
    public void stopTimedEvent(String eventName) throws TimedEventException {
        if(!timedEvents.containsKey(eventName))
            throw new TimedEventException("Event name does not exist");

        stopTimedEvent(eventName, new HashMap());
    }

    @Override
    public void stopTimedEvent(String eventName, HashMap hashMap) throws TimedEventException {
        if(!timedEvents.containsKey(eventName))
            throw new TimedEventException("Event name does not exist");

        long oldDate = Date.parse(timedEvents.get(eventName));
        long newDate = Date.parse(new Date().toString());

        long eventTime = newDate - oldDate;
        String eventTimeString = (new SimpleDateFormat("hh:mm:ss")).format(new Date(eventTime));

        hashMap.put(EVENT_CATEGORY, eventName);
        hashMap.put(EVENT_ACTION, "Duration");
        hashMap.put(EVENT_LABEL, eventTimeString);
        timedEvents.remove(eventName);

        sendEventWithProperties(eventName, hashMap);
    }
}
