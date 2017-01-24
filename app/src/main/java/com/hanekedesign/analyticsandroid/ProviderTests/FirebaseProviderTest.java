package com.hanekedesign.analyticsandroid.ProviderTests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hanekedesign.androidanalytics.Analytics;
import com.hanekedesign.androidanalytics.AnalyticsProvider;
import com.hanekedesign.androidanalytics.Providers.FirebaseBuilder;
import com.hanekedesign.androidanalytics.Providers.FirebaseProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nthunem on 1/24/17.
 */

public class FirebaseProviderTest {

    FirebaseProvider firebaseProviderDefault;
    FirebaseProvider firebaseProviderEventName;
    FirebaseProvider firebaseProviderScreenName;
    FirebaseProvider firebaseProviderSessionName;
    FirebaseProvider firebaseProviderExceptionName;
    FirebaseProvider firebaseProviderSessionTime;

    HashMap defaultMap      = new HashMap();
    HashMap stringMap       = new HashMap();
    HashMap integerMap      = new HashMap();
    HashMap booleanMap      = new HashMap();
    HashMap floatMap        = new HashMap();
    HashMap doubleMap       = new HashMap();
    HashMap charMap         = new HashMap();
    HashMap charSequenceMap = new HashMap();
    HashMap properties_1    = new HashMap();
    HashMap properties_2    = new HashMap();
    HashMap properties_3    = new HashMap();

    private final String TAG            = "Firebase Test";
    private final long SESSION_TIME     = 1 * 1000;
    private final String EVENT_STRING   = "Test Event String";
    private final String SCREEN_STRING  = "Test Screen String";
    private final String USER_ID        = "test_000001";
    private final String PROFILE_1      = "Profile 1";
    private final String PROFILE_1_VALUE    = "Profile 1 Value";
    private final String PROFILE_2          = "Profile 2";
    private final String PROFILE_2_VALUE    = "Profile 2 Value";
    private final String PROFILE_3          = "Profile 3";
    private final String PROFILE_3_VALUE    = "Profile 3 Value";
    private final String PROFILE_2_NEW      = "Profile 2";
    private final String PROFILE_2_NEW_VALUE = "Profile 2 Value";
    private final String STRING_KEY     = "String Key";
    private final String STRING_VALUE   = "String Value";
    private final String INT_KEY        = "Int Key";
    private final String INT_VALUE      = "Int Value";
    private final String BOOL_KEY       = "Bool Key";
    private final String BOOL_VALUE     = "Bool Value";
    private final String FLOAT_KEY      = "Float Key";
    private final String FLOAT_VALUE    = "Float Value";
    private final String DOUBLE_KEY     = "Double Key";
    private final String DOUBLE_VALUE   = "Double Value";
    private final String CHAR_KEY       = "Char Key";
    private final String CHAR_VALUE     = "Char Value";
    private final String CHARSEQ_KEY    = "CharSeq Key";
    private final String CHARSEQ_VALUE  = "CharSeq Value";
    private final String EVENT_NAME     = "Test Event Name";
    private final String SCREEN_NAME    = "Test Screen Name";
    private final String SESSION_NAME   = "Test Session Name";
    private final String EXCEPTION_NAME = "Test Exception Name";

    Context context;

    public FirebaseProviderTest(Context context) {
        this.context = context;

        //defaultMap stays empty
        stringMap.put(STRING_KEY, STRING_VALUE);
        integerMap.put(INT_KEY, INT_VALUE);
        booleanMap.put(BOOL_KEY, BOOL_VALUE);
        floatMap.put(FLOAT_KEY, FLOAT_VALUE);
        doubleMap.put(DOUBLE_KEY, DOUBLE_VALUE);
        charMap.put(CHAR_KEY, CHAR_VALUE);
        charSequenceMap.put(CHARSEQ_KEY, CHARSEQ_VALUE);

        properties_1.put(PROFILE_1, PROFILE_1_VALUE);

        properties_2.put(PROFILE_1, PROFILE_1_VALUE);
        properties_2.put(PROFILE_2, PROFILE_2_VALUE);

        properties_3.put(PROFILE_1, PROFILE_1_VALUE);
        properties_3.put(PROFILE_2, PROFILE_2_VALUE);
        properties_3.put(PROFILE_3, PROFILE_3_VALUE);

        firebaseProviderDefault = new FirebaseBuilder(context)
                .build();

        firebaseProviderEventName = new FirebaseBuilder(context)
                .setDefaultEventTitle(EVENT_NAME)
                .build();

        firebaseProviderScreenName = new FirebaseBuilder(context)
                .setDefaultEventTitle(EVENT_NAME)
                .setDefaultScreenNameTitle(SCREEN_NAME)
                .build();

        firebaseProviderSessionName = new FirebaseBuilder(context)
                .setDefaultEventTitle(EVENT_NAME)
                .setDefaultScreenNameTitle(SCREEN_NAME)
                .setDefaultSessionTitle(SESSION_NAME)
                .build();

        firebaseProviderExceptionName = new FirebaseBuilder(context)
                .setDefaultEventTitle(EVENT_NAME)
                .setDefaultScreenNameTitle(SCREEN_NAME)
                .setDefaultSessionTitle(SESSION_NAME)
                .setDefaultExceptionTitle(EXCEPTION_NAME)
                .build();

        firebaseProviderSessionTime = new FirebaseBuilder(context)
                .setDefaultEventTitle(EVENT_NAME)
                .setDefaultScreenNameTitle(SCREEN_NAME)
                .setDefaultSessionTitle(SESSION_NAME)
                .setDefaultExceptionTitle(EXCEPTION_NAME)
                .setMinimumSessionDuration(SESSION_TIME)
                .build();

        Log.e(TAG, "Objects Set");

        runTests(firebaseProviderDefault);
        runTests(firebaseProviderEventName);
        runTests(firebaseProviderScreenName);
        runTests(firebaseProviderSessionName);
        runTests(firebaseProviderExceptionName);
        runTests(firebaseProviderSessionTime);
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
        Analytics.sendEventWithProperties(EVENT_STRING, stringMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, integerMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, booleanMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, floatMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, doubleMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, charMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, charSequenceMap);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, properties_1);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, properties_2);
        Analytics.sendEventWithProperties(null, defaultMap);
        Analytics.sendEventWithProperties(EVENT_STRING, properties_3);
        Analytics.sendEventWithProperties(null, defaultMap);

        Analytics.updateUserProfile(PROFILE_2, PROFILE_2_NEW);
        Analytics.sendEventWithProperties(EVENT_STRING, properties_3);

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
