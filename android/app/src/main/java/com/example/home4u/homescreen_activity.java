package com.example.home4u;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;


public class homescreen_activity extends Activity {



	private Button manageScenesButton;
	private Button enableAlarmButton;
	private Button playMusicButton;

	private Switch securitySwitch;


	private ImageButton play_arrow;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);



		play_arrow = findViewById(R.id.play_arrow);
		manageScenesButton =  findViewById(R.id.manageScenesButton);
		enableAlarmButton = findViewById(R.id.enableAlarmButton);
		playMusicButton =  findViewById(R.id.playMusicButton);
		securitySwitch = findViewById(R.id.securitySwitch);
		
		//custom code goes here
	
	}
}
	
	