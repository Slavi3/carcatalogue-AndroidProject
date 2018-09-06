package com.example.stan.carcatalogue6.views.CarsList;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.stan.carcatalogue6.models.Car;
import com.example.stan.carcatalogue6.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsListFragment         extends Fragment
        implements CarsListContracts.View {
    private CarsListContracts.Navigator mNavigator;

    @BindView(R.id.lv_cars)
    ListView mSuperheroesListView;

    @BindView(R.id.loading)
    ProgressBar mLoadingView;

    @BindView(R.id.et_filter)
    EditText mFilterEditText;

    @Inject
    ArrayAdapter<Car> mSuperheroesAdapter;

    private CarsListContracts.Presenter mPresenter;

    @Inject
    public CarsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars_list, container, false);

        // ButterKnife is applied
        ButterKnife.bind(this, view);

        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadCars();
    }

    @OnItemClick(R.id.lv_cars)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       Car car= mSuperheroesAdapter.getItem(position);
        mPresenter.selectCar(car);
    }

    public static CarsListFragment newInstance() {
        return new CarsListFragment();
    }

    @Override
    public void setPresenter(CarsListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCars(List<Car> cars) {
        runOnUi(() -> {
            mSuperheroesAdapter.clear();
            mSuperheroesAdapter.addAll(cars);
        });
    }

    @Override
    public void showEmptyCarsList() {
        runOnUi(() -> Toast.makeText(getContext(),
                "No cars",
                Toast.LENGTH_LONG)
                .show()
        );
    }

    @Override
    public void showError(Exception e) {
        runOnUi(() ->
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                        .show()
        );
    }

    @Override
    public void showLoading() {
        runOnUi(() -> {
            mSuperheroesListView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUi(() -> {
            mSuperheroesListView.setVisibility(View.VISIBLE);
            mLoadingView.setVisibility(View.GONE);
        });
    }

    @Override
    public void showCarDetails(Car car) {
        runOnUi(() -> mNavigator.navigateWith(car));
    }

    void setNavigator(CarsListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    private void runOnUi(Runnable action) {
        getActivity()
                .runOnUiThread(action);
    }

    @OnTextChanged(R.id.et_filter)
    public void onTextChanged() {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.filterCars(pattern);
    }
}
