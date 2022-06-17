package com.example.shercofaqapp.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.shercofaqapp.model.Bike;
import com.example.shercofaqapp.model.BikeDao;
import com.example.shercofaqapp.model.MyBikesDatabase;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.List;

public class BikeRepository {

    private BikeDao bikeDao;
    private LiveData<List<Bike>> bikes;

    public BikeRepository(Application application) {

        MyBikesDatabase myBikesDatabase = MyBikesDatabase.getInstance(application);
        bikeDao = myBikesDatabase.getBikeDao();

    }

    public LiveData<List<Bike>> getBikes() {

        return bikeDao.getAllBikes();

    }

    public Bike getBike(long id) {

        return bikeDao.getBike(id);

    }

    //Insert methods
    public void insertBike(Bike bike) {

        new InsertBikeAsyncTask(bikeDao).execute(bike);

    }

    private static class InsertBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDao bikeDao;

        public InsertBikeAsyncTask(BikeDao bikeDao) {
            this.bikeDao = bikeDao;
        }

        @Override
        protected Void doInBackground(Bike... bikes) {

            bikeDao.insert(bikes[0]);


            return null;
        }
    }

    //Update methods
    public void updateBike(Bike bike) {

        new UpdateBikeAsyncTask(bikeDao).execute(bike);

    }

    private static class UpdateBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDao bikeDao;

        public UpdateBikeAsyncTask(BikeDao bikeDao) {
            this.bikeDao = bikeDao;
        }

        @Override
        protected Void doInBackground(Bike... bikes) {

            bikeDao.update(bikes[0]);

            return null;
        }
    }

    //Delete methods
    public void deleteBike(Bike bike) {

        new DeleteBikeAsyncTask(bikeDao).execute(bike);

    }

    private static class DeleteBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDao bikeDao;

        public DeleteBikeAsyncTask(BikeDao bikeDao) {
            this.bikeDao = bikeDao;
        }

        @Override
        protected Void doInBackground(Bike... bikes) {

            bikeDao.delete(bikes[0]);

            return null;
        }
    }

}
