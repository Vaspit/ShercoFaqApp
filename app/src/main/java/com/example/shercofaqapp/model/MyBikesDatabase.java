package com.example.shercofaqapp.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shercofaqapp.R;

@Database(entities = {Bike.class}, version = 5)
public abstract class MyBikesDatabase extends RoomDatabase {

    public static MyBikesDatabase instance;

    public abstract BikeDao getBikeDao();

    public static synchronized MyBikesDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyBikesDatabase.class, "myBikesDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }

        return instance;

    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
