package com.oscar.pozas.github.sf.movies.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.orm.SugarRecord;
import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;

import java.util.List;

public class FilmsLocalDataSource implements FilmsDataSource {

    private static FilmsLocalDataSource INSTANCE;

    // Prevent direct instantation.
    public FilmsLocalDataSource(@NonNull Context context) {

    }

    public static FilmsLocalDataSource getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new FilmsLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getFilms(GetFilms.RequestValues requestValues, GetFilmsCallback callback) {  // TODO Improve better logic
        List<Film> films = SugarRecord.listAll(Film.class);
        if(films != null && !films.isEmpty()) {
            callback.onFilmsLoaded(films);
        } else {
            callback.onFilmsLoadedFail();
        }
    }

    @Override
    public void getFilms(GetFilms.RequestValues requestValues, String query,
                         GetFilmsCallback callback) {

    }

    public void saveFilms(List<Film> films) { // TODO Improve better logic
        SugarRecord.deleteAll(Film.class);
        SugarRecord.saveInTx(films);
    }

}
