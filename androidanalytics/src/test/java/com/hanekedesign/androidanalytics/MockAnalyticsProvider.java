package com.hanekedesign.androidanalytics;

import java.util.HashMap;

public class MockAnalyticsProvider implements AnalyticsProvider {

    public static class Builder implements ProviderBuilder{

        public MockAnalyticsProvider mockAnalyticsProvider;

        public Builder(MockAnalyticsProvider mockAnalyticsProvider) {
            this.mockAnalyticsProvider = mockAnalyticsProvider;
        }

        @Override
        public AnalyticsProvider build() {
            return mockAnalyticsProvider;
        }
    }

    @Override
    public void sendUserId(String userId) {

    }

    @Override
    public void sendEvent(String event) {

    }

    @Override
    public void sendEventWithProperties(String event, HashMap<String, ?> eventMap) {

    }

    @Override
    public void sendScreenViewEvent(String screenName) {

    }

    @Override
    public void sendSessionEvent() {

    }

    @Override
    public void sendCaughtException(Exception e) {

    }

    @Override
    public void sendCaughtException(Exception e, boolean isFatal) {

    }

    @Override
    public void updateUserProfile(String key, Object value) {

    }
}
