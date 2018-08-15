package com.aljazkajtna.kamino.dagger;

import com.aljazkajtna.kamino.ui.planet.PlanetFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract PlanetFragment contributePlanetFragment();
}
