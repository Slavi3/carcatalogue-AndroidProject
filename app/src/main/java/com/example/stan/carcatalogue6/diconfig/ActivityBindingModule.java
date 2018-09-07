package com.example.stan.carcatalogue6.diconfig;


import com.example.stan.carcatalogue6.views.CarsList.CarsListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
            modules = CarsListModule.class
    )
    abstract CarsListActivity carsListActivity();
}