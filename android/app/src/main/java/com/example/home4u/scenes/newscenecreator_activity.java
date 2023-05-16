package com.example.home4u.scenes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.home4u.DatabaseHelper;
import com.example.home4u.R;
import com.example.home4u.SceneDataModel;
import com.example.home4u.scene_manager_screen.SceneManagerScreenActivity;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.Locale;

public class newscenecreator_activity extends Activity {

	@SuppressLint("UseSwitchCompatOrMaterialCode") //allows backwards compatibility for switches to work on older android
	private Switch setSecuritySwitch, playMusicSwitch;
	private Button saveButton, startTime, endTime;
	private ImageButton backButtonNewScene;
	private EditText sceneNameTextInput;

	private MaterialCardView selectCard;
	private TextView selectDays;
	private boolean [] selectedDays;
	private ArrayList<Integer> daysList = new ArrayList<>();
	private String [] daysArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private int hourStart, minuteStart, hourEnd, minuteEnd;
	private DatabaseHelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newscenecreator);

		setSecuritySwitch = findViewById(R.id.setSecuritySwitch);
		playMusicSwitch = findViewById(R.id.playMusicSwitch);
		@SuppressLint("UseSwitchCompatOrMaterialCode") Switch playMusicSwitch = findViewById(R.id.playMusicSwitch);
		saveButton = findViewById(R.id.rectangle_11);
		sceneNameTextInput = findViewById(R.id.sceneNameTextInput);
		backButtonNewScene = findViewById(R.id.backButtonNewScene);
		startTime = findViewById(R.id.startTime);
		endTime = findViewById(R.id.endTime);
		selectCard = findViewById(R.id.selectCard);
		selectedDays = new boolean[daysArray.length];
		selectDays = findViewById(R.id.selectDays);

		dbHelper = new DatabaseHelper(getApplicationContext());


		selectCard.setOnClickListener(v -> {
			showDaysDialog();
		});

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


		//Check whether it is from a existing/user-selected scene,
		//by verifying the scene name is not empty;
		//If yes, fill back the add-scene screen with all relevant data for the scene;
		//Otherwise, start with an empty/plain add-scene screen.
		Intent intent = getIntent();
		String curSceneName = intent.getStringExtra("name");
		if (curSceneName != null) {
			SceneDataModel curSceneData = dbHelper.retrieveDataOfAScene(curSceneName);

			sceneNameTextInput.setText(curSceneData.getSceneName());
			startTime.setText(curSceneData.getStartTime());
			endTime.setText(curSceneData.getEndTime());
			setSecuritySwitch.setChecked(curSceneData.getSetSecurity());
			playMusicSwitch.setChecked(curSceneData.getPlayMusic());
			selectDays.setText(curSceneData.getDaysOfTheWeek());
			backButtonNewScene.setOnClickListener(v -> finish());
			saveButton.setOnClickListener(v -> saveUpdates(curSceneData));
		} else {
			backButtonNewScene.setOnClickListener(v -> finish());
			saveButton.setOnClickListener(v -> checker());
		}
	}

	//MultiSelect pop-up to allow picking of days for the scene
	//https://www.youtube.com/watch?v=4GdbCl-47wE
	private void showDaysDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(newscenecreator_activity.this); //used for pop-up over the activity
		builder.setTitle("Select days");
		builder.setCancelable(false);
		builder.setMultiChoiceItems(daysArray, selectedDays, (dialog, which, isChecked) -> {
			if(isChecked){
				daysList.add(which);
			}else{
				daysList.remove(which);
			}
		}).setPositiveButton("ok", (dialog, which) -> { //Confirm button
			StringBuilder stringBuilder = new StringBuilder();

			for(int i = 0; i< daysList.size(); i++){

				stringBuilder.append(daysArray[daysList.get(i)]);

				if(i != daysList.size() -1){
					stringBuilder.append(", "); //used .append to create into a list form for the selectDays TextView
				}
				selectDays.setText(stringBuilder.toString());
			}
		}).setNegativeButton("cancel", (dialog, which) -> dialog.dismiss()); //cancel button
		builder.show();
	}

	/**
	 * Update and save data of an existing scene.
	 * @param curSceneData
	 */
	private void saveUpdates(SceneDataModel curSceneData) {
		//If scene name got changed, auto update back to the initial scene name for the user and display a message
		if (!curSceneData.getSceneName().equals(sceneNameTextInput.getText().toString())) {
			sceneNameTextInput.setText(curSceneData.getSceneName());
			Toast.makeText(this, "Sorry, you cannot change the scene name", Toast.LENGTH_SHORT).show();
		} else {
			if (!verifyNoChanges(curSceneData)) {
				SceneDataModel sceneDataModel = new SceneDataModel(sceneNameTextInput.getText().toString(),
						startTime.getText().toString(),
						endTime.getText().toString(),
						setSecuritySwitch.isChecked(), playMusicSwitch.isChecked(), selectDays.getText().toString());
				dbHelper.updateScene(sceneDataModel);
				Toast.makeText(getApplicationContext(), curSceneData.getSceneName() + "has been updated successfully", Toast.LENGTH_SHORT).show();
			}
			dbHelper.close();
			startActivity(new Intent(newscenecreator_activity.this, SceneManagerScreenActivity.class));
		}
	}

	/**
	 * Check any changes are made to the current existing scene.
	 * @param curSceneData
	 * @return
	 */
	private boolean verifyNoChanges(SceneDataModel curSceneData) {
		boolean startTimeNoChange =  curSceneData.getStartTime() == startTime.getText().toString();
		boolean endTimeNoChange = curSceneData.getEndTime() == endTime.getText().toString();
		boolean SecurityNoChange = curSceneData.getSetSecurity() == setSecuritySwitch.isChecked();
		boolean MusicNoChange = curSceneData.getPlayMusic() == playMusicSwitch.isChecked();
		boolean daysSelectedNoChange = curSceneData.getDaysOfTheWeek().equals(selectDays.getText().toString());
		return startTimeNoChange && endTimeNoChange && SecurityNoChange && MusicNoChange && daysSelectedNoChange;
	}

	//Check to create a valid scene and its connection with the database
	private void checker() {
		SceneDataModel sceneDataModel;
		String sceneName = sceneNameTextInput.getText().toString();
		if (sceneName.isEmpty()){
			Toast.makeText(this, "Please enter a name for the scene", Toast.LENGTH_SHORT).show();
		} else{
			 sceneDataModel = new SceneDataModel(sceneNameTextInput.getText().toString(),
					startTime.getText().toString(),
					endTime.getText().toString(),
					setSecuritySwitch.isChecked(), playMusicSwitch.isChecked(), selectDays.getText().toString());

			//DatabaseHelper databaseHelper = new DatabaseHelper(newscenecreator_activity.this);

			if (dbHelper.addOne(sceneDataModel)) {
				Toast.makeText(newscenecreator_activity.this, sceneName + "has been added successfully", Toast.LENGTH_SHORT).show();
			}

			dbHelper.close();
			//add transition to other activity
			startActivity(new Intent(newscenecreator_activity.this, SceneManagerScreenActivity.class));
		}
	}

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
	
	