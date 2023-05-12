package com.example.home4u;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

public class homescreen_activity extends Activity {


	private View _bg__homeScreen_ek2;

	private View playMusic;
	private View manageScenes;
	private View alarmSystem;
	private TextView remotely_disable_your_alarm;
	private Button rectangle_41;
	private Button rectangle_61;
	private TextView disable_alarm;
	private TextView create_a_scene_that_controls_your_home4u_device_;
	private TextView connect_your_phone_to_the_device_and_play_music;
	private Button rectangle_31;
	private TextView play_music;
	private TextView manage_scenes;
	private ImageView media_bluetooth_on;
	private ImageView alarm_off;

	private TextView home4u;
	private View songPlayerBottom;
	private View rectangle_110;
	private TextView song_name;
	private TextView artist_name;

	private ImageButton play_arrow;

	@SuppressLint("MissingInflatedId")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);


		_bg__homeScreen_ek2 = findViewById(R.id._bg__homeScreen_ek2);
		play_arrow = findViewById(R.id.play_arrow);
		manageScenes = findViewById(R.id.manageScenes);
		alarmSystem =  findViewById(R.id.alarmSystem);
		remotely_disable_your_alarm = findViewById(R.id.remotely_disable_your_alarm);
		rectangle_41 =  findViewById(R.id.rectangle_41);
		rectangle_61 = findViewById(R.id.rectangle_61);
		create_a_scene_that_controls_your_home4u_device_ = findViewById(R.id.create_a_scene_that_controls_your_home4u_device_);
		connect_your_phone_to_the_device_and_play_music = findViewById(R.id.connect_your_phone_to_the_device_and_play_music);
		rectangle_31 =  findViewById(R.id.rectangle_31);
		play_music = findViewById(R.id.play_music);
		media_bluetooth_on =  findViewById(R.id.media_bluetooth_on);
		alarm_off = findViewById(R.id.alarm_off);
		home4u = findViewById(R.id.home4u);
		songPlayerBottom = findViewById(R.id.songPlayerBottom);
		rectangle_110 = findViewById(R.id.rectangle_110);
		song_name = findViewById(R.id.song_name);
		artist_name = findViewById(R.id.artist_name);
	
		
		//custom code goes here
	
	}
}
	
	