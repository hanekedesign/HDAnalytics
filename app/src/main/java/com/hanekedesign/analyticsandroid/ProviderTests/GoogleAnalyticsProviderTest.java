package com.hanekedesign.analyticsandroid.ProviderTests;

import android.content.Context;
import android.util.Log;

import com.hanekedesign.androidanalytics.Analytics;
import com.hanekedesign.androidanalytics.AnalyticsProvider;
import com.hanekedesign.androidanalytics.Providers.GoogleAnalyticsBuilder;
import com.hanekedesign.androidanalytics.Providers.GoogleAnalyticsProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/19/17.
 */

public class GoogleAnalyticsProviderTest {

    GoogleAnalyticsProvider googleAnalyticsProviderDefault;
    GoogleAnalyticsProvider googleAnalyticsProviderFrequency;
    GoogleAnalyticsProvider googleAnalyticsProviderAdvertising;
    GoogleAnalyticsProvider googleAnalyticsProviderCategory;
    GoogleAnalyticsProvider googleAnalyticsProviderAction;
    GoogleAnalyticsProvider googleAnalyticsProviderUncaught;
    GoogleAnalyticsProvider googleAnalyticsProviderUserid;

    private HashMap eventMap         = new HashMap();
    private HashMap eventMapCategory = new HashMap();
    private HashMap eventMapAction   = new HashMap();
    private HashMap eventMapLabel    = new HashMap();
    private HashMap eventMapValue    = new HashMap();

    private HashMap exceptionMap          = new HashMap();
    private HashMap exceptionMapException = new HashMap();
    private HashMap exceptionMapIsFatal   = new HashMap();

    private final String TAG                = "GoogleAnalytics Test";
    private final String PROPERTY_ID        = "UA-90565556-1";
    private final String USER_ID            = "test_id00000001";
    private final int DISPATCH_FREQUENCY    = 30;  //seconds
    private final boolean SEND_ADVERTISING  = true;
    private final String EVENT_STRING       = "test_event";
    private final String EVENT_CATEGORY_DFEAULT     = "test_category_default";
    private final String EVENT_CATEGORY_FREQUENCY   = "test_category_frequency";
    private final String EVENT_CATEGORY_ADVERTISING = "test_category_advertising";
    private final String EVENT_CATEGORY_UNCAUGHT    = "test_category_uncaught";
    private final String EVENT_CATEGORY_USERID      = "test_category_userid";
    private final String EVENT_CATEGORY_CATEGORY    = "test_category_category";
    private final String EVENT_CATEGORY_ACTION      = "test_category_category";
    private final String EVENT_CATEGORY_LABEL       = "test_category_category";
    private final String EVENT_CATEGORY_VALUE       = "test_category_category";
    private final String EVENT_ACTION       = "test_action";
    private final String EVENT_LABEL        = "test_label";
    private final String EVENT_VALUE        = "test_value";
    private final String SCREENNAME         = "test_screenname";
    private final String EXCEPTION_DESCRIPTION      = "test_description";
    private final boolean EXCEPTION_IS_FATAL        = true;
    private final boolean SEND_UNCAUGHT_EXCEPTION   = true;
    private final boolean SEND_USER_ID              = true;


    public GoogleAnalyticsProviderTest(Context context) {
        googleAnalyticsProviderDefault = new GoogleAnalyticsBuilder(context)
                .build();

        googleAnalyticsProviderFrequency = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .defaultCategory(EVENT_CATEGORY_FREQUENCY)
                .build();

        googleAnalyticsProviderAdvertising = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_ADVERTISING)
                .build();

        googleAnalyticsProviderCategory = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_CATEGORY)
                .build();

        googleAnalyticsProviderAction = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_ACTION)
                .defaultAction(EVENT_ACTION)
                .build();

        googleAnalyticsProviderUncaught = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_UNCAUGHT)
                .defaultAction(EVENT_ACTION)
                .sendUncaughtExceptions(SEND_UNCAUGHT_EXCEPTION)
                .build();

        googleAnalyticsProviderUserid = new GoogleAnalyticsBuilder(context)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_USERID)
                .defaultAction(EVENT_ACTION)
                .sendUncaughtExceptions(SEND_UNCAUGHT_EXCEPTION)
                .sendUserId(SEND_USER_ID)
                .build();

        //eventMap stays empty

        eventMapCategory.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_CATEGORY);

        eventMapAction.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_ACTION);
        eventMapAction.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);

        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_LABEL);
        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);
        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_LABEL, EVENT_LABEL);

        eventMapValue.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_VALUE);
        eventMapValue.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);
        eventMapValue.put(GoogleAnalyticsProvider.EVENT_LABEL, EVENT_LABEL);
        eventMapValue.put(GoogleAnalyticsProvider.EVENT_VALUE, EVENT_VALUE);

        //exceptionMap stays empty

        exceptionMapException.put(GoogleAnalyticsProvider.EXCEPTION_DESCRIPTION, EXCEPTION_DESCRIPTION);

        exceptionMapIsFatal.put(GoogleAnalyticsProvider.EXCEPTION_DESCRIPTION, EXCEPTION_DESCRIPTION);
        exceptionMapIsFatal.put(GoogleAnalyticsProvider.EXCEPTION_IS_FATAL, EXCEPTION_IS_FATAL);

        //can only test one at a time
        runTests(googleAnalyticsProviderDefault);
        runTests(googleAnalyticsProviderFrequency);
        runTests(googleAnalyticsProviderAdvertising);
        runTests(googleAnalyticsProviderCategory);
        runTests(googleAnalyticsProviderAction);
        runTests(googleAnalyticsProviderUncaught);
        runTests(googleAnalyticsProviderUserid);
    }

    public void runTests(AnalyticsProvider provider) {
        Analytics.addProvider(provider);

        if(Analytics.getProviderInstance(provider) != provider)
            Log.e(TAG, "Provider does not match current");

        HashMap<String, AnalyticsProvider> providerHashMap = Analytics.getAllProviders();
        for (Map.Entry<String, AnalyticsProvider> entry : providerHashMap.entrySet()) {
            Log.d(TAG, entry.getKey());
        }

        Analytics.sendEvent(EVENT_STRING);

        Analytics.sendEventWithProperties(EVENT_STRING, eventMap);
        Analytics.sendEventWithProperties(null, eventMap);
        Analytics.sendEventWithProperties(EVENT_STRING, eventMapCategory);
        Analytics.sendEventWithProperties(null, eventMapCategory);
        Analytics.sendEventWithProperties(EVENT_STRING, eventMapAction);
        Analytics.sendEventWithProperties(null, eventMapAction);
        Analytics.sendEventWithProperties(EVENT_STRING, eventMapLabel);
        Analytics.sendEventWithProperties(null, eventMapLabel);
        Analytics.sendEventWithProperties(EVENT_STRING, eventMapValue);
        Analytics.sendEventWithProperties(null, eventMapValue);

        Analytics.sendScreenViewEvent(SCREENNAME);

        Analytics.sendSessionEvent();

        Exception exception = new Exception();
        try {
            int divideByZero = 1 / 0;
        } catch (Exception e) {
            exception = e;
        }
        Analytics.sendCaughtException(exception);
        Analytics.sendCaughtException(exception, true);

        Analytics.removeProvider(provider);

        //cause uncaught exception
        //int divideByZero = 1 / 0;
    }
}
