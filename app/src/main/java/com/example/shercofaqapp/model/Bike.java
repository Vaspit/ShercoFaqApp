package com.example.shercofaqapp.model;

import androidx.databinding.BaseObservable;
import androidx.room.Entity;

@Entity(tableName = "bikes")
public class Bike extends BaseObservable {

    private int id;
    private String bikeName;
    private String bikeModelYear;
    private String bikeEngineType;
    private String bikeEngineVolume;
    private String bikeEdition;

    public Bike() {
    }

    public Bike(int id, String bikeName, String bikeModelYear, String bikeEngineType,
                String bikeEngineVolume, String bikeEdition) {
        this.id = id;
        this.bikeName = bikeName;
        this.bikeModelYear = bikeModelYear;
        this.bikeEngineType = bikeEngineType;
        this.bikeEngineVolume = bikeEngineVolume;
        this.bikeEdition = bikeEdition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBikeModelYear() {
        return bikeModelYear;
    }

    public void setBikeModelYear(String bikeModelYear) {
        this.bikeModelYear = bikeModelYear;
    }

    public String getBikeEngineType() {
        return bikeEngineType;
    }

    public void setBikeEngineType(String bikeEngineType) {
        this.bikeEngineType = bikeEngineType;
    }

    public String getBikeEngineVolume() {
        return bikeEngineVolume;
    }

    public void setBikeEngineVolume(String bikeEngineVolume) {
        this.bikeEngineVolume = bikeEngineVolume;
    }

    public String getBikeEdition() {
        return bikeEdition;
    }

    public void setBikeEdition(String bikeEdition) {
        this.bikeEdition = bikeEdition;
    }
}
