##HDAnalytics

HDAnalytics is an Android Analytics tool similar to ARAnalytics for iOS or Analytics.js for javascript.

HDAnalytics is an analytics abstraction for analytics integration for tracking user data and events.  It greatly reduces the work required to use an analytics provider.  HDAnalytics currently supports: Google Analytics, Mixpanel, and Firebase.

####Integration:

First, add the dependency to your project's build.gradle file.
```java
compile 'com.hanekedesign.androidanalytics:hdanalytics:1.0@aar'
```

####Usage:

Next, declare the analytics provider and add it to the Analytics object.  For example, if using Mixpanel Anayltics:
```java
MixpanelProvider mixpanelProvider;
Analytics.addProvider(mixpanelProvider);
```
When sending an event, use the public static Analytics class.  The Analytics class lets you add more than one provider to use at a time, and will call each analytics provider's API accordingly.  
```java
Analytics.sendEvent("Sample Event");
```

####Event Tracking:
```java
// Send event event to provider
void sendEvent(String event);

// Send an event with additional properties and event name
void sendEventWithProperties(String event, HashMap<String, ?> eventMap);

// Send session event
void sendSessionEvent();
```

####Exception Tracking:
```java
// Send a caught exception
void sendCaughtException(Exception e);

// Send a caught exception
void sendCaughtException(Exception e, boolean isFatal);
```
####User Data:
```java
// Send user data
void sendUserId(String userId);

// Updates user profile
void updateUserProfile(String key, Object value);
```
####Screen Tracking:
```java
// Send screen view event
void sendScreenViewEvent(String screenName);
```

####Google Analytics & Firebase:
Google Analytics & Firebase automatically logs certain events such as session, screen views (Google Anayltics), and user data.  To expand on these, you can send any of the events with custom data to provide additional information.
