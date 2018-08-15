package com.aljazkajtna.kamino.ui.planet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.repository.PlanetRepository;

public class PlanetScreenModel extends ViewModel {

    private LiveData<Planet> planetLiveData;
    private PlanetRepository planetRepository;

    public PlanetScreenModel(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
        if (planetLiveData != null) {
            return;
        }

        planetLiveData = planetRepository.getPlanet();
    }

    public LiveData<Planet> getPlanetLiveData() {
        return planetLiveData;
    }
}
