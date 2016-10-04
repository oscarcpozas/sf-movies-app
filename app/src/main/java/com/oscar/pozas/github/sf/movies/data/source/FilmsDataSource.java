package com.oscar.pozas.github.sf.movies.data.source;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.domain.UseCase;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;

import java.util.List;

public interface FilmsDataSource {

    interface GetFilmsCallback {
        void onFilmsLoaded(List<Film> films);
        void onFilmsLoadedFail();
    }

    void getFilms(GetFilms.RequestValues requestValues, @NonNull GetFilmsCallback callback);

    void getFilms(GetFilms.RequestValues requestValues, @NonNull String query,
                  @NonNull GetFilmsCallback callback);

    void saveFilms(@NonNull List<Film> films);

}
