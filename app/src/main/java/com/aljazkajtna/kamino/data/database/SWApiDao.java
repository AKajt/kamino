package com.aljazkajtna.kamino.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Resident;
import com.aljazkajtna.kamino.data.pojo.Timestamp;

import java.util.List;

@Dao
public interface SWApiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePlanet(Planet planet);

    @Query("SELECT * FROM planet")
    LiveData<Planet> loadPlanet();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveResident(Resident resident);

    @Query("SELECT * FROM resident")
    LiveData<List<Resident>> loadResidents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTimestamp(Timestamp timestamp);

    @Query("SELECT * FROM timestamp")
    Timestamp loadTimestamp();

    @Query("DELETE FROM timestamp")
    void deleteTimestamp();

}
