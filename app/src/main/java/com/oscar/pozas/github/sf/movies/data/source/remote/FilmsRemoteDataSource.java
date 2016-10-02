package com.oscar.pozas.github.sf.movies.data.source.remote;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;

import retrofit2.Retrofit;

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
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.sfgov.org")
                .build();
        FilmsDataService service = retrofit.create(FilmsDataService.class);
        service.getListFilmsFromRemote();*/
    }

}
