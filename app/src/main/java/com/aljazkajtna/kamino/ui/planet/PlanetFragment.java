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
import com.aljazkajtna.kamino.data.pojo.Resident;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

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
            RequestOptions options = new RequestOptions().centerCrop();
            Glide.with(PlanetFragment.this).load(planet.getImageUrl()).apply(options).into(viewController.getImage());
        });
    }

    public void openImage() {
        Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment).navigate(R.id.action_planetFragment_to_planetPictureFragment);
    }

    public void likePlanet() {
        viewModel.likePlanet();
    }

    public void viewResidents() {
        Bundle bundle = new Bundle();
        ArrayList<Integer> residents = new ArrayList<>();
        for (String s : viewModel.getPlanetLiveData().getValue().getResidents()) {
            String[] value = s.split("/");
            residents.add(Integer.valueOf(value[value.length-1]));
        }
        bundle.putSerializable("residents", residents);
        Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment).navigate(R.id.action_planetFragment_to_residentsFragment, bundle);
    }
}
