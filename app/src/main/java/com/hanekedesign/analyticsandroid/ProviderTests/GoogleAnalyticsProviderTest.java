package com.hanekedesign.analyticsandroid.ProviderTests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
    GoogleAnalyticsProvider googleAnalyticsProviderProperties;

    private HashMap eventMap         = new HashMap();
    private HashMap eventMapCategory = new HashMap();
    private HashMap eventMapAction   = new HashMap();
    private HashMap eventMapLabel    = new HashMap();
    private HashMap properties = new HashMap();

    private String[] keys = {"KEY 1", "KEY 2", "KEY 3", "KEY 4"};
    private String[] values = {"VALUE 1", "VALUE 2", "VALUE 3", "VALUE 4"};

    private final int DIMENSION_ADD_CONTACT_INDEX = 1;
    private final String DIMENSION_ADD_CONTACT_VALUE = "Account 555";

    private final String TAG                = "GoogleAnalytics Test";
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
    private final String EVENT_CATEGORY_PROPERTY    = "Sign In";
    private final String EVENT_ACTION_PROPERTY      = "Custom Sign In Event";
    private final String EVENT_ACTION       = "test_action";
    private final String EVENT_LABEL        = "test_label";
    private final boolean SEND_UNCAUGHT_EXCEPTION   = true;

    Context context;

    public GoogleAnalyticsProviderTest(Context context, String providerId) {
        this.context = context;

        googleAnalyticsProviderDefault = new GoogleAnalyticsBuilder(context, providerId)
                .build();

        googleAnalyticsProviderFrequency = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .defaultCategory(EVENT_CATEGORY_FREQUENCY)
                .build();

        googleAnalyticsProviderAdvertising = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_ADVERTISING)
                .build();

        googleAnalyticsProviderCategory = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_CATEGORY)
                .build();

        googleAnalyticsProviderAction = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_ACTION)
                .defaultAction(EVENT_ACTION)
                .build();

        googleAnalyticsProviderUncaught = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_UNCAUGHT)
                .defaultAction(EVENT_ACTION)
                .sendUncaughtExceptions(SEND_UNCAUGHT_EXCEPTION)
                .build();

        googleAnalyticsProviderUserid = new GoogleAnalyticsBuilder(context, providerId)
                .dispatchFrequency(DISPATCH_FREQUENCY)
                .sendAdvertising(SEND_ADVERTISING)
                .defaultCategory(EVENT_CATEGORY_USERID)
                .defaultAction(EVENT_ACTION)
                .sendUncaughtExceptions(SEND_UNCAUGHT_EXCEPTION)
                .build();

        googleAnalyticsProviderProperties = new GoogleAnalyticsBuilder(context, providerId)
                .defaultCategory(EVENT_CATEGORY_PROPERTY)
                .defaultAction(EVENT_ACTION_PROPERTY)
                .build();

        //eventMap stays empty

        eventMapCategory.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_CATEGORY);

        eventMapAction.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_ACTION);
        eventMapAction.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);

        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_CATEGORY, EVENT_CATEGORY_LABEL);
        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_ACTION, EVENT_ACTION);
        eventMapLabel.put(GoogleAnalyticsProvider.EVENT_LABEL, EVENT_LABEL);

        Log.e(TAG, "Objects set");

        //can only test one at a time
        runTests(googleAnalyticsProviderDefault);
//        runTests(googleAnalyticsProviderFrequency);
//        runTests(googleAnalyticsProviderAdvertising);
//        runTests(googleAnalyticsProviderCategory);
//        runTests(googleAnalyticsProviderAction);
//        runTests(googleAnalyticsProviderUncaught);
//        runTests(googleAnalyticsProviderUserid);
    }

    public void runTests(AnalyticsProvider provider) {
        Log.e(TAG, "Running test: " + provider.getClass().getName());

        Analytics.addProvider(provider);

//        for(int i = 0; i < keys.length; ++i) {
//            for(int j = 0; j < 1; ++j) {
//                properties.put("&" + keys[i], values[j]);
//            }
//            Analytics.sendEventWithProperties(null, properties);
//            properties.clear();
//        }
//
//        for(int i = 0; i < keys.length; ++i) {
//            for(int j = 0; j < 2; ++j) {
//                properties.put("&" + keys[i], values[j]);
//            }
//            Analytics.sendEventWithProperties(null, properties);
//            properties.clear();
//        }
//
//        for(int i = 0; i < keys.length; ++i) {
//            for(int j = 0; j < 3; ++j) {
//                properties.put("&" + keys[i], values[j]);
//            }
//            Analytics.sendEventWithProperties(null, properties);
//            properties.clear();
//        }
//
//        for(int i = 0; i < keys.length; ++i) {
//            for(int j = 0; j < 4; ++j) {
//                properties.put("&" + keys[i], values[j]);
//            }
//            Analytics.sendEventWithProperties(null, properties);
//            properties.clear();
//        }

        properties.put(DIMENSION_ADD_CONTACT_INDEX, DIMENSION_ADD_CONTACT_INDEX);
        Analytics.sendEventWithProperties(null, properties);
        properties.clear();

//        if(Analytics.getProviderInstance(provider) != provider)
//            Log.e(TAG, "Provider does not match current");
//
//        HashMap<String, AnalyticsProvider> providerHashMap = Analytics.getAllProviders();
//        for (Map.Entry<String, AnalyticsProvider> entry : providerHashMap.entrySet()) {
//            Log.e(TAG, entry.getKey());
//        }
//
//        Analytics.sendEvent(EVENT_STRING);
//        Analytics.sendEvent(null);
//
//        Analytics.sendEventWithProperties(EVENT_STRING, eventMap);
//        Analytics.sendEventWithProperties(null, eventMap);
//        Analytics.sendEventWithProperties(EVENT_STRING, eventMapCategory);
//        Analytics.sendEventWithProperties(null, eventMapCategory);
//        Analytics.sendEventWithProperties(EVENT_STRING, eventMapAction);
//        Analytics.sendEventWithProperties(null, eventMapAction);
//        Analytics.sendEventWithProperties(EVENT_STRING, eventMapLabel);
//        Analytics.sendEventWithProperties(null, eventMapLabel);
//        Analytics.sendEventWithProperties(EVENT_STRING, eventMapValue);
//        Analytics.sendEventWithProperties(null, eventMapValue);
//
//        Analytics.sendScreenViewEvent(SCREENNAME);
//
//        Analytics.sendSessionEvent();
//
//        Exception exception = new Exception();
//        try {
//            int divideByZero = 1 / 0;
//        } catch (Exception e) {
//            exception = e;
//        }
//        Analytics.sendCaughtException(exception);
//        Analytics.sendCaughtException(exception, true);
//
//        Analytics.removeProvider(provider);
//
//        Log.e(TAG, "Test finished: " + provider.getClass().getName());

        //cause uncaught exception
        //int divideByZero = 1 / 0;

        Toast.makeText(context, "Tests completed", Toast.LENGTH_SHORT).show();
    }
}
