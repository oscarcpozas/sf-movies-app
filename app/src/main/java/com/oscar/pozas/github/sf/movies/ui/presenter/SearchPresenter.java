package com.oscar.pozas.github.sf.movies.ui.presenter;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.domain.UseCaseHandler;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;
import com.oscar.pozas.github.sf.movies.ui.contract.SearchContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View mSearchView;  // View reference
    private final GetFilms mGetFilms;               // Use case

    private final UseCaseHandler mUseCaseHandler;

    public SearchPresenter(@NonNull SearchContract.View mainView, @NonNull UseCaseHandler useCaseHandler,
                         @NonNull GetFilms getFilms) {
        mSearchView = checkNotNull(mainView);
        mUseCaseHandler = checkNotNull(useCaseHandler);
        mGetFilms = checkNotNull(getFilms);

        mSearchView.setPresenter(this);
    }

    @Override
    public void start() {

    }

}
