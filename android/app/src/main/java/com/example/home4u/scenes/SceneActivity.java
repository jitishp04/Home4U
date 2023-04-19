package com.example.home4u.scenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.home4u.R;

public class SceneActivity extends AppCompatActivity {
    private Button addSceneButton;
    private Button manageSceneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        sceneView();

        addSceneButton.setOnClickListener(v -> {
            Intent addSceneIntent = new Intent(SceneActivity.this, AddSceneActivity.class);
            startActivity(addSceneIntent);
        });

        manageSceneButton.setOnClickListener(v -> {
            Intent manageSceneIntent = new Intent(SceneActivity.this, ManageScenesActivity.class);
            startActivity(manageSceneIntent);
        });

    }

    private void sceneView() {
        addSceneButton = findViewById(R.id.addSceneButton);
        manageSceneButton = findViewById(R.id.manageScenesButton);
    }
}