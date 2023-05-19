
	 
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

	public class frame_activity extends Activity {


		private View Background_Frame;
		private View song_card1;
		private TextView title;
		private TextView song_count;
		private View musicicon1;
		private TextView song_name1;
		private TextView song_artist1;
		private View song_card2;
		private View musicicon2;
		private TextView song_name2;
		private TextView song_artist2;
		private ImageButton back_button;
		private ImageView album_cover1;
		private ImageView album_cover2;
		private ImageButton play_button2;
		private ImageButton play_button1;

		private ImageButton universal_play;





		private ImageButton pause_button;

		@SuppressLint("MissingInflatedId")
		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.frame);


			Background_Frame = (View) findViewById(R.id.stage2);
			song_card1 = (View) findViewById(R.id.song_card1);
			title = (TextView) findViewById(R.id.title);
			song_count = (TextView) findViewById(R.id.song_count);
			musicicon1 = (View) findViewById(R.id.music_icon1);
			song_name1 = (TextView) findViewById(R.id.song_name1);
			song_artist1 = (TextView) findViewById(R.id.song_artist1);
			song_card2 = (View) findViewById(R.id.song_card2);
			musicicon2 = (View) findViewById(R.id.music_icon2);
			song_name2 = (TextView) findViewById(R.id.song_name2);
			song_artist2 = (TextView) findViewById(R.id.song_artist_2);
			back_button = (ImageButton) findViewById(R.id.back_button);
			album_cover1 = (ImageView) findViewById(R.id.album_cover1);
			album_cover2 = (ImageView) findViewById(R.id.album_cover2);
			play_button2 = (ImageButton) findViewById(R.id.play_button2);
			play_button1 = (ImageButton) findViewById(R.id.play_button1);
			pause_button = (ImageButton) findViewById(R.id.pause_button);
			universal_play = (ImageButton) findViewById(R.id.universal_play);




			//custom code goes here
			song_name1.setText("Twinkle Twinkle"); //first song card
			song_artist1.setText("Jane Taylor");

			song_name2.setText("Music Scale"); // second song card
			song_artist2.setText("-");

		}


	}
	
	