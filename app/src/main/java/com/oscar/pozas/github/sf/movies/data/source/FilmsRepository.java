package com.oscar.pozas.github.sf.movies.data.source;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.domain.main.model.Film;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilmsRepository implements FilmsDataSource {

    private static FilmsRepository INSTANCE = null;

    private final FilmsDataSource mFilmsRemoteDateSource;

    private List<Film> mFilmsCache;

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
    public void getFilms(@NonNull final GetFilmsCallback callback) {
        checkNotNull(callback);

        if(mFilmsCache != null) { // TODO Improve check dirty cache.
            callback.onFilmsLoaded(mFilmsCache);
            return;
        }

        mFilmsRemoteDateSource.getFilms(new GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(List<Film> films) {
                mFilmsCache = films;
                callback.onFilmsLoaded(films);
            }

            @Override
            public void onFilmsLoadedFail() {
                callback.onFilmsLoadedFail();
            }
        });
    }

    @Override
    public void getFilms(String query, GetFilmsCallback callback) {

    }

}
