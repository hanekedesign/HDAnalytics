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

    MixpanelAPI mixpanel;
    JSONObject properties = new JSONObject();

    public MixpanelBuilder(Context context, String token) {
        this.context = context;
        this.token = token;
        mixpanel = MixpanelAPI.getInstance(context, token);
    }

    public MixpanelBuilder registerSuperProperties(HashMap<?, ?> hashMap) {
        for(Map.Entry<?, ?> entry : hashMap.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();

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
    public MixpanelProvider build() {
        return new MixpanelProvider(this);
    }

}