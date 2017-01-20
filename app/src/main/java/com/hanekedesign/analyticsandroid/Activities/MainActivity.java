package com.hanekedesign.analyticsandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.hanekedesign.analyticsandroid.ProviderTests.GoogleAnalyticsProviderTest;
import com.hanekedesign.analyticsandroid.ProviderTests.GoogleAnalyticsProviderTest;
import com.hanekedesign.analyticsandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GoogleAnalyticsProviderTest(this);
    }
}
