package com.aljazkajtna.kamino.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.aljazkajtna.kamino.AppConstants;
import com.aljazkajtna.kamino.data.database.SWApiDao;
import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Timestamp;
import com.aljazkajtna.kamino.data.web.SWApiWebService;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PlanetRepository {

    private static final String Tag = "PlanetRepository";

    private final SWApiWebService webService;
    private final SWApiDao SWApiDao;
    private final Executor executor;

    @Inject
    public PlanetRepository(SWApiWebService webService, SWApiDao SWApiDao, Executor executor) {
        this.webService = webService;
        this.SWApiDao = SWApiDao;
        this.executor = executor;
    }

    public LiveData<Planet> getPlanet() {
        refreshPlanet();
        return SWApiDao.loadPlanet();
    }

    public void savePlanet(Planet planet) {
        executor.execute(() -> {
            SWApiDao.savePlanet(planet);
        });
    }

    public void likePlanet(Planet planet) {
        planet.setLiked(true);
        planet.setLikes(planet.getLikes()+1);
        savePlanet(planet);
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

                                Planet p = SWApiDao.loadPlanet().getValue();
                                if (p != null && p.isLiked()) {
                                    likePlanet(planet);
                                    return;
                                }
                                savePlanet(planet);
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
