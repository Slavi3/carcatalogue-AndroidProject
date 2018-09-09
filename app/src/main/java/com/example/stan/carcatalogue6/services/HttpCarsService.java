package com.example.stan.carcatalogue6.services;

import com.example.stan.carcatalogue6.models.Car;
import com.example.stan.carcatalogue6.repositories.base.Repository;
import com.example.stan.carcatalogue6.services.base.CarsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HttpCarsService implements CarsService {
    private final Repository<Car> mCarsRepository;

    public HttpCarsService(Repository<Car> superheroesRepository) {
        mCarsRepository = superheroesRepository;
    }

    @Override
    public List<Car> getAllCars() throws IOException {
        return mCarsRepository.getAll();
    }

    @Override
    public Car getDetailsById(int id) throws IOException {
        return mCarsRepository.getById(id);
    }

    @Override
    public List<Car> getFilteredCars(String pattern) throws IOException {
        String patternToLower = pattern.toLowerCase();

        return getAllCars().stream()
                .filter(car -> car.getMake().toLowerCase().contains(patternToLower))
                .collect(Collectors.toList());
    }
}
