package com.example.stan.carcatalogue6.diconfig;

import com.example.stan.carcatalogue6.views.CarsList.CarsListContracts;
import com.example.stan.carcatalogue6.views.CarsList.CarsListFragment;
import com.example.stan.carcatalogue6.views.CarsList.CarsListPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CarsListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract CarsListFragment carsListFragment();

    @ActivityScoped
    @Binds
    abstract CarsListContracts.Presenter taskPresenter(CarsListPresenter presenter);
}
