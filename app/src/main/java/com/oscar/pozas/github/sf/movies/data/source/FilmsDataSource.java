package com.oscar.pozas.github.sf.movies.data.source;

import com.oscar.pozas.github.sf.movies.domain.main.model.Film;

import java.util.List;

public interface FilmsDataSource {

    interface GetFilmsCallback {
        void onFilmsLoaded(List<Film> films);
        void onFilmsLoadedFail();
    }

    void getFilms(GetFilmsCallback callback);

}
