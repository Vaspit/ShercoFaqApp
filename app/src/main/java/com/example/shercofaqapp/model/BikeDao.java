package com.example.shercofaqapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BikeDao {

    @Insert
    void insert(Bike bike);

    @Update
    void update(Bike bike);

    @Delete
    void delete(Bike bike);

    @Query("SELECT * FROM bikes_table")
    LiveData<List<Bike>> getAllBikes();

    @Query("SELECT * FROM bikes_table WHERE bikeId ==:bikeId")
    Bike getBike(long bikeId);



}
