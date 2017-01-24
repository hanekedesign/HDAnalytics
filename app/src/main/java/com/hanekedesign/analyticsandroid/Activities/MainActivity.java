package com.hanekedesign.analyticsandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hanekedesign.analyticsandroid.ProviderTests.GoogleAnalyticsProviderTest;
import com.hanekedesign.analyticsandroid.ProviderTests.MixpanelProviderTest;
import com.hanekedesign.analyticsandroid.R;

public class MainActivity extends AppCompatActivity {

    String googleProviderId;
    String mixpanelToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleProviderId = "UA-90565556-1";
        mixpanelToken = "ade5113503e859183a793570b623234b";

        new GoogleAnalyticsProviderTest(this, googleProviderId);
        //new MixpanelProviderTest(this, mixpanelToken);
    }
}
