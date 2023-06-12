package com.example.wpaccessibilityexample.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Post.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String DATABASE_NAME = "postDb";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDatabase(Context context) {
        if (instance == null) {
             instance = Room.databaseBuilder(context, DatabaseHelper.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract  PostDao postDao();
}
