package com.hanekedesign.androidanalytics;

import com.hanekedesign.androidanalytics.AnalyticsProvider;

/**
 * Created by nthunem on 1/19/17.
 */

public interface ProviderBuilder {

    /**
     * Builds the Analytics Provider's object to be used to send events
     * @return          Active Analytics Provider Object
     */
    AnalyticsProvider build();
}
