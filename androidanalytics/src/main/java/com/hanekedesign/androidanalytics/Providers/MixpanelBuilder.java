package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/23/17.
 */

public class MixpanelBuilder extends ProviderBuilder {

    //required
    String token;
    Context context;
    //optional
    boolean sendEventsImmediately = false;
    String defaultEventName = "Default";
    String sessionEventName = "Session";
    String screenViewEventName = "Screen View";
    String exceptionEventName = "Exception";

    MixpanelAPI mixpanel;
    JSONObject properties = new JSONObject();

    public MixpanelBuilder(Context context, String token) {
        this.context = context;
        this.token = token;
        mixpanel = MixpanelAPI.getInstance(context, token);
    }

    public MixpanelBuilder initPushHandling(String pushToken) {
        mixpanel.getPeople().identify(token);
        mixpanel.getPeople().initPushHandling(pushToken);

        return this;
    }

    public MixpanelBuilder registerSuperProperties(HashMap<?, ?> hashMap) {
        for(Map.Entry<?, ?> entry : hashMap.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            try {
                properties.put(key, value);
                mixpanel.registerSuperProperties(properties);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public MixpanelBuilder sendUserId(String userId) {
        mixpanel.identify(userId);
        mixpanel.getPeople().identify(userId);

        return this;
    }

    public MixpanelBuilder sendEventsImmediately(boolean sendEventsImmediately) {
        this.sendEventsImmediately = sendEventsImmediately;

        return this;
    }

    public MixpanelBuilder setDefaultEventName(String eventName) {
        this.defaultEventName = eventName;

        return this;
    }

    public MixpanelBuilder setSessionEventName(String eventName) {
        this.sessionEventName = eventName;

        return this;
    }

    public MixpanelBuilder setScreenViewEventName(String eventName) {
        this.screenViewEventName = eventName;

        return this;
    }

    public MixpanelBuilder setExceptionEventName(String eventName) {
        this.exceptionEventName = eventName;

        return this;
    }

    @Override
    public MixpanelProvider build() {
        return new MixpanelProvider(this);
    }

}