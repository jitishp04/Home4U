package com.example.home4u.scene_manager_screen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.home4u.DatabaseHelper;
import com.example.home4u.R;
import com.example.home4u.SceneDataModel;
import com.example.home4u.scenes.newscenecreator_activity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerScreenActivity extends AppCompatActivity {

    private ImageButton addSceneBtn;

    private ArrayList<SceneDataModel> sceneDataList;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_manager_screen);

        addSceneBtn = findViewById(R.id.addSceneButton);
        dbHelper = new DatabaseHelper(getApplicationContext());
        sceneDataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SceneManagerScreenActivity.this));
        myAdapter = new MyAdapter(sceneDataList, SceneManagerScreenActivity.this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnClick(SceneManagerScreenActivity.this);
        prepareData();

        addSceneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SceneManagerScreenActivity.this, newscenecreator_activity.class);
                startActivity(intent);
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
                SceneDataModel deletedScene = sceneDataList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                SceneDataModel deletedSceneData = dbHelper.retrieveDataOfAScene(deletedScene.getSceneName());

                sceneDataList.remove(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                myAdapter.notifyDataSetChanged();

                //Remove the scene with its relevant data from db
                dbHelper.deleteData(deletedScene.getSceneName());
                Snackbar.make(recyclerView, deletedScene.getSceneName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    public void onClick(View v) {
                        sceneDataList.add(position, deletedScene);

                        myAdapter.notifyItemInserted(position);
                        myAdapter.notifyDataSetChanged();

                        //Add a deleted scene back
                        SceneDataModel deletedScene = new SceneDataModel(deletedSceneData.getSceneName(),
                                deletedSceneData.getStartTime(),
                                deletedSceneData.getEndTime(),
                                deletedSceneData.getSetSecurity(), deletedSceneData.getPlayMusic());
                        dbHelper.addOne(deletedScene);
                        System.out.println(deletedScene.getStartTime());
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    //When user selects/clicks a specific scene, it will go to the add-scene screen with passing the scene name
    public void onItemClick(int position) {
        SceneDataModel sceneData = sceneDataList.get(position);

        Intent intent = new Intent(SceneManagerScreenActivity.this, newscenecreator_activity.class);
        intent.putExtra("name", sceneData.getSceneName());
        startActivity(intent);
    }

    private void prepareData() {
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        List dataList = helper.getAllScenes();
        this.sceneDataList.addAll(dataList);
        myAdapter.notifyDataSetChanged();
    }
}