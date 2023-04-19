package com.example.home4u.scenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home4u.R;

public class AddSceneActivity extends AppCompatActivity {

    private EditText sceneNameText;
    private TextView sceneNameTextView;
    private Button saveSceneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scene);

        addSceneView();

        saveSceneButton.setOnClickListener(v -> {
            checker();
        });
    }

    private void checker() {
        String sceneName = sceneNameText.getText().toString();

        if (sceneName.isEmpty()){
            Toast.makeText(this, "Please enter a name for the scene", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Scene created successfully", Toast.LENGTH_SHORT).show();
            //add transition to other activity
        }
    }

    private void addSceneView() {
        sceneNameText = findViewById(R.id.sceneNameText);
        sceneNameTextView = findViewById(R.id.sceneNameTextView);
        saveSceneButton = findViewById(R.id.saveSceneButton);
    }


}