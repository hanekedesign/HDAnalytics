package com.hanekedesign.androidanalytics.Providers;

import android.content.Context;

/**
 * Created by nthunem on 1/24/17.
 */

public class FirebaseBuilder extends ProviderBuilder {

    //required
    Context context;
    String providerId;
    //optional

    public FirebaseBuilder(Context context, String providerId) {

    }

    @Override
    public FirebaseProvider build() {
        return new FirebaseProvider(this);
    }
}
