package com.aljazkajtna.kamino.ui.planet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.repository.PlanetRepository;

import javax.inject.Inject;

public class PlanetScreenModel extends ViewModel {

    private LiveData<Planet> planetLiveData;
    private PlanetRepository planetRepository;

    @Inject
    public PlanetScreenModel(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public void init() {
        if (planetLiveData != null) {
            return;
        }

        planetLiveData = planetRepository.getPlanet();
    }

    public LiveData<Planet> getPlanetLiveData() {
        return planetLiveData;
    }

    public void likePlanet() {
        Planet planet = planetLiveData.getValue();
        planetRepository.likePlanet(planet);
    }
}
