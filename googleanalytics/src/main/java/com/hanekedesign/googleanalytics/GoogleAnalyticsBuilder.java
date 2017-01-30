package com.hanekedesign.googleanalytics;

import android.content.Context;

import com.hanekedesign.hdanalytics.ProviderBuilder;

public class GoogleAnalyticsBuilder implements ProviderBuilder {

    //required
    final String providerId;
    final Context context;
    //optional
    int dispatchFrequency = 0;
    boolean sendAdvertising = false;
    boolean sendUncaughtExceptions = false;
    String defaultCategory;
    String defaultAction;

    public GoogleAnalyticsBuilder(Context context, String providerId) {
        this.providerId = providerId;
        this.context = context;
    }

    /**
     * Sets frequency to send events
     *
     * @param seconds           Seconds in which to send events
     * @return                  GoogleAnalyticsBuilder object
     */
    public GoogleAnalyticsBuilder dispatchFrequency(int seconds) {
        this.dispatchFrequency = seconds;
        return this;
    }

    /**
     * Sets whether to track how the app was discovered (App Linking)
     *
     * @param sendAdvertising   True/false
     * @return                  GoogleAnalyticsBuilder object
     */
    public GoogleAnalyticsBuilder sendAdvertising(boolean sendAdvertising) {
        this.sendAdvertising = sendAdvertising;
        return this;
    }

    /**
     * Sets whether to send uncaught exceptions and app crashes to Google Analytics
     *
     * @param sendUncaughtExceptions    True/False
     * @return                          GoogleAnalyticsBuilder object
     */
    public GoogleAnalyticsBuilder sendUncaughtExceptions(boolean sendUncaughtExceptions) {
        this.sendUncaughtExceptions = sendUncaughtExceptions;
        return this;
    }

    @Override
    public GoogleAnalyticsProvider build() {
        return new GoogleAnalyticsProvider(this);
    }
}