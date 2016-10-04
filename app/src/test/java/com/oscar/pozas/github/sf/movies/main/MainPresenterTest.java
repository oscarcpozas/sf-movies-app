package com.oscar.pozas.github.sf.movies.main;

import com.google.common.collect.Lists;
import com.oscar.pozas.github.sf.movies.TestUseCaseScheduler;
import com.oscar.pozas.github.sf.movies.data.source.FilmsDataSource;
import com.oscar.pozas.github.sf.movies.data.source.FilmsRepository;
import com.oscar.pozas.github.sf.movies.domain.UseCaseHandler;
import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.domain.main.usecase.GetFilms;
import com.oscar.pozas.github.sf.movies.ui.contract.MainContract;
import com.oscar.pozas.github.sf.movies.ui.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Mock private FilmsRepository mFilmsRepository;

    @Mock private MainContract.View mMainView;

    private MainPresenter mMainPresenter;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);

        mMainPresenter = givenMainPresenter();

        when(mMainView.isActive()).thenReturn(true);
    }

    private MainPresenter givenMainPresenter() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        GetFilms getFilms = new GetFilms(mFilmsRepository);

        return new MainPresenter(mMainView, useCaseHandler, getFilms);
    }

    @Captor ArgumentCaptor<FilmsDataSource.GetFilmsCallback> mGetFilmsCallbackArgumentCaptor;

    @Test
    public void loadAllFilmsAndLoadIntoView() {
        mMainPresenter.loadLocations(true, 0, 0);

        List<Film> films = Lists.newArrayList(); // Empty for test.

        // verify(mFilmsRepository).getFilms(mGetFilmsCallbackArgumentCaptor.capture());
        // mGetFilmsCallbackArgumentCaptor.getValue().onFilmsLoaded(films);

        verify(mMainView).showMarksInMap(films);
    }

    @Test
    public void searchFilmByNameAndLoadIntoView() {
        mMainPresenter.searchQuery("");

        verify(mFilmsRepository);
    }
    
}