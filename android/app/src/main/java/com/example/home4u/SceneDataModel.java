package com.example.home4u;

import androidx.annotation.NonNull;

import java.sql.Time;

public class SceneDataModel {
    private String sceneName;
    private String startTime;
    private String endTime;
    private Boolean setSecurity;
    private Boolean playMusic;

    public SceneDataModel(String sceneName, String startTime, String endTime, Boolean setSecurity, Boolean playMusic) {
        this.sceneName = sceneName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.setSecurity = setSecurity;
        this.playMusic = playMusic;
    }

    public SceneDataModel() {}

    //for testing
    @NonNull
    @Override
    public String toString() {
        return "SceneDataModel{" +
                "sceneName='" + sceneName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", setSecurity=" + setSecurity +
                ", playMusic=" + playMusic +
                '}';
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getSetSecurity() {
        return setSecurity;
    }

    public void setSetSecurity(Boolean setSecurity) {
        this.setSecurity = setSecurity;
    }

    public Boolean getPlayMusic() {
        return playMusic;
    }

    public void setPlayMusic(Boolean playMusic) {
        this.playMusic = playMusic;
    }
}
