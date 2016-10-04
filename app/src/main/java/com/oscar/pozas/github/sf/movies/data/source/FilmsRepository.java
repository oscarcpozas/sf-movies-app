package com.oscar.pozas.github.sf.movies.data.source;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilmsRepository implements FilmsDataSource {

    private static FilmsRepository INSTANCE = null;

    private final FilmsDataSource mFilmsRemoteDateSource;
    private final FilmsDataSource mFilmsLocalDataSource;

    private List<Film> mFilmsCache = null;
    private boolean mCacheCorrupted = true;

    // Prevent direct instantation.
    private FilmsRepository(@NonNull FilmsDataSource filmsRemoteDateSource,
                            @NonNull FilmsDataSource filmsLocalDataSource) {
        mFilmsRemoteDateSource = checkNotNull(filmsRemoteDateSource);
        mFilmsLocalDataSource = checkNotNull(filmsLocalDataSource);
    }

    public static FilmsRepository getInstance(FilmsDataSource filmsRemoteDateSource,
                                              FilmsDataSource filmsLocalDataSource) {
        if(INSTANCE == null) {
            return new FilmsRepository(filmsRemoteDateSource, filmsLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyFilmsRepository() { INSTANCE = null; }

    @Override
    public void getFilms(GetFilms.RequestValues requestValues,
                         @NonNull final GetFilmsCallback callback) {
        checkNotNull(callback);

        if(mFilmsCache != null && !mCacheCorrupted) {
            callback.onFilmsLoaded(mFilmsCache);
            return;
        }

        mFilmsRemoteDateSource.getFilms(requestValues, new GetFilmsCallback() {
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
    public void getFilms(GetFilms.RequestValues requestValues,
                         @NonNull String query, @NonNull GetFilmsCallback callback) {
        checkNotNull(query);
        checkNotNull(callback);

        if(mFilmsCache != null && !mCacheCorrupted) {
            callback.onFilmsLoaded(mFilmsCache);
            return;
        }


    }

    @Override
    public void saveFilms(@NonNull List<Film> films) {
        mFilmsLocalDataSource.saveFilms(films);
    }

}
