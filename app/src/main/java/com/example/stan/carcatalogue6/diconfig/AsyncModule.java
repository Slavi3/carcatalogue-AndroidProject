package com.example.stan.carcatalogue6.diconfig;
import com.example.stan.carcatalogue6.async.AsyncRunner;
import com.example.stan.carcatalogue6.async.AsyncRunnerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncModule {
    @Provides
    @Singleton
    public AsyncRunner asyncRunner() {
        return new AsyncRunnerImpl();
    }
}
