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
    private final String EVENT_STRING   = "test_event_string";
    private final String SCREEN_STRING  = "test_screen_string";
    private final String USER_ID        = "test_000001";
    private final String PROFILE_1      = "profile_1";
    private final String PROFILE_1_VALUE    = "profile_1_value";
    private final String PROFILE_2          = "profile_2";
    private final String PROFILE_2_VALUE    = "profile_2_value";
    private final String PROFILE_3          = "profile_3";
    private final String PROFILE_3_VALUE    = "profile_3_value";
    private final String PROFILE_2_NEW      = "profile_2";
    private final String PROFILE_2_NEW_VALUE = "profile_2_value";
    private final String STRING_KEY     = "string_key";
    private final String STRING_VALUE   = "string_value";
    private final String INT_KEY        = "int_key";
    private final String INT_VALUE      = "int_value";
    private final String BOOL_KEY       = "bool_key";
    private final String BOOL_VALUE     = "bool_value";
    private final String FLOAT_KEY      = "float_key";
    private final String FLOAT_VALUE    = "float_value";
    private final String DOUBLE_KEY     = "double_key";
    private final String DOUBLE_VALUE   = "double_value";
    private final String CHAR_KEY       = "char_key";
    private final String CHAR_VALUE     = "char_value";
    private final String CHARSEQ_KEY    = "charSeq_key";
    private final String CHARSEQ_VALUE  = "charSeq_value";

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


        firebaseProviderSessionTime = new FirebaseBuilder(context)
                .setMinimumSessionDuration(SESSION_TIME)
                .build();

        Log.e(TAG, "Objects Set");

        runTests(firebaseProviderDefault);
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

        Analytics.sendUserId(USER_ID);

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

        Analytics.updateUserProfile(PROFILE_2_NEW, PROFILE_2_NEW_VALUE);
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
        int divideByZero = 1 / 0;

        Toast.makeText(context, "Tests completed", Toast.LENGTH_SHORT).show();
    }

}
