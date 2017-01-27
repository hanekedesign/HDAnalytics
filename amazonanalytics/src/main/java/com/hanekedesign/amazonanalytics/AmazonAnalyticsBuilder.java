package com.hanekedesign.amazonanalytics;

import android.content.Context;
import android.util.Log;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.InitializationException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.hanekedesign.androidanalytics.ProviderBuilder;

public class AmazonAnalyticsBuilder implements ProviderBuilder {

    //required
    Context context;
    String appId;
    String poolId;
    //optional
    String defaultEventName = "Default";
    String defaultScreenEvent = "Screen View";

    MobileAnalyticsManager analyticsManager;

    public AmazonAnalyticsBuilder(Context context, String appId, String poolId) {
        this.context = context;
        this.appId = appId;
        this.poolId = poolId;

        try {
            analyticsManager = MobileAnalyticsManager.getOrCreateInstance(
                    this.context,
                    this.appId,
                    this.poolId
            );
        }
        catch (InitializationException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    @Override
    public AmazonAnalyticsProvider build() {
        return new AmazonAnalyticsProvider(this);
    }

}