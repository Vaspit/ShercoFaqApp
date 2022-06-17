package com.example.shercofaqapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shercofaqapp.model.repositories.BikeFireBaseRepository;
import com.example.shercofaqapp.model.repositories.BikeRepository;
import com.example.shercofaqapp.model.Bike;

import java.util.List;

public class GarageFragmentViewModel extends AndroidViewModel {

    BikeRepository bikeRepository;
    BikeFireBaseRepository bikeFireBaseRepository;
    private LiveData<List<Bike>> bikes;

    public GarageFragmentViewModel(@NonNull Application application) {
        super(application);

        bikeRepository = new BikeRepository(application);

    }

    public LiveData<List<Bike>> getBikes() {

        bikes = bikeRepository.getBikes();
        return bikes;

    }

    public void addNewBike(Bike bike) {

        bikeRepository.insertBike(bike);
//        bikeFireBaseRepository.createBike(bike, userId);

    }

    public void deleteBike(Bike bike) {

        bikeRepository.deleteBike(bike);

    }

    public void updateBike(Bike bike) {

        bikeRepository.updateBike(bike);

    }

    public Bike getBike(long id) {

        return bikeRepository.getBike(id);

    }

}
