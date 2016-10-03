package com.oscar.pozas.github.sf.movies.domain.main.usecase;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;
import com.oscar.pozas.github.sf.movies.data.source.FilmsRepository;
import com.oscar.pozas.github.sf.movies.domain.UseCase;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetFilms extends UseCase<GetFilms.RequestValues, GetFilms.ResponseValue> {

    private final FilmsRepository mFilmsRepository;

    public GetFilms(@NonNull FilmsRepository filmsRepository) {
        mFilmsRepository = checkNotNull(filmsRepository);
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        mFilmsRepository.getFilms(new FilmsDataSource.GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(List<Film> films) {
                ResponseValue responseValue = new ResponseValue(films);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onFilmsLoadedFail() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean mForceUpdate;

        public RequestValues(boolean forceUpdate) {
            mForceUpdate = forceUpdate;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Film> mFilms;

        public ResponseValue(@NonNull List<Film> films) {
            mFilms = checkNotNull(films, "films cannot be null!");
        }

        public List<Film> getFilms() {
            return mFilms;
        }
    }

}
