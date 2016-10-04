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
import com.oscar.pozas.github.sf.movies.domain.UseCase;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;

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
    public void getFilms(GetFilms.RequestValues requestValues, @NonNull GetFilmsCallback callback) {
        final OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder().url(baseURL).build();

        try {
            Response response = httpClient.newCall(request).execute();
            Gson gson = new Gson();
            List<Film> films = gson.fromJson(response.body().charStream(),
                    new TypeToken<List<Film>>(){}.getType());

            List<Film> filmsResult = parseFilms(films, requestValues.getmMinValue(),
                    requestValues.getmMaxValue());

            callback.onFilmsLoaded(filmsResult);
        } catch (IOException e) { callback.onFilmsLoadedFail(); }
    }

    @Override
    public void getFilms(GetFilms.RequestValues requestValues,
                         String query, GetFilmsCallback callback) {
        callback.onFilmsLoadedFail(); // Query not abalible in Remote
    }

    @Override
    public void saveFilms(@NonNull List<Film> films) {

    }

    private List<Film> parseFilms(List<Film> films, int minValue, int maxValue) {
        List<Film> filmsResult = new ArrayList<>();
        for(Film film : films) {
            try {
                if(film.getReleaseYear() >= minValue && film.getReleaseYear() <= maxValue) {
                    if (film.getLocation() != null && !film.getLocation().isEmpty()) {
                        final String street = film.getLocation() + ", San Francisco, CA";
                        Log.d("SFAPP", "location:received:" + street);

                        List<Address> streetLocation = mGeocoder.getFromLocationName(street, 1);

                        if (!streetLocation.isEmpty()) {
                            LatLng location = new LatLng(streetLocation.get(0).getLatitude(),
                                    streetLocation.get(0).getLongitude());
                            film.setStreetLocation(location);
                            filmsResult.add(film);
                        }
                    }
                }
            } catch (IOException e) {

            }
        }
        return filmsResult;
    }

}
