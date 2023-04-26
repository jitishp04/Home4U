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

import com.example.home4u.R;

public class newscenecreator_activity extends Activity {


	private TextView new_scene;
	private TextView set_scene_duration;
	private View rectangle_5;
	private View rectangle_9;
	private View rectangle_10;
	private TextView play_music;
	private ImageView rectangle_7;
	private TextView __;
	private View rectangle_6;
	private TextView ___ek1;
	private TextView start;
	private TextView end;
	private View rectangle_8;
	private TextView scene_name;
	private ImageView border_color;
	private TextView set_security;
	private Button monButton;
	private Button wedButton;
	private Button thuButton;
	private Button tueButton;
	private Button satButton;
	private Button sunButton;
	private Button friButton;
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

		new_scene = (TextView) findViewById(R.id.new_scene);
		set_scene_duration = (TextView) findViewById(R.id.set_scene_duration);
		rectangle_5 = (View) findViewById(R.id.rectangle_5);
		rectangle_9 = (View) findViewById(R.id.rectangle_9);
		rectangle_10 = (View) findViewById(R.id.rectangle_10);
		play_music = (TextView) findViewById(R.id.play_music);
		rectangle_7 = (ImageView) findViewById(R.id.rectangle_7);
		__ = (TextView) findViewById(R.id.__);
		rectangle_6 = (View) findViewById(R.id.rectangle_6);
		___ek1 = (TextView) findViewById(R.id.___ek1);
		start = (TextView) findViewById(R.id.start);
		end = (TextView) findViewById(R.id.end);
		rectangle_8 = (View) findViewById(R.id.rectangle_8);
		border_color = (ImageView) findViewById(R.id.border_color);
		set_security = (TextView) findViewById(R.id.set_security);

		monButton = findViewById(R.id.monButton);
		wedButton = findViewById(R.id.wedButton);
		thuButton = findViewById(R.id.thuButton);
		tueButton = findViewById(R.id.tueButton);
		satButton = findViewById(R.id.satButton);
		sunButton = findViewById(R.id.sunButton);
		friButton = findViewById(R.id.friButton);

		setSecuritySwitch = findViewById(R.id.setSecuritySwitch);
		playMusicSwitch = findViewById(R.id.playMusicSwitch);
		saveButton = findViewById(R.id.rectangle_11);
		sceneNameTextInput = findViewById(R.id.sceneNameTextInput);
		startTimeText = findViewById(R.id.startTimeText);
		endTimeText = findViewById(R.id.endTimeText);
		backButtonNewScene = findViewById(R.id.backButtonNewScene);

		//custom code goes here
	
	}
}
	
	