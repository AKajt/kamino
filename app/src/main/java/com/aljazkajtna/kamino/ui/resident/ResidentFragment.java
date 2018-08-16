package com.aljazkajtna.kamino.ui.resident;

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
import com.aljazkajtna.kamino.ui.residents.ResidentsScreenController;
import com.aljazkajtna.kamino.ui.residents.ResidentsScreenModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ResidentFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ResidentScreenController viewController;
    private ResidentScreenModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resident, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewController = new ResidentScreenController(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResidentScreenModel.class);
        viewModel.init(getArguments().getString("resident"));
        viewModel.getResidentLiveData().observe(this, resident -> {
            if (resident == null) return;
            viewController.updateView(resident.get(0));
            RequestOptions options = new RequestOptions().centerCrop();
            Glide.with(ResidentFragment.this).load(resident.get(0).getImageUrl()).apply(options).into(viewController.getImage());
        });
    }
}
