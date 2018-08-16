package com.aljazkajtna.kamino.ui.residents;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import dagger.android.support.AndroidSupportInjection;

public class ResidentsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ResidentsScreenController viewController;
    private ResidentsScreenModel viewModel;

    public ResidentsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_residents, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewController = new ResidentsScreenController(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResidentsScreenModel.class);
        viewModel.init((List<Integer>) getArguments().getSerializable("residents"));
        viewModel.getResidentLiveData().observe(this, residents -> {
            if (residents == null) return;
            viewController.updateView(residents);
        });
    }

    public void openResident(Resident data) {
        Bundle bundle = new Bundle();
        bundle.putString("resident", data.getName());

        Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment).navigate(R.id.action_residentsFragment_to_residentFragment, bundle);
    }
}
