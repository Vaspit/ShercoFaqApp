package com.example.shercofaqapp.model;

import androidx.databinding.library.baseAdapters.BR;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "bikes_table")
public class Bike extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long bikeId;
    @ColumnInfo(name = "bike_name")
    private String bikeName;
    @ColumnInfo(name = "bike_model_year")
    private String bikeModelYear;
    @ColumnInfo(name = "bike_type")
    private String bikeType;
    @ColumnInfo(name = "bike_engine_type")
    private String bikeEngineType;
    @ColumnInfo(name = "bike_engine_volume")
    private String bikeEngineVolume;
    @ColumnInfo(name = "bike_edition")
    private String bikeEdition;
    @ColumnInfo(name = "bike_image")
    private Integer bikeImage;
    @ColumnInfo(name = "bike_firebase_key")
    private String bikeFirebaseKey;

    @Ignore
    public Bike() {
    }

    public Bike(
            long bikeId,
            String bikeName,
            String bikeModelYear,
            String bikeType,
            String bikeEngineType,
            String bikeEngineVolume,
            String bikeEdition,
            Integer bikeImage,
            String bikeFirebaseKey) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.bikeModelYear = bikeModelYear;
        this.bikeType = bikeType;
        this.bikeEngineType = bikeEngineType;
        this.bikeEngineVolume = bikeEngineVolume;
        this.bikeEdition = bikeEdition;
        this.bikeImage = bikeImage;
        this.bikeFirebaseKey = bikeFirebaseKey;
    }

    @Bindable
    public long getBikeId() {
        return bikeId;
    }

    public void setBikeId(long bikeId) {
        this.bikeId = bikeId;
        notifyPropertyChanged(BR.bikeId);
    }

    @Bindable
    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
        notifyPropertyChanged(BR.bikeName);
    }

    @Bindable
    public String getBikeModelYear() {
        return bikeModelYear;
    }

    public void setBikeModelYear(String bikeModelYear) {
        this.bikeModelYear = bikeModelYear;
        notifyPropertyChanged(BR.bikeModelYear);
    }

    @Bindable
    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
        notifyPropertyChanged(BR.bikeType);
    }

    @Bindable
    public String getBikeEngineType() {
        return bikeEngineType;
    }

    public void setBikeEngineType(String bikeEngineType) {
        this.bikeEngineType = bikeEngineType;
        notifyPropertyChanged(BR.bikeEngineType);
    }

    @Bindable
    public String getBikeEngineVolume() {
        return bikeEngineVolume;
    }

    public void setBikeEngineVolume(String bikeEngineVolume) {
        this.bikeEngineVolume = bikeEngineVolume;
        notifyPropertyChanged(BR.bikeEngineVolume);
    }

    @Bindable
    public String getBikeEdition() {
        return bikeEdition;
    }

    public void setBikeEdition(String bikeEdition) {
        this.bikeEdition = bikeEdition;
        notifyPropertyChanged(BR.bikeEdition);
    }

    @Bindable
    public Integer getBikeImage() {
        return bikeImage;
    }

    public void setBikeImage(Integer bikeImage) {
        this.bikeImage = bikeImage;
        notifyPropertyChanged(BR.bikeImage);
    }

    @Bindable
    public String getBikeFirebaseKey() {
        return bikeFirebaseKey;
    }

    public void setBikeFirebaseKey(String bikeFirebaseKey) {
        this.bikeFirebaseKey = bikeFirebaseKey;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("bikeName", bikeName);
        result.put("bikeModelYear", bikeModelYear);
        result.put("bikeType", bikeType);
        result.put("bikeEngineType", bikeEngineType);
        result.put("bikeEngineVolume", bikeEngineVolume);
        result.put("bikeEdition", bikeEdition);
        result.put("bikeImage", bikeImage);
        result.put("bikeFirebaseKey", bikeFirebaseKey);

        return result;
    }
}
