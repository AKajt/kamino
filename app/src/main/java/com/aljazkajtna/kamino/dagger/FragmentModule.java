package com.aljazkajtna.kamino.dagger;

import com.aljazkajtna.kamino.ui.planet.PlanetFragment;
import com.aljazkajtna.kamino.ui.planetpicture.PlanetPictureFragment;
import com.aljazkajtna.kamino.ui.resident.ResidentFragment;
import com.aljazkajtna.kamino.ui.residents.ResidentsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract PlanetFragment contributePlanetFragment();

    @ContributesAndroidInjector
    abstract PlanetPictureFragment contributePlanetPictureFragment();

    @ContributesAndroidInjector
    abstract ResidentsFragment contributeResidentsFragment();

    @ContributesAndroidInjector
    abstract ResidentFragment contributeResidentFragment();
}
