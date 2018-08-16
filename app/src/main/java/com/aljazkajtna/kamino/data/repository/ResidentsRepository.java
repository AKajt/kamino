package com.aljazkajtna.kamino.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.aljazkajtna.kamino.data.database.SWApiDao;
import com.aljazkajtna.kamino.data.pojo.Resident;
import com.aljazkajtna.kamino.data.web.SWApiwebService;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ResidentsRepository {

    private static final String Tag = "ResidentRepository";

    private final SWApiwebService webService;
    private final com.aljazkajtna.kamino.data.database.SWApiDao SWApiDao;
    private final Executor executor;

    @Inject
    public ResidentsRepository(SWApiwebService webService, SWApiDao SWApiDao, Executor executor) {
        this.webService = webService;
        this.SWApiDao = SWApiDao;
        this.executor = executor;
    }

    public LiveData<List<Resident>> getResidents(List<Integer> residents) {
        refreshResidents(residents);
        return SWApiDao.loadResidents();
    }

    private void refreshResidents(List<Integer> residents) {
        executor.execute(() -> {
            for (int i : residents) {
                webService.getResident(i).enqueue(new Callback<Resident>() {
                    @Override
                    public void onResponse(Call<Resident> call, Response<Resident> response) {
                        executor.execute(() -> {
                            if (response.isSuccessful()) {
                                Resident resident = response.body();
                                SWApiDao.saveResident(resident);
                            } else {
                                Log.d(Tag, "Error while fetching data: " + response.code());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Resident> call, Throwable t) {
                        Log.d(Tag, "Fetching data failed: " + t.toString());
                    }
                });
            }

        });

    }

}