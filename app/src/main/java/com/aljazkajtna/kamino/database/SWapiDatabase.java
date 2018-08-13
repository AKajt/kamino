package com.aljazkajtna.kamino.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Resident;

@Database(entities = {Planet.class, Resident.class}, version = 1)
public abstract class SWapiDatabase extends RoomDatabase {

    public abstract PlanetDao planetDao();

    public abstract ResidentDao residentDao();

}
