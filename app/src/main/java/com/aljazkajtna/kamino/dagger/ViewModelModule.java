package com.aljazkajtna.kamino.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.aljazkajtna.kamino.ui.FactoryViewModel;
import com.aljazkajtna.kamino.ui.planet.PlanetScreenModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlanetScreenModel.class)
    abstract ViewModel bindPlanetScreenViewModel(PlanetScreenModel planetScreenModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
