package com.oscar.pozas.github.sf.movies.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.contract.MainContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View, OnMapReadyCallback {

    public static final String MAIN_FRAGMENT_ID = "MAIN_FRAGMENT_ID";

    private static final LatLng SF = new LatLng(37.7661679, -122.435493);

    private MainContract.Presenter mPresenter;
    private LoadingIndicatorCallback mLoadingCallback;

    private GoogleMap mGMap;
    private static String searchQuery;

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static MainFragment newInstance() { return new MainFragment(); }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoadingCallback = (LoadingIndicatorCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);

        SupportMapFragment gMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.gmap);
        gMapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGMap = googleMap;
        mGMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SF, 11.5f));
    }

    @Override
    public void showMarksInMap() {

    }

    @Override
    public void setLoadingIndicatorView(boolean visible) {
        mLoadingCallback.onVisibilityChange(visible);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    public static void setSearchTextQuery(String query) {
        searchQuery = query;
    }

    public interface LoadingIndicatorCallback {
        void onVisibilityChange(boolean visible);
    }

}
