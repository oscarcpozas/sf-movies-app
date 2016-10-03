package com.oscar.pozas.github.sf.movies.ui.contract;

import com.oscar.pozas.github.sf.movies.domain.main.model.Film;
import com.oscar.pozas.github.sf.movies.ui.fragment.BaseView;
import com.oscar.pozas.github.sf.movies.ui.presenter.BasePresenter;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showMarksInMap(List<Film> films);

        void showSuggestedSearch();

        void setLoadingIndicatorView(boolean visible);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadLocations(boolean forceUpdate);

        void searchQuery(String query);

    }

}
