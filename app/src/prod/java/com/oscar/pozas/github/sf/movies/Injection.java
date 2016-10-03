package com.oscar.pozas.github.sf.movies;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.data.source.FilmsRepository;
import com.oscar.pozas.github.sf.movies.data.source.remote.FilmsRemoteDataSource;
import com.oscar.pozas.github.sf.movies.domain.UseCaseHandler;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    /**
     * Provide repositories.
     */

    public static FilmsRepository getFilmsRepository(@NonNull Context context) {
        checkNotNull(context);
        return FilmsRepository.getInstance(FilmsRemoteDataSource.getInstance(context));
    }

    /**
     *  Provide handler.
     */

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    /**
     * Provide use cases.
     */

    public static GetFilms provideGetFilms(@NonNull Context context) {
        return new GetFilms(getFilmsRepository(context));
    }

}
