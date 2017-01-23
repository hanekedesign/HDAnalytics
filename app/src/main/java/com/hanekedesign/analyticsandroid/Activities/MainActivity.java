package com.hanekedesign.analyticsandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.hanekedesign.analyticsandroid.ProviderTests.GoogleAnalyticsProviderTest;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.analyticsandroid.ProviderTests.GoogleAnalyticsProviderTest;
import com.hanekedesign.analyticsandroid.R;

public class MainActivity extends AppCompatActivity {

    String providerId;
    Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        providerId = "UA-90565556-1";

        new GoogleAnalyticsProviderTest(this, providerId);
    }
}
