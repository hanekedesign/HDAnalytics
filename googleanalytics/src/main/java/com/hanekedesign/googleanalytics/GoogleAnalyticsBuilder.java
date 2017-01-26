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

    public GoogleAnalyticsBuilder dispatchFrequency(int seconds) {
        this.dispatchFrequency = seconds;
        return this;
    }

    public GoogleAnalyticsBuilder sendAdvertising(boolean sendAdvertising) {
        this.sendAdvertising = sendAdvertising;
        return this;
    }

    public GoogleAnalyticsBuilder defaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
        return this;
    }

    public GoogleAnalyticsBuilder defaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
        return this;
    }

    public GoogleAnalyticsBuilder sendUncaughtExceptions(boolean sendUncaughtExceptions) {
        this.sendUncaughtExceptions = sendUncaughtExceptions;
        return this;
    }

    @Override
    public GoogleAnalyticsProvider build() {
        return new GoogleAnalyticsProvider(this);
    }
}