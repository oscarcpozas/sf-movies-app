package com.oscar.pozas.github.sf.movies.ui.presenter;

import android.support.annotation.NonNull;

import com.oscar.pozas.github.sf.movies.domain.UseCase;
import com.oscar.pozas.github.sf.movies.domain.UseCaseHandler;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;
import com.oscar.pozas.github.sf.movies.ui.contract.MainContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;  // View reference
    private final GetFilms mGetFilms;           // Use case

    private final UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    public MainPresenter(@NonNull MainContract.View mainView,@NonNull UseCaseHandler useCaseHandler,
                         @NonNull GetFilms getFilms) {
        mMainView = checkNotNull(mainView);
        mUseCaseHandler = checkNotNull(useCaseHandler);
        mGetFilms = checkNotNull(getFilms);

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        loadLocations(false);
    }

    /**
     * @param forceUpdate Pass in true to refresh data over network
     */
    @Override
    public void loadLocations(boolean forceUpdate) {
        GetFilms.RequestValues requestValues = new GetFilms.RequestValues(forceUpdate);

        mUseCaseHandler.execute(mGetFilms, requestValues,
                new UseCase.UseCaseCallback<GetFilms.ResponseValue>() {
            @Override
            public void onSuccess(GetFilms.ResponseValue response) {
                mMainView.showMarksInMap();
            }

            @Override
            public void onError() {

            }
        });

        if(mFirstLoad) mFirstLoad = false;
    }

}
