package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;

/**
 * Created by nthunem on 1/24/17.
 */

public class FirebaseBuilder extends ProviderBuilder {

    //required
    Context context;
    //optional
    String defaultEventTitle = "default";
    String defaultScreenNameTitle = "screen_view";
    String defaultSessionTitle = "session";
    String defaultExceptionTitle = "exception";
    long minimumSessionDuration = 10 * 1000;

    public FirebaseBuilder(Context context) {
        this.context = context;
    }

    public FirebaseBuilder setMinimumSessionDuration(long milliseconds) {
        this.minimumSessionDuration = milliseconds;
        return this;
    }

    @Override
    public FirebaseProvider build() {
        return new FirebaseProvider(this);
    }
}
