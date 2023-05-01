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
	


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.home4u.R;

import java.util.Locale;

public class newscenecreator_activity extends Activity {

	@SuppressLint("UseSwitchCompatOrMaterialCode") //allows backwards compatibility for switches to work on older android
	private Switch setSecuritySwitch;
	private Button saveButton;
	private EditText sceneNameTextInput;
	private ImageButton backButtonNewScene;
	private Button monButton;
	private Button tueButton;
	private Button wedButton;
	private Button thuButton;
	private Button friButton;
	private Button satButton;
	private Button sunButton;

	private Button startTime;
	private Button endTime;

	private int hourStart, minuteStart;
	private int hourEnd, minuteEnd;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newscenecreator);

		setSecuritySwitch = findViewById(R.id.setSecuritySwitch);
		@SuppressLint("UseSwitchCompatOrMaterialCode") Switch playMusicSwitch = findViewById(R.id.playMusicSwitch);
		saveButton = findViewById(R.id.rectangle_11);
		sceneNameTextInput = findViewById(R.id.sceneNameTextInput);
		backButtonNewScene = findViewById(R.id.backButtonNewScene);

		startTime = findViewById(R.id.startTime);
		endTime = findViewById(R.id.endTime);

		monButton = findViewById(R.id.monButton);
		tueButton = findViewById(R.id.tueButton);
		wedButton = findViewById(R.id.wedButton);
		thuButton = findViewById(R.id.thuButton);
		friButton = findViewById(R.id.friButton);
		satButton = findViewById(R.id.satButton);
		sunButton = findViewById(R.id.sunButton);

		monButton.setOnClickListener(buttonClickListener);
		tueButton.setOnClickListener(buttonClickListener);
		wedButton.setOnClickListener(buttonClickListener);
		thuButton.setOnClickListener(buttonClickListener);
		friButton.setOnClickListener(buttonClickListener);
		satButton.setOnClickListener(buttonClickListener);
		sunButton.setOnClickListener(buttonClickListener);

		setSecuritySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if(isChecked){
				//TODO add the function to activate alarm
			}else{
				//TODO disactivate the alarm
			}
		});

		playMusicSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if(isChecked){
				//TODO play song
			}else{
				//TODO
			}
		});
		backButtonNewScene.setOnClickListener(v -> finish());
		saveButton.setOnClickListener(v -> checker());
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

	//inspired by Chat-gpt: using switch case instead of calling onClickListener multiple times
	View.OnClickListener buttonClickListener = v -> {
		// Do something when any of the buttons is clicked
		switch (v.getId()) {
			case R.id.monButton:

				break;
			case R.id.tueButton:

				break;
			case R.id.wedButton:

				break;
			case R.id.thuButton:

				break;
			case R.id.friButton:

				break;
			case R.id.satButton:

				break;
			case R.id.sunButton:

				break;
		}
	};

//inspired from: https://www.youtube.com/watch?v=c6c1giRekB4
	public void timePickerStart(View view) {
		TimePickerDialog.OnTimeSetListener onTimeSetListenerStart = (view1, selectHourStart, selectMinuteStart) -> {
			hourStart = selectHourStart;
			minuteStart = selectMinuteStart;
			startTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hourStart, minuteStart));
		};
		TimePickerDialog timePickerDialogStart = new TimePickerDialog(this, onTimeSetListenerStart,hourStart,minuteStart,true);
		timePickerDialogStart.setTitle("Select Time");
		timePickerDialogStart.show();
	}

	public void timePickerEnd(View view) {
		TimePickerDialog.OnTimeSetListener onTimeSetListenerEnd = (view1, selectHourEnd, selectMinuteEnd) -> {
			hourEnd = selectHourEnd;
			minuteEnd = selectMinuteEnd;
			endTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hourEnd, minuteEnd));
		};
		TimePickerDialog timePickerDialogEnd = new TimePickerDialog(this, onTimeSetListenerEnd,hourEnd,minuteEnd,true);
		timePickerDialogEnd.setTitle("Select Time");
		timePickerDialogEnd.show();
	}
}
	
	