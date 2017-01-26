package com.hanekedesign.androidanalytics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AnalyticsTests {

    @Test
    public void analyticsSanitationTest(){

        MockAnalyticsProvider providerInstance = new MockAnalyticsProvider(){

        };
        Analytics.addProvider(new MockAnalyticsProvider.Builder(providerInstance).build());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("invalid_prop", new ArrayList<>()); // should not be able to put in non primitive objects

        try {
            Analytics.sendEventWithProperties("test_event", properties);
            assertTrue(false); // should not get here
        }catch (Exception e){

        }

        properties = new HashMap<>();
        properties.put("string", "test string");
        properties.put("int", 2);
        properties.put("double", 3.14d);
        properties.put("float", .99f);
        properties.put("bool", true);

        try {
            Analytics.sendEventWithProperties("test_event", properties);
            assertTrue(true);
        }catch (Exception e){
            assertTrue(false); // should not get here
        }

//        Analytics.removeProvider(providerInstance);
    }
}
