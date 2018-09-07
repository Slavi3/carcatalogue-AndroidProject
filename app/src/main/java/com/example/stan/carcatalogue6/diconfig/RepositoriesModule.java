package com.example.stan.carcatalogue6.diconfig;


import com.example.stan.carcatalogue6.http.HttpRequester;
import com.example.stan.carcatalogue6.models.Car;
import com.example.stan.carcatalogue6.parsers.base.JsonParser;
import com.example.stan.carcatalogue6.repositories.HttpRepository;
import com.example.stan.carcatalogue6.repositories.base.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public Repository<Car> carRepository(
            @Named("baseServerUrl") String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Car> jsonParser
    ) {
        String url = baseServerUrl + "/cars";
        return new HttpRepository<>(url, httpRequester, jsonParser);
    }
}
