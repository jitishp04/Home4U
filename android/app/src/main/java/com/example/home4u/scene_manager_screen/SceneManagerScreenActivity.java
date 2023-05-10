package com.example.home4u.scene_manager_screen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.home4u.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SceneManagerScreenActivity extends AppCompatActivity {

    public static final String SCENENAME = "SCENENAME";
    private ImageButton addSceneBtn;

    private ArrayList<SceneData> sceneDatas;

    private String sceneName;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    private int pos;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                sceneName = data.getStringExtra(SCENENAME);

                if (pos != -1) {
                    sceneDatas.set(pos, new SceneData(sceneName, sceneDatas.get(pos).getSceneIcon()));
                    pos = -1;
                } else {
                    sceneDatas.add(new SceneData(sceneName, R.drawable.alarm_icon_32));
                }
                myAdapter.notifyDataSetChanged();

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_manager_screen);

        addSceneBtn = findViewById(R.id.addSceneButton);

        sceneDatas = new ArrayList<SceneData>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SceneManagerScreenActivity.this));
        myAdapter = new MyAdapter(sceneDatas, SceneManagerScreenActivity.this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnClick(SceneManagerScreenActivity.this);
        pos = -1;

        addSceneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SceneManagerScreenActivity.this, TestAddSceneActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        /*
        Code based on Swipe to Delete and Undo in Android RecyclerView
        Author: chaitanyamunje
        Source: https://www.geeksforgeeks.org/swipe-to-delete-and-undo-in-android-recyclerview/
        */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SceneData deletedScene = sceneDatas.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                sceneDatas.remove(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                myAdapter.notifyDataSetChanged();
                Snackbar.make(recyclerView, deletedScene.getSceneName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    public void onClick(View v) {
                        sceneDatas.add(position, deletedScene);
                        myAdapter.notifyItemInserted(position);
                        myAdapter.notifyDataSetChanged();
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onItemClick(int position) {
        SceneData sceneDataList = sceneDatas.get(position);

        Intent intent = new Intent(SceneManagerScreenActivity.this, TestAddSceneActivity.class);
        intent.putExtra("name", sceneDataList.getSceneName());
        pos = position;
        activityResultLauncher.launch(intent);
    }
}