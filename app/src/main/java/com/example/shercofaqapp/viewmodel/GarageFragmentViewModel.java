package com.example.shercofaqapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shercofaqapp.model.repositories.BikeFireBaseRepository;
import com.example.shercofaqapp.model.repositories.BikeRepository;
import com.example.shercofaqapp.model.Bike;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.List;
import java.util.Objects;

public class GarageFragmentViewModel extends AndroidViewModel {

    BikeRepository bikeRepository;
    BikeFireBaseRepository bikeFireBaseRepository;
    private LiveData<List<Bike>> bikes;
    private LiveData<List<Bike>> bikesWithinFirebase;

    public GarageFragmentViewModel(@NonNull Application application) {
        super(application);

        bikeRepository = new BikeRepository(application);
        bikeFireBaseRepository = new BikeFireBaseRepository();
    }

    public LiveData<List<Bike>> getBikes() {
        bikes = bikeRepository.getBikes();
        return bikes;
    }

    public LiveData<List<Bike>> getBikesFromFirebase() {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        bikeFireBaseRepository.readBikes(userId, (MutableLiveData<List<Bike>>) bikesWithinFirebase);
        return bikesWithinFirebase;
    }

    public void addNewBike(Bike bike, String userId, String key) {
        bikeRepository.insertBike(bike);
        bikeFireBaseRepository.createBike(bike, userId, key);
    }

    public void deleteBike(Bike bike) {
        bikeRepository.deleteBike(bike);
    }

    public void updateBike(Bike bike, String userId, String bikeKey) {
        bikeRepository.updateBike(bike);
        bikeFireBaseRepository.updateBike(bike, userId, bikeKey);
    }

    public Bike getBike(long id) {
        return bikeRepository.getBike(id);
    }

}
