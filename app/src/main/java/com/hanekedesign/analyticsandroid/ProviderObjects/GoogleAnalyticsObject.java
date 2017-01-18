package com.hanekedesign.analyticsandroid.ProviderObjects;

/**
 * Created by nthunem on 1/18/17.
 */

public class GoogleAnalyticsObject {

    // Event
    private String eventCategory;
    private String eventAction;
    private String eventLabel;
    private long eventValue;

    // Social Events
    private String socialNetwork;
    private String socialAction;
    private String socialTarget;

    // User timings
    private String timingCategory;
    private long timingValue;
    private String timingName;
    private String timingLabel;

    public GoogleAnalyticsObject() {}

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventAction() {
        return eventAction;
    }

    public void setEventAction(String eventAction) {
        this.eventAction = eventAction;
    }

    public String getEventLabel() {
        return eventLabel;
    }

    public void setEventLabel(String eventLabel) {
        this.eventLabel = eventLabel;
    }

    public long getEventValue() {
        return eventValue;
    }

    public void setEventValue(long eventValue) {
        this.eventValue = eventValue;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getSocialAction() {
        return socialAction;
    }

    public void setSocialAction(String socialAction) {
        this.socialAction = socialAction;
    }

    public String getSocialTarget() {
        return socialTarget;
    }

    public void setSocialTarget(String socialTarget) {
        this.socialTarget = socialTarget;
    }

    public String getTimingCategory() {
        return timingCategory;
    }

    public void setTimingCategory(String timingCategory) {
        this.timingCategory = timingCategory;
    }

    public long getTimingValue() {
        return timingValue;
    }

    public void setTimingValue(long timingValue) {
        this.timingValue = timingValue;
    }

    public String getTimingName() {
        return timingName;
    }

    public void setTimingName(String timingName) {
        this.timingName = timingName;
    }

    public String getTimingLabel() {
        return timingLabel;
    }

    public void setTimingLabel(String timingLabel) {
        this.timingLabel = timingLabel;
    }
}
