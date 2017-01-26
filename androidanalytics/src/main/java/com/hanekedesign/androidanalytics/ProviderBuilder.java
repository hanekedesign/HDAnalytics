package com.hanekedesign.androidanalytics;

public interface ProviderBuilder {

    /**
     * Builds the Analytics Provider's object to be used to send events
     * @return          Active Analytics Provider Object
     */
    AnalyticsProvider build();
}
