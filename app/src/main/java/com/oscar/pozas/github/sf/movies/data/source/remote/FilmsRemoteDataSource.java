package com.oscar.pozas.github.sf.movies.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilmsRemoteDataSource implements FilmsDataSource {

    public static FilmsRemoteDataSource INSTANCE;

    // Prevent direct instantation.
    private FilmsRemoteDataSource() {}

    public static FilmsRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FilmsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFilms(@NonNull GetFilmsCallback callback) {
        final OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data.sfgov.org/resource/wwmu-gmzc.json")
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            Gson gson = new Gson();
            List<Film> films = gson.fromJson(response.body().charStream(),
                    new TypeToken<List<Film>>(){}.getType());
            callback.onFilmsLoaded(films);
        } catch (IOException e) { callback.onFilmsLoadedFail(); }
    }

}
