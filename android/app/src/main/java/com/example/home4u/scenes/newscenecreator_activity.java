package com.example.home4u.scenes;

/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		newscenecreator
	 *	@date 		Wednesday 26th of April 2023 10:42:12 AM
	 *	@title 		Add Scene
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	


import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.home4u.R;

public class newscenecreator_activity extends Activity {

	private Switch setSecuritySwitch;
	private Switch playMusicSwitch;
	private Button saveButton;
	private EditText sceneNameTextInput;
	private EditText startTimeText;
	private EditText endTimeText;
	private ImageButton backButtonNewScene;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newscenecreator);

		NewSceneView();

		setSecuritySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if(isChecked){
				//TODO add the function to activate alarm
			}else{
				//TODO disactivate the alarm
			}
		});

		playMusicSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if(isChecked){
				//TODO activate dark mode
			}else{
				//TODO light mode
			}
		});

		saveButton.setOnClickListener(v -> {
			checker();
		});


	}

	private void checker() {
		String sceneName = sceneNameTextInput.getText().toString();

		if (sceneName.isEmpty()){
			Toast.makeText(this, "Please enter a name for the scene", Toast.LENGTH_SHORT).show();
		} else{
			Toast.makeText(this, "Scene created successfully", Toast.LENGTH_SHORT).show();
			//add transition to other activity
		}
		//TODO create a else if to compare if the same name already exsists

}

	private void NewSceneView() {
		setSecuritySwitch = findViewById(R.id.setSecuritySwitch);
		playMusicSwitch = findViewById(R.id.playMusicSwitch);
		saveButton = findViewById(R.id.rectangle_11);
		sceneNameTextInput = findViewById(R.id.sceneNameTextInput);
		startTimeText = findViewById(R.id.startTimeText);
		endTimeText = findViewById(R.id.endTimeText);
		backButtonNewScene = findViewById(R.id.backButtonNewScene);
	}
}
	
	