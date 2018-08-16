package com.aljazkajtna.kamino.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.aljazkajtna.kamino.AppConstants;
import com.aljazkajtna.kamino.data.database.SWApiDao;
import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Timestamp;
import com.aljazkajtna.kamino.data.web.SWApiwebService;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PlanetRepository {

    private static final String Tag = "PlanetRepository";

    private final SWApiwebService webService;
    private final SWApiDao SWApiDao;
    private final Executor executor;

    @Inject
    public PlanetRepository(SWApiwebService webService, SWApiDao SWApiDao, Executor executor) {
        this.webService = webService;
        this.SWApiDao = SWApiDao;
        this.executor = executor;
    }

    public LiveData<Planet> getPlanet() {
        refreshPlanet();
        return SWApiDao.loadPlanet();
    }

    private void refreshPlanet() {
        executor.execute(() -> {
            long timestamp = 0;
            if (SWApiDao.loadTimestamp() != null) {
                timestamp = SWApiDao.loadTimestamp().getTimestamp();
            }
            boolean shouldFetch = System.currentTimeMillis() - timestamp > AppConstants.DATA_EXPIRATION_DURATION;
            if (shouldFetch) {
                webService.getPlanet().enqueue(new Callback<Planet>() {
                    @Override
                    public void onResponse(Call<Planet> call, Response<Planet> response) {
                        executor.execute(() -> {
                            if (response.isSuccessful()) {
                                Planet planet = response.body();
                                SWApiDao.deleteTimestamp();
                                Timestamp ts = new Timestamp();
                                ts.setTimestamp(System.currentTimeMillis());
                                SWApiDao.saveTimestamp(ts);
                                SWApiDao.savePlanet(planet);
                            } else {
                                Log.d(Tag, "Error while fetching data: " + response.code());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Planet> call, Throwable t) {
                        Log.d(Tag, "Fetching data failed: " + t.toString());
                    }
                });
            }
        });
    }
}
