package com.aljazkajtna.kamino.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.aljazkajtna.kamino.ui.FactoryViewModel;
import com.aljazkajtna.kamino.ui.planet.PlanetScreenModel;
import com.aljazkajtna.kamino.ui.planetpicture.PlanetPictureScreenModel;
import com.aljazkajtna.kamino.ui.resident.ResidentScreenModel;
import com.aljazkajtna.kamino.ui.residents.ResidentsScreenModel;

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
    @IntoMap
    @ViewModelKey(PlanetPictureScreenModel.class)
    abstract ViewModel bindPlanetPictureScreenModel(PlanetPictureScreenModel planetPictureScreenModel);

    @Binds
    @IntoMap
    @ViewModelKey(ResidentsScreenModel.class)
    abstract ViewModel bindResidentsScreenModel(ResidentsScreenModel residentsScreenModel);

    @Binds
    @IntoMap
    @ViewModelKey(ResidentScreenModel.class)
    abstract ViewModel bindResidentScreenModel(ResidentScreenModel residentsScreenModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
