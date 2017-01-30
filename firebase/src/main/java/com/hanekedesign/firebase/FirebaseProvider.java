package com.hanekedesign.firebase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.hanekedesign.hdanalytics.AnalyticsProvider;

import java.util.HashMap;
import java.util.Map;

public class FirebaseProvider implements AnalyticsProvider {

    private Context context;

    private String eventNameTitle;
    private String screenNameTitle;
    private String sessionTitle;
    private String sessionEvent = "new_session";

    private String userId;

    private FirebaseAnalytics firebaseAnalytics;

    FirebaseProvider(FirebaseBuilder builder) {
        this.context = builder.context;
        this.eventNameTitle = builder.defaultEventTitle;
        this.screenNameTitle = builder.defaultScreenNameTitle;
        this.sessionTitle = builder.defaultSessionTitle;

        firebaseAnalytics = FirebaseAnalytics.getInstance(this.context);
        firebaseAnalytics.setMinimumSessionDuration(builder.minimumSessionDuration);
    }

    @Override
    public void sendUserId(String userId) {
        this.userId = userId;
        firebaseAnalytics.setUserId(userId);
    }

    @Override
    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    @Override
    public void sendEventWithProperties(String event, HashMap<String, ?> eventMap) {
        Bundle params = new Bundle();
        if(event != null)
            eventNameTitle = event;

        if(eventMap != null) {
            for(Map.Entry<String, ?> entry : eventMap.entrySet()) {
                if(entry.getValue() instanceof String) {
                    params.putString(entry.getKey(), ((String) entry.getValue()));
                }
                else if(entry.getValue() instanceof Integer) {
                    params.putInt(entry.getKey(), ((Integer) entry.getValue()));
                }
                else if(entry.getValue() instanceof Boolean) {
                    params.putBoolean(entry.getKey(), ((Boolean) entry.getValue()));
                }
                else if(entry.getValue() instanceof Float) {
                    params.putFloat(entry.getKey(), ((Float) entry.getValue()));
                }
                else if(entry.getValue() instanceof Double) {
                    params.putDouble(entry.getKey(), ((Double) entry.getValue()));
                }
                else if(entry.getValue() instanceof Character) {
                    params.putChar(entry.getKey(), ((Character) entry.getValue()));
                }
                else if(entry.getValue() instanceof CharSequence) {
                    params.putCharSequence(entry.getKey(), ((CharSequence) entry.getValue()));
                }
                else {}
            }
        }
        firebaseAnalytics.logEvent(eventNameTitle, params);
    }

    @Override
    public void sendScreenViewEvent(String screenName) {
        Bundle params = new Bundle();
        params.putString(screenNameTitle, screenName);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
    }

    @Override
    public void sendSessionEvent() {
        Bundle params = new Bundle();
        params.putString(sessionTitle, sessionEvent);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);
    }

    @Override
    public void sendCaughtException(Exception e) {
        sendCaughtException(e, false);
    }

    @Override
    public void sendCaughtException(Exception e, boolean isFatal) {
        FirebaseCrash.report(e);
    }

    @Override
    public void updateUserProfile(String key, Object value) {
        firebaseAnalytics.setUserProperty(key, value.toString());
    }

    @Override
    public void addSuperProperties(HashMap<String, ?> hashMap) {

    }
}
