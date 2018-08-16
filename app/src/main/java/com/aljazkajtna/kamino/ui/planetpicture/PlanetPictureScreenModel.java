package com.aljazkajtna.kamino.ui.planetpicture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.repository.PlanetRepository;

import javax.inject.Inject;

class PlanetPictureScreenModel extends ViewModel {

    private LiveData<Planet> planetLiveData;
    private PlanetRepository planetRepository;

    @Inject
    public PlanetPictureScreenModel(PlanetRepository planetRepository) {
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

}
