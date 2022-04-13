package com.example.shercofaqapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shercofaqapp.model.AppRepository;
import com.example.shercofaqapp.model.Bike;

import java.util.List;

public class GarageFragmentViewModel extends AndroidViewModel {

    AppRepository appRepository;
    private LiveData<List<Bike>> bikes;

    public GarageFragmentViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);

    }

    public LiveData<List<Bike>> getBikes() {

        bikes = appRepository.getBikes();
        return bikes;

    }

    public void addNewBike(Bike bike) {

        appRepository.insertBike(bike);

    }

    public void deleteBike(Bike bike) {

        appRepository.deleteBike(bike);

    }

    public void updateBike(Bike bike) {

        appRepository.updateBike(bike);

    }

}
