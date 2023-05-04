package com.example.home4u;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//Database implementation was inspired from: https://www.youtube.com/watch?v=312RhjfetP8
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SCENE_TABLE = "SCENE_TABLE";
    private static final String COLUMN_SCENE_NAME = "SCENE_NAME";
    private static final String COLUMN_START_TIME = "START_TIME";
    private static final String COLUMN_END_TIME = "END_TIME";
    private static final String COLUMN_SET_SECURITY = "SET_SECURITY";
    private static final String COLUMN_PLAY_MUSIC = "PLAY_MUSIC";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "sceneManager.db", null, 1);
    }

    //first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + SCENE_TABLE + " (" + COLUMN_SCENE_NAME + " TEXT PRIMARY KEY, " + COLUMN_START_TIME + " INTEGER, " +
                COLUMN_END_TIME + " INTEGER, " + COLUMN_SET_SECURITY + " BOOLEAN, " + COLUMN_PLAY_MUSIC + " BOOLEAN)";

        db.execSQL(createTableStatement);
    }

    //For updating
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addOne(SceneDataModel sceneDataModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SCENE_NAME, sceneDataModel.getSceneName());
        contentValues.put(COLUMN_START_TIME, sceneDataModel.getStartTime());
        contentValues.put(COLUMN_END_TIME, sceneDataModel.getEndTime());
        contentValues.put(COLUMN_PLAY_MUSIC, sceneDataModel.getPlayMusic());
        contentValues.put(COLUMN_SET_SECURITY, sceneDataModel.getSetSecurity());

        long insert = database.insert(SCENE_TABLE, null, contentValues);
        //test:
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public void updateScene (SceneDataModel sceneDataModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COLUMN_SCENE_NAME, sceneDataModel.getSceneName());
        contentValues.put(COLUMN_START_TIME, sceneDataModel.getStartTime());
        contentValues.put(COLUMN_END_TIME, sceneDataModel.getEndTime());
        contentValues.put(COLUMN_PLAY_MUSIC, sceneDataModel.getPlayMusic());
        contentValues.put(COLUMN_SET_SECURITY, sceneDataModel.getSetSecurity());

        database.update(SCENE_TABLE,contentValues,"COLUMN_SCENE_NAME=?", new String[]{COLUMN_SCENE_NAME});
        database.close();
    }

    //Get all created Scenes stored in db
    @SuppressLint("Range")
    public List<SceneDataModel> getAllScenes() {
        List<SceneDataModel> sceneList = new ArrayList<SceneDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCENE_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                SceneDataModel sceneDataModel = new SceneDataModel();
                sceneDataModel.setSceneName(cursor.getString(cursor.getColumnIndex(COLUMN_SCENE_NAME)));
                sceneList.add(sceneDataModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return sceneList;
    }

    //Delete a scene in db
    public void deleteData(String sceneName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SCENE_TABLE, "SCENE_NAME = ?", new String[]{sceneName});
    }
}

//TODO issues: time is a string variable, work on CRUD, not sure to add the days of the week