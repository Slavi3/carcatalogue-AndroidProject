package com.example.stan.carcatalogue6.diconfig;

import com.example.stan.carcatalogue6.models.Car;
import com.example.stan.carcatalogue6.parsers.GsonJsonParser;
import com.example.stan.carcatalogue6.parsers.base.JsonParser;
import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Car> carJsonParser() {
        return new GsonJsonParser<>(Car.class, Car[].class);
    }
}
