package com.hanekedesign.analyticsandroid.ProviderTests;

import android.content.Context;
import android.util.Log;

import com.hanekedesign.analyticsandroid.Providers.GoogleAnalyticsProvider;

import java.util.HashMap;

/**
 * Created by nthunem on 1/19/17.
 */

public class GoogleAnalyticsProviderTest {

    private GoogleAnalyticsProvider googleAnalytics;
    private final String TAG = "GoogleAnalytics Test";
    private final String PROPERTY_ID = "UA-90565556-1";
    private final String USER_ID = "test_id00000001";
    private final int DISPATCH_FREQUENCY = 30;  //seconds
    private final boolean SET_ADVERTISING = true;
    private final String EVENT_CATEGORY = "test_category";
    private final String EVENT_ACTION = "test_action";
    private final String EVENT_LABEL = "test_label";
    private final String EVENT_VALUE = "test_value";
    private HashMap eventMap = new HashMap();
    private final String SCREENNAME = "test_screenname";
    private HashMap exceptionMap = new HashMap();
    private final String EXCEPTION_DESCRIPTION = "test_description";
    private final boolean EXCEPTION_IS_FATAL = true;
    private final boolean SEND_UNCAUGHT_EXCEPTION = true;


    public GoogleAnalyticsProviderTest(Context context) {
        googleAnalytics = new GoogleAnalyticsProvider(context);

        eventMap.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY);
        eventMap.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);
        eventMap.put(GoogleAnalyticsProvider.EVENT_LABEL, EVENT_LABEL);
        eventMap.put(GoogleAnalyticsProvider.EVENT_VALUE, EVENT_VALUE);

        exceptionMap.put(GoogleAnalyticsProvider.EXCEPTION_DESCRIPTION, EXCEPTION_DESCRIPTION);
        exceptionMap.put(GoogleAnalyticsProvider.EXCEPTION_IS_FATAL, EXCEPTION_IS_FATAL);

        runTests();
    }

    public void runTests() {
        googleAnalytics.setupProvider(PROPERTY_ID);
        Log.d(TAG, googleAnalytics.getProviderInstance().toString());
        googleAnalytics.sendUserId(USER_ID);
        googleAnalytics.setDispatchFrequency(DISPATCH_FREQUENCY);
        googleAnalytics.setAdvertisingFeatures(SET_ADVERTISING);
        googleAnalytics.sendEvent();
        googleAnalytics.sendEvent(EVENT_CATEGORY);
        googleAnalytics.sendEventWithProperties(eventMap);
        googleAnalytics.sendEventWithProperties(GoogleAnalyticsProvider.EVENT_CATEGORY, eventMap);
        googleAnalytics.sendScreenViewEvent(SCREENNAME);
        googleAnalytics.sendSessionEvent();
        googleAnalytics.sendCaughtException(exceptionMap);
        googleAnalytics.setUncaughtExceptionEvent(SEND_UNCAUGHT_EXCEPTION);

        //cause uncaught exception
        int divideByZero = 1 / 0;

        //googleAnalytics.removeProvider();
    }

}
