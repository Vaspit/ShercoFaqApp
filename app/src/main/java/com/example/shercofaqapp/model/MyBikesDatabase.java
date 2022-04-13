package com.example.shercofaqapp.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Bike.class}, version = 3)
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

            new InitialDataAsyncTask(instance).execute();

        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private BikeDao bikeDao;

        public InitialDataAsyncTask(MyBikesDatabase myBikesDatabase) {

            bikeDao = myBikesDatabase.getBikeDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {

            Bike bike1 = new Bike();
            bike1.setBikeName("Sherco");
            bike1.setBikeModelYear("2019");
            bike1.setBikeEngineType("Two stroke");
            bike1.setBikeEngineVolume("300 cc");
            bike1.setBikeEdition("Factory");

            bikeDao.insert(bike1);

            return null;
        }
    }

}
