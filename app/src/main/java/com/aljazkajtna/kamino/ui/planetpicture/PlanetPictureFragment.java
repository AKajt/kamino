package com.aljazkajtna.kamino.ui.planetpicture;

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
import com.aljazkajtna.kamino.ui.planet.PlanetFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PlanetPictureFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private PlanetPictureScreenController viewController;
    private PlanetPictureScreenModel viewModel;

    public PlanetPictureFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_picture, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewController = new PlanetPictureScreenController(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlanetPictureScreenModel.class);
        viewModel.init();
        viewModel.getPlanetLiveData().observe(this, planet -> {
            viewController.updateView(planet);
            RequestOptions options = new RequestOptions().centerCrop();
            Glide.with(PlanetPictureFragment.this).load(planet.getImageUrl()).apply(options).into(viewController.getImage());
        });
    }
}
