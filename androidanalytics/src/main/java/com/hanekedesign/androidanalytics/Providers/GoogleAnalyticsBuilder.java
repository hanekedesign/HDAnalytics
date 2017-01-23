package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;

/**
 * Created by nthunem on 1/19/17.
 */

public class GoogleAnalyticsBuilder extends ProviderBuilder {

    //required
    final String providerId;
    final Context context;
    //optional
    int dispatchFrequency = 0;
    boolean sendAdvertising = false;
    boolean sendUncaughtExceptions = false;
    String userId = "";
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

    public GoogleAnalyticsBuilder sendUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public GoogleAnalyticsProvider build() {
        return new GoogleAnalyticsProvider(this);
    }
}