package com.aljazkajtna.kamino.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aljazkajtna.kamino.data.pojo.Resident;

import java.util.List;

@Dao
public interface ResidentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Resident resident);

    @Query("SELECT * FROM resident")
    LiveData<List<Resident>> loadResidents();

}
