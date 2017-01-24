package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hanekedesign.androidanalytics.AnalyticsProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/24/17.
 */

public class FirebaseProvider implements AnalyticsProvider {

    private Context context;
    private String providerId;

    private String eventName = "Default;"
    private String userId;

    private FirebaseAnalytics firebaseAnalytics;

    FirebaseProvider(FirebaseBuilder builder) {
        this.context = builder.context;
        this.providerId = builder.providerId;

        firebaseAnalytics = FirebaseAnalytics.getInstance(this.context);
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
            eventName = event;

        if(eventMap != null) {
            for(Map.Entry<String, ?> entry : eventMap.entrySet()) {
                if(entry.getValue() instanceof String) {
                    params.putString(entry.getKey(), ((String) entry.getValue()));
                }
                else if(entry.getValue() instanceof Integer) {
                    params.putInt(entry.getKey(), ((Integer) entry.getValue());
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
        firebaseAnalytics.logEvent(eventName, params);
    }

    @Override
    public void sendScreenViewEvent(String screenName) {

    }

    @Override
    public void sendSessionEvent() {

    }

    @Override
    public void sendCaughtException(Exception e) {

    }

    @Override
    public void sendCaughtException(Exception e, boolean isFatal) {

    }

    @Override
    public void updateUserProfile(String key, Object value) {

    }
}
