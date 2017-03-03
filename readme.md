##HDAnalytics

HDAnalytics is an Android Analytics tool similar to ARAnalytics for iOS or Analytics.js for javascript.

HDAnalytics is an analytics abstraction for analytics integration for tracking user data and events.  It greatly reduces the work required to use an analytics provider.  HDAnalytics currently supports: Google Analytics, Mixpanel, and Firebase.

####Integration:

First, add the dependency to your project's build.gradle file.  You also need to add the latest Mixpanel dependency.
```java
compile 'AnalyticsAndroid:hdanalytics:0.2:release@aar'
compile 'AnalyticsAndroid:mixpanel:0.2:release@aar'
compile "com.mixpanel.android:mixpanel-android:4.+"
```

####Usage:

Next, declare the analytics provider and add it to the Analytics object.  For example, if using Mixpanel Analytics:
```java
MixpanelProvider mixpanelProvider;
mixpanelProvider = new MixpanelBuilder(context, token).build();
Analytics.addProvider(mixpanelProvider);
```
When sending an event, use the public static Analytics class.  The Analytics class lets you add more than one provider to use at a time, and will call each analytics provider's API accordingly.  
```java
Analytics.sendEvent("Sample Event");
```

####Google Analytics & Firebase:
Google Analytics & Firebase automatically logs certain events such as session, screen views (Google Anayltics), and user data.  To expand on these, you can send any of the events with custom data to provide additional information.  
<br />
<br />
<br />
<br />

    Copyright 2017 Haneke Design

    Permission is hereby granted, free of charge, to any person obtaining a copy of this 
    software and associated documentation files (the "Software"), to deal in the Software 
    without restriction, including without limitation the rights to use, copy, modify, 
    merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
    persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or 
    substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
    ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
