
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		frame
	 *	@date 		Wednesday 10th of May 2023 09:17:31 AM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	

package com.example.musicscreen;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class frame_activity extends Activity {

	
	private View Background_Frame;
	private View song_card1;
	private TextView title;
	private TextView song_count;
	private View musicicon1;
	private TextView song_name1;
	private TextView song_artist_1;
	private View song_card2;
	private View musicicon2;
	private TextView song_name_2;
	private TextView song_artist_2;
	private ImageView back_button;
	private ImageView album_cover1;
	private ImageView album_cover2;
	private ImageView play_button2;
	private ImageView play_button1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame);

		
		Background_Frame = (View) findViewById(R.id._bg__frame_ek2);
		song_card1 = (View) findViewById(R.id.song_card1);
		title = (TextView) findViewById(R.id.title);
		song_count = (TextView) findViewById(R.id.song_count);
		musicicon1 = (View) findViewById(R.id.musicicon1);
		song_name1 = (TextView) findViewById(R.id.song_name1);
		song_artist_1 = (TextView) findViewById(R.id.song_artist_1);
		song_card2 = (View) findViewById(R.id.song_card2);
		musicicon2 = (View) findViewById(R.id.musicicon2);
		song_name_2 = (TextView) findViewById(R.id.song_name_2);
		song_artist_2 = (TextView) findViewById(R.id.song_artist_2);
		back_button = (ImageView) findViewById(R.id.back_button);
		album_cover1 = (ImageView) findViewById(R.id.album_cover1);
		album_cover2 = (ImageView) findViewById(R.id.album_cover2);
		play_button2 = (ImageView) findViewById(R.id.play_button2);
		play_button1 = (ImageView) findViewById(R.id.play_button1);
	
		
		//custom code goes here

	}
}
	
	