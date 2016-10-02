package com.oscar.pozas.github.sf.movies.data.source;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilmsRepository implements FilmsDataSource {

    private static FilmsRepository INSTANCE = null;

    private final FilmsDataSource mFilmsRemoteDateSource;

    // Prevent direct instantation.
    private FilmsRepository(@NonNull FilmsDataSource filmsRemoteDateSource) {
        mFilmsRemoteDateSource = checkNotNull(filmsRemoteDateSource);
    }

    public static FilmsRepository getInstance(FilmsDataSource filmsRemoteDateSource) {
        if(INSTANCE == null) {
            return new FilmsRepository(filmsRemoteDateSource);
        }
        return INSTANCE;
    }

    public static void destroyFilmsRepository() { INSTANCE = null; }

    @Override
    public void getFilms(@NonNull GetFilmsCallback callback) {
        checkNotNull(callback);


    }

}
