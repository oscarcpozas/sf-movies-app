package com.oscar.pozas.github.sf.movies.ui.contract;

import com.oscar.pozas.github.sf.movies.ui.fragment.BaseView;
import com.oscar.pozas.github.sf.movies.ui.presenter.BasePresenter;

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicatorView(boolean visible);

    }

    interface Presenter extends BasePresenter {

        void querySearch(String query);

    }

}
