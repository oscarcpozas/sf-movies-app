package com.oscar.pozas.github.sf.movies.ui.contract;

import com.oscar.pozas.github.sf.movies.ui.fragment.BaseView;
import com.oscar.pozas.github.sf.movies.ui.presenter.BasePresenter;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showMarksInMap();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadLocations(boolean forceUpdate);

    }

}
