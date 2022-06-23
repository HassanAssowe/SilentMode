package com.hassanassowe.silentmode;

import java.util.Date;

//This class represents a  "Silent Mode" instance. It contains properties an instance would have in order for the app to act on.
public class SilentMode {
    private String name; //The name of the silent mode instance.
    private Date startDate; //The startDate of the instance.
    private Long startTime; //The startTime of the instance.
    private Date endDate; //The endDate of the instance.
    private Long endTime; //The endTime of the instance.
    private Long duration; //How long the instance will last (OPTIONAL)
    Boolean vibrateMode; //If vibrate instead of silence is used. (OPTIONAL)

    public SilentMode(String name, Date startDate, Long startTime, Long duration) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
    }

    public SilentMode(String name, Date startDate, Long startTime, Date endDate, Long endTime, Boolean vibrateMode) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.vibrateMode = vibrateMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getVibrateMode() {
        return vibrateMode;
    }

    public void setVibrateMode(Boolean vibrateMode) {
        this.vibrateMode = vibrateMode;
    }
}
