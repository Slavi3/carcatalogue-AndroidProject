package com.example.stan.carcatalogue6.diconfig;

import com.example.stan.carcatalogue6.http.HttpRequester;
import com.example.stan.carcatalogue6.http.OkHttpHttpRequester;
import com.example.stan.carcatalogue6.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
