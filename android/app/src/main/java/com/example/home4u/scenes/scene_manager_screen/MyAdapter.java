package com.example.home4u.scenes.scene_manager_screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home4u.R;
import com.example.home4u.SceneDataModel;

import java.util.ArrayList;

/*
Code based on CardView using RecyclerView in Android with Example
Author: chaitanyamunje
Source: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
*/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<SceneDataModel> sceneDataList;
    private Context context;

    private SceneManagerScreenActivity onClick;

    public void setOnClick(SceneManagerScreenActivity onClick) {
        this.onClick = onClick;
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }
    public MyAdapter(ArrayList<SceneDataModel> sceneData, SceneManagerScreenActivity activity) {
        this.sceneDataList = sceneData;
        this.context = activity;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.scene_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final SceneDataModel curSceneData = sceneDataList.get(position);
        int pos = position;
        holder.textViewSceneName.setText(curSceneData.getSceneName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(pos);
            }
        });
    }


    @Override
    public int getItemCount() {
        return sceneDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView textViewSceneName;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            icon = view.findViewById(R.id.icon);
            textViewSceneName = view.findViewById(R.id.newSceneName);
        }
    }
}