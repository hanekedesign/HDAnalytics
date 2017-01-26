package com.hanekedesign.googleanalytics;

import android.content.Context;

import com.hanekedesign.androidanalytics.ProviderBuilder;

/**
 * Created by nthunem on 1/19/17.
 */

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
     * Sets default category title
     *
     * @param defaultCategory   Default category title
     * @return                  GoogleAnalyticsBuilder object
     */
    public GoogleAnalyticsBuilder defaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
        return this;
    }

    /**
     * Sets default action title
     *
     * @param defaultAction     Default action title
     * @return                  GoogleAnalyticsBuilder object
     */
    public GoogleAnalyticsBuilder defaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
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