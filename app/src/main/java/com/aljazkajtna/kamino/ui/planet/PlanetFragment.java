package com.aljazkajtna.kamino.ui.planet;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aljazkajtna.kamino.R;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import dagger.android.support.AndroidSupportInjection;

public class PlanetFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private PlanetScreenController viewController;
    private PlanetScreenModel viewModel;

    public PlanetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewController = new PlanetScreenController(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlanetScreenModel.class);
        viewModel.init();
        viewModel.getPlanetLiveData().observe(this, planet -> {
            if (planet == null) return;
            viewController.updateView(planet);
        });
    }

    public void openImage() {
        Navigation.findNavController(this.getView()).navigate(R.id.action_planetFragment_to_planetPictureFragment);
    }

    public void likePlanet() {
        //todo
    }

    public void viewResidents() {
        //todo
    }
}
