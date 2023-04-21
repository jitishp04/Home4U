package com.example.home4u.scenes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.home4u.R;

public class AddSceneActivity extends AppCompatActivity {

    private EditText sceneNameText;
    private Button saveSceneButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode") //doesn't send warnings regarding the min SDK not allowing switch
    private Switch alarmSwitch, modeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scene);

        addSceneView();
        alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                //TODO add the function to activate alarm
            }else{
                //TODO disactivate the alarm
            }
        });

        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                //TODO activate dark mode
            }else{
                //TODO light mode
            }
        });

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
        saveSceneButton = findViewById(R.id.saveSceneButton);
        alarmSwitch = findViewById(R.id.alarmSwitch);
        modeSwitch = findViewById(R.id.modeSwitch);
    }


}