package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;
import android.util.Log;

import com.hanekedesign.androidanalytics.AnalyticsProvider;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/23/17.
 */

public class MixpanelProvider implements AnalyticsProvider {

    private static final String TAG = "Mixpanel Provider";

    private String token;
    private Context context;
    private String userId;
    private boolean sendEventsImmediately = false;
    private String eventName = "Default";
    private String sessionEventName = "Session";
    private String screenViewEventName = "Screen View";
    private String exceptionEventName = "Exception";

    private MixpanelAPI mixpanel;

    MixpanelProvider(MixpanelBuilder builder, MixpanelAPI mixpanel) {
        this.token = builder.token;
        this.context = builder.context;
        this.sendEventsImmediately = builder.sendEventsImmediately;
        this.eventName = builder.defaultEventName;

        Log.e(TAG, "TOKEN = " + token);

        this.mixpanel = mixpanel;
//        mixpanel = MixpanelAPI.getInstance(context, token);
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
                String key = entry.getKey();
                String value = entry.getValue().toString();

                try {
                    properties.put(key, value);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mixpanel.track(event, properties);
        }
        if(sendEventsImmediately)
            mixpanel.flush();

        Log.e(TAG, "sendEventWithProperties");
    }

    @Override
    public void sendScreenViewEvent(String screenName) {
        JSONObject properties = new JSONObject();
        try {
            properties.put(screenViewEventName, screenName);
            mixpanel.track(eventName, properties);
            Log.e(TAG, "sendScreenViewEvent");
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
            Log.e(TAG, "sendSessionEvent");
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
            Log.e(TAG, "sendCaughtException");
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

    public void flushMixPanel() {
        mixpanel.flush();
    }
}
