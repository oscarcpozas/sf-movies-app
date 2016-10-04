package com.oscar.pozas.github.sf.movies.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.appyvet.rangebar.RangeBar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.ui.contract.MainContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View, OnMapReadyCallback {

    public static final String MAIN_FRAGMENT_ID = "MAIN_FRAGMENT_ID";

    private static final LatLng SF = new LatLng(37.7661679, -122.435493);

    private MainContract.Presenter mPresenter;

    @BindView(R.id.sheet_view) LinearLayout mBottomSheetViewgroup;
    @BindView(R.id.rangebar) RangeBar mRangeBarView;
    @BindView(R.id.fab_filter_view) FloatingActionButton button;

    private BottomSheetBehavior bottomSheetBehavior;

    private LoadingIndicatorCallback mLoadingCallback;

    private GoogleMap mGMap;

    private int minValue = 1995;
    private int maxValue = 2016;

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
        ButterKnife.bind(this, root);

        bottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetViewgroup);
        bottomSheetBehavior.setHideable(true);

        mRangeBarView.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex, String leftPinValue,
                                              String rightPinValue) {
                minValue = Integer.parseInt(leftPinValue);
                maxValue = Integer.parseInt(rightPinValue);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if(mPresenter != null) {
                    mPresenter.loadLocations(true, minValue, maxValue);
                }
            }
        });

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
    public void showMarksInMap(List<Film> films) {
        mGMap.clear();
        for(Film film : films) {
            mGMap.addMarker(new MarkerOptions()
                    .position(film.getStreetLocation())
                    .icon(vectorToBitmap(R.drawable.ic_map_marker, Color.parseColor("#A4C639")))
                    .title(film.getTitle()));
        }
    }

    @Override
    public void setFilterSheetView(boolean visible) {
        bottomSheetBehavior.setState(visible ? BottomSheetBehavior.STATE_EXPANDED :
                BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void setLoadingIndicatorView(boolean visible) {
        mLoadingCallback.onVisibilityChange(visible);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    public interface LoadingIndicatorCallback {
        void onVisibilityChange(boolean visible);
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}
