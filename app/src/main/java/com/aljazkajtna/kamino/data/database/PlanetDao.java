package com.aljazkajtna.kamino.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aljazkajtna.kamino.data.pojo.Planet;

@Dao
public interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Planet planet);

    @Query("SELECT * FROM planet")
    LiveData<Planet> loadPlanet();

}
