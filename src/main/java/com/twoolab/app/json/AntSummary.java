package com.twoolab.app.json;

/**
 * @author yeesheng on 22/02/2018
 * @project antmonitorapp
 */
public class AntSummary {

//    @JsonProperty("elapsed")
    private int elapsedTime;
//    @JsonProperty("hwerrors")
    private int hardwareErrors;
//    @JsonProperty("avgspeed")
    private int averageSpeed;

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getHardwareErrors() {
        return hardwareErrors;
    }

    public void setHardwareErrors(int hardwareErrors) {
        this.hardwareErrors = hardwareErrors;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
