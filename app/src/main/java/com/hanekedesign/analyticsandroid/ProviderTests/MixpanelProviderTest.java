package com.hanekedesign.analyticsandroid.ProviderTests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hanekedesign.androidanalytics.Analytics;
import com.hanekedesign.androidanalytics.AnalyticsProvider;
import com.hanekedesign.androidanalytics.Providers.MixpanelBuilder;
import com.hanekedesign.androidanalytics.Providers.MixpanelProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/23/17.
 */

public class MixpanelProviderTest {

    MixpanelProvider mixpanelProviderDefault;
    MixpanelProvider mixpanelProviderUserId;
    MixpanelProvider mixpanelProviderSendImmediately;
    MixpanelProvider mixpanelProviderEventName;
    MixpanelProvider mixpanelProviderSessionName;
    MixpanelProvider mixpanelProviderScreenViewName;
    MixpanelProvider mixpanelProviderExceptionName;
    MixpanelProvider mixpanelProviderSuper1;
    MixpanelProvider mixpanelProviderSuper2;
    MixpanelProvider mixpanelProviderSuper3;

    HashMap defaultMap          = new HashMap();
    HashMap superProperties1    = new HashMap();
    HashMap superProperties2    = new HashMap();
    HashMap superProperties3    = new HashMap();
    HashMap properties1         = new HashMap();
    HashMap properties2         = new HashMap();
    HashMap properties3         = new HashMap();

    private final String TAG            = "Mixpanel Test";
    private final String USER_ID        = "1234321";
    private final String EVENT_STRING   = "Test Event String";
    private final String SCREEN_STRING  = "Test Screen String";
    private final String SUPER_1        = "Test Super String";
    private final String SUPER_2        = "Test Super Integer";
    private final String SUPER_3        = "Test Super Boolean";
    private final String SUPER_VALUE_1  = "Super String";
    private final int SUPER_VALUE_2     = 233332;
    private final boolean SUPER_VALUE_3 = false;
    private final String PROPERTY_1     = "Test String";
    private final String PROPERTY_2     = "Test Integer";
    private final String PROPERTY_3     = "Test Boolean";
    private final String PROPERTY_VALUE_1   = "String Value";
    private final int PROPERTY_VALUE_2      = 100001;
    private final boolean PROPERTY_VALUE_3  = true;

    Context context;

    public MixpanelProviderTest(Context context, String token) {
        this.context = context;

        //default map stays empty
        superProperties1.put(SUPER_1, SUPER_VALUE_1);

        superProperties2.put(SUPER_1, SUPER_VALUE_1);
        superProperties2.put(SUPER_2, SUPER_VALUE_2);

        superProperties3.put(SUPER_1, SUPER_VALUE_1);
        superProperties3.put(SUPER_2, SUPER_VALUE_2);
        superProperties3.put(SUPER_3, SUPER_VALUE_3);

        properties1.put(PROPERTY_1, PROPERTY_VALUE_1);

        properties2.put(PROPERTY_1, PROPERTY_VALUE_1);
        properties2.put(PROPERTY_2, PROPERTY_VALUE_2);

        properties3.put(PROPERTY_1, PROPERTY_VALUE_1);
        properties2.put(PROPERTY_2, PROPERTY_VALUE_2);
        properties2.put(PROPERTY_3, PROPERTY_VALUE_3);

        mixpanelProviderDefault = new MixpanelBuilder(context, token)
                .build();

        mixpanelProviderUserId = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .build();

        mixpanelProviderSendImmediately = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .build();

        mixpanelProviderEventName = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .build();

        mixpanelProviderSessionName = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .build();

        mixpanelProviderScreenViewName = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .setScreenViewEventName("Test Screen View Name")
                .build();

        mixpanelProviderExceptionName = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .setScreenViewEventName("Test Screen View Name")
                .setExceptionEventName("Test Exception Name")
                .build();

        mixpanelProviderSuper1 = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .setScreenViewEventName("Test Screen View Name")
                .setExceptionEventName("Test Exception Name")
                .registerSuperProperties(superProperties1)
                .build();

        mixpanelProviderSuper2 = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .setScreenViewEventName("Test Screen View Name")
                .setExceptionEventName("Test Exception Name")
                .registerSuperProperties(superProperties2)
                .build();

        mixpanelProviderSuper3 = new MixpanelBuilder(context, token)
                .sendUserId(USER_ID)
                .sendEventsImmediately(true)
                .setDefaultEventName("Test Event Name")
                .setSessionEventName("Test Session Name")
                .setScreenViewEventName("Test Screen View Name")
                .setExceptionEventName("Test Exception Name")
                .registerSuperProperties(superProperties3)
                .build();



        Log.e(TAG, "Objects Set");

        runTests(mixpanelProviderDefault);
        runTests(mixpanelProviderUserId);
        runTests(mixpanelProviderSendImmediately);
        runTests(mixpanelProviderEventName);
        runTests(mixpanelProviderSessionName);
        runTests(mixpanelProviderScreenViewName);
        runTests(mixpanelProviderExceptionName);
        runTests(mixpanelProviderSuper1);
        runTests(mixpanelProviderSuper2);
        runTests(mixpanelProviderSuper3);
    }

    public void runTests(AnalyticsProvider provider) {
        Log.e(TAG, "Running test: " + provider.getClass().getName());

        Analytics.addProvider(provider);

        if(Analytics.getProviderInstance(provider) != provider)
            Log.e(TAG, "Provider does not match current");

        HashMap<String, AnalyticsProvider> providerHashMap = Analytics.getAllProviders();
        for (Map.Entry<String, AnalyticsProvider> entry : providerHashMap.entrySet()) {
            Log.e(TAG, entry.getKey());
        }

        Analytics.sendEvent(EVENT_STRING);
        Analytics.sendEvent(null);

        Analytics.sendEventWithProperties(EVENT_STRING, defaultMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, properties1);
        Analytics.sendEventWithProperties(null, properties1);
        Analytics.sendEventWithProperties(EVENT_STRING, properties2);
        Analytics.sendEventWithProperties(null, properties2);
        Analytics.sendEventWithProperties(EVENT_STRING, properties3);
        Analytics.sendEventWithProperties(null, properties3);

        Analytics.sendScreenViewEvent(SCREEN_STRING);

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

        Log.e(TAG, "Test finished: " + provider.getClass().getName());

        //cause uncaught exception
        //int divideByZero = 1 / 0;

        Toast.makeText(context, "Tests completed", Toast.LENGTH_SHORT).show();
    }
}
