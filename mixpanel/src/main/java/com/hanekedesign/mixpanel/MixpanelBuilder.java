package com.hanekedesign.mixpanel;

import android.content.Context;

import com.hanekedesign.hdanalytics.ProviderBuilder;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MixpanelBuilder implements ProviderBuilder {

    //required
    String token;
    Context context;
    //optional
    String defaultEventName = "Default";

    MixpanelAPI mixpanel;
    JSONObject properties = new JSONObject();

    public MixpanelBuilder(Context context, String token) {
        this.context = context;
        this.token = token;
        mixpanel = MixpanelAPI.getInstance(context, token);
    }

    /**
     * Allow push notifications to be sent to the client
     *
     * @param pushToken         Google Sender ID
     * @return                  MixpanelBuilder object
     */
    public MixpanelBuilder initPushHandling(String pushToken) {
        mixpanel.getPeople().identify(token);
        mixpanel.getPeople().initPushHandling(pushToken);

        return this;
    }

    /**
     * Registers super properties to be sent with every event
     *
     * @param hashMap           Hashmap of key/value pairs
     * @return                  MixpanelBuilder object
     */
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

    @Override
    public MixpanelProvider build() {
        return new MixpanelProvider(this, mixpanel);
    }

}