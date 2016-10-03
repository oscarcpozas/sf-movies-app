package com.oscar.pozas.github.sf.movies.data.source.remote;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilmsRemoteDataSource implements FilmsDataSource {

    public static FilmsRemoteDataSource INSTANCE;

    private final String baseURL = "https://data.sfgov.org/resource/wwmu-gmzc.json";

    private final Geocoder mGeocoder;

    // Prevent direct instantation.
    private FilmsRemoteDataSource(@NonNull Context context) {
        checkNotNull(context);
        mGeocoder = new Geocoder(context);
    }

    public static FilmsRemoteDataSource getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new FilmsRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getFilms(@NonNull GetFilmsCallback callback) {
        final OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder().url(baseURL)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            Gson gson = new Gson();
            List<Film> films = gson.fromJson(response.body().charStream(),
                    new TypeToken<List<Film>>(){}.getType());

            List<Film> filmsResult = parseFilms(films);

            callback.onFilmsLoaded(filmsResult);
        } catch (IOException e) { callback.onFilmsLoadedFail(); }
    }

    @Override
    public void getFilms(String query, GetFilmsCallback callback) {
        // No querys for remotte server
    }

    private List<Film> parseFilms(List<Film> films) { // TODO Improve better logic
        List<Film> filmsResult = new ArrayList<>();
        for(Film film : films) {
            try {
                if(film.getLocation() != null && !film.getLocation().isEmpty()) {
                    final String street = film.getLocation() + ", San Francisco, CA";
                    Log.d("LOCATIONS", street);

                    List<Address> streetLocation = mGeocoder.getFromLocationName(street, 1);

                    if(!streetLocation.isEmpty()) {
                        LatLng location = new LatLng(streetLocation.get(0).getLatitude(),
                                streetLocation.get(0).getLongitude());
                        film.setStreetLocation(location);
                        filmsResult.add(film);
                    }
                }
            } catch (IOException e) {

            }
        }
        return filmsResult;
    }

}
