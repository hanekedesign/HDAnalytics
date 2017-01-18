package com.hanekedesign.analyticsandroid.ProviderObjects;

/**
 * Created by nthunem on 1/18/17.
 */

public class ExceptionObject {

    private String description;
    private boolean isFatal;

    public ExceptionObject() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFatal() {
        return isFatal;
    }

    public void setFatal(boolean fatal) {
        isFatal = fatal;
    }
}
