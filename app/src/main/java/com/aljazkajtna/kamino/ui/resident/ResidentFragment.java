package com.aljazkajtna.kamino.ui.resident;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aljazkajtna.kamino.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

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
            Glide.with(ResidentFragment.this).load(resident.get(0).getImageUrl()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    viewController.getNoImage().setVisibility(View.VISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).apply(options).into(viewController.getImage());
        });
    }
}
