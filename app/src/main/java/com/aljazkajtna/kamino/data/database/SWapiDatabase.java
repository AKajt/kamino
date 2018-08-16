package com.aljazkajtna.kamino.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Resident;
import com.aljazkajtna.kamino.data.pojo.Timestamp;

@Database(entities = {Planet.class, Resident.class, Timestamp.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class SWapiDatabase extends RoomDatabase {

    public abstract SWApiDao swapiDao();

}
