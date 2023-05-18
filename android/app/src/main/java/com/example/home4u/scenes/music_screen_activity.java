
	 
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
	

package com.example.home4u.scenes;

    import android.annotation.SuppressLint;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;
    import java.util.List;

    import com.example.home4u.R;
    import com.example.home4u.connectivity.BrokerConnection;
    import com.example.home4u.music.MusicInfo;
    import com.example.home4u.music.MusicInfoDownloaderCallback;
    import com.example.home4u.music.MusicPlayer;
    import com.example.home4u.music.SongInfo;
    import com.example.home4u.music.SongsListAdapter;

    public class music_screen_activity extends Activity {

        MusicPlayer musicPlayer;
        private final MusicInfo musicInfo = new MusicInfo();


        public static int lastPlayed = 1;
        private View        Background_Frame;
        private TextView    title;
        private TextView    song_count;
        private ImageButton back_button;
        private ImageButton universal_play;
        private ImageButton pause_button;
        private ImageButton skip_next;
        private ImageButton skip_previous;

        private ListView songListView;

        
        private BrokerConnection brokerConnection; // Declare the brokerConnection
        int QOS = 0;
        public static final String PUB_TOPIC = "MusicPlayer";

        public music_screen_activity() {
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.music_scene_screen);


            musicPlayer = new MusicPlayer(musicInfo,this);
            handleSongs();

            Background_Frame = (View)          findViewById(R.id.stage2);
            title            = (TextView)      findViewById(R.id.title);
            song_count       = (TextView)      findViewById(R.id.song_count);
            back_button      = (ImageButton)   findViewById(R.id.back_button);
            pause_button     = (ImageButton)   findViewById(R.id.pause_button);
            universal_play   = (ImageButton)   findViewById(R.id.universal_play);
            skip_next        = (ImageButton)   findViewById(R.id.skip_next);
            skip_previous    = (ImageButton)   findViewById(R.id.skip_previous);
            songListView     = (ListView)      findViewById(R.id.song_list_view);

            song_count.setText(musicInfo.getSongs().size()+" songs");
            songListView.setOnItemClickListener((adapterView, view, i, l) -> {
                musicPlayer.play(i);
            });

            universal_play.setOnClickListener(view -> musicPlayer.play(lastPlayed));
            pause_button.setOnClickListener(view -> musicPlayer.pause());
            skip_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    musicPlayer.next();
                }
            });
            skip_previous.setOnClickListener(view -> musicPlayer.previous());
            back_button.setOnClickListener(view -> {
                // goes back to home screen
            });
    }

        private void handleSongs()
        {

            musicInfo.download(new MusicInfoDownloaderCallback() {
                @Override
                public void onDownloaded()
                {
                    runOnUiThread(() -> {
                        populateSongView(musicInfo.getSongs());
                    });
                }

                @Override
                public void onFailure()
                {

                }
            });
        }
        private void populateSongView(List<SongInfo>songs)
        {
            final ListView songListView = findViewById(R.id.song_list_view);
            final SongsListAdapter songListAdapter =
                    new SongsListAdapter(music_screen_activity.this, android.R.layout.simple_list_item_1, songs);
            songListView.setAdapter(songListAdapter);
        }

    }

	
	