package com.example.home4u.scene_manager_screen;

public class SceneData {
    private String sceneName;
    private Integer sceneIcon;

    public SceneData(String sceneName, Integer sceneIcon) {
        this.sceneName = sceneName;
        this.sceneIcon = sceneIcon;
    }

    public Integer getSceneIcon() {
        return sceneIcon;
    }

    public void setSceneIcon(Integer sceneIcon) {
        this.sceneIcon = sceneIcon;
    }

    public SceneData(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}