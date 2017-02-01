package com.hanekedesign.mixpanel;

import android.content.Context;
import android.util.Log;

import com.hanekedesign.hdanalytics.AnalyticsProvider;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MixpanelProvider implements AnalyticsProvider {

    private String token;
    private Context context;
    private String userId;
    private String eventName = "Default";
    private String sessionEventName = "Session";
    private String screenViewEventName = "Screen View";
    private String exceptionEventName = "Exception";

    private MixpanelAPI mixpanel;

    MixpanelProvider(MixpanelBuilder builder, MixpanelAPI mixpanel) {
        this.token = builder.token;
        this.context = builder.context;
        this.eventName = builder.defaultEventName;

        this.mixpanel = mixpanel;
    }

    @Override
    public void sendUserId(String userId) {
        this.userId = userId;
        mixpanel.identify(userId);
        mixpanel.getPeople().identify(userId);
    }

    @Override
    public void sendEvent(String event) {
        sendEventWithProperties(event, null);
    }

    @Override
    public void sendEventWithProperties(String event, HashMap<String, ?> eventMap) {
        JSONObject properties = new JSONObject();
        if(eventMap == null) {
            mixpanel.track(event);
        }
        else {
            for(Map.Entry<String, ?> entry : eventMap.entrySet()) {
                try {
                    properties.put(entry.getKey(), entry.getValue());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mixpanel.track(event, properties);
        }
    }

    @Override
    public void sendScreenViewEvent(String screenName) {
        JSONObject properties = new JSONObject();
        try {
            properties.put(screenViewEventName, screenName);
            mixpanel.track(eventName, properties);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSessionEvent() {
        JSONObject properties = new JSONObject();
        try {
            properties.put(sessionEventName, new Date());
            mixpanel.track(eventName, properties);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCaughtException(Exception e) {
        sendCaughtException(e, false);
    }

    @Override
    public void sendCaughtException(Exception e, boolean isFatal) {
        JSONObject properties = new JSONObject();
        try {
            properties.put(exceptionEventName, e.getLocalizedMessage());
            mixpanel.track(eventName, properties);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUserProfile(String key, Object value) {
        mixpanel.getPeople().identify(userId);
        mixpanel.getPeople().set(key, value);
    }

    @Override
    public void addSuperProperties(HashMap<String, ?> hashMap) {
        JSONObject properties = new JSONObject();
        for(Map.Entry<String, ?> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                properties.put(key, value);
                mixpanel.registerSuperProperties(properties);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeSuperProperty(String propertyName) {
        mixpanel.unregisterSuperProperty(propertyName);
    }

    @Override
    public void removeAllSuperProperties() {
        mixpanel.clearSuperProperties();
    }
}
