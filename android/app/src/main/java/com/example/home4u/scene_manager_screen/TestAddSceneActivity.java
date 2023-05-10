package com.example.home4u.scene_manager_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.home4u.R;

public class TestAddSceneActivity extends AppCompatActivity {
    private Button saveBtn;
    private EditText sceneNameText;
    private String sceneName;
    private String oldSceneName;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add_scene);

        saveBtn = findViewById(R.id.btnSceneName);
        sceneNameText = findViewById(R.id.sceneNameText);

        Intent intent = getIntent();
        oldSceneName = intent.getStringExtra("name");
        pos = intent.getIntExtra("pos", -1);
        if (oldSceneName != null) {
            sceneNameText.setText(oldSceneName);
        }

        saveBtn.setOnClickListener(view -> sendData());
    }

    public void sendData() {
        sceneName = sceneNameText.getText().toString().trim();

        Intent intent = new Intent();
        intent.putExtra(SceneManagerScreenActivity.SCENENAME, sceneName);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}