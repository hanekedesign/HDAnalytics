package com.hanekedesign.firebase;

import android.content.Context;

import com.hanekedesign.androidanalytics.*;

/**
 * Created by nthunem on 1/24/17.
 */

public class FirebaseBuilder implements ProviderBuilder {

    //required
    Context context;
    //optional
    String defaultEventTitle = "default";
    String defaultScreenNameTitle = "screen_view";
    String defaultSessionTitle = "session";
    long minimumSessionDuration = 10 * 1000;

    public FirebaseBuilder(Context context) {
        this.context = context;
    }

    /**
     * Sets minimum session duration.
     *
     * Session event will not be sent automatically to Firebase before the specified time
     *
     * @param milliseconds      Number of milliseconds before session event is send
     * @return                  FirebaseBuilder object
     */
    public FirebaseBuilder setMinimumSessionDuration(long milliseconds) {
        this.minimumSessionDuration = milliseconds;
        return this;
    }

    @Override
    public FirebaseProvider build() {
        return new FirebaseProvider(this);
    }
}
