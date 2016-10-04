package com.oscar.pozas.github.sf.movies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.contract.SearchContract;

import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchFragment extends Fragment implements SearchContract.View {

    public static final String SEARCH_FRAGMENT_ID = "SEARCH_FRAGMENT_ID";

    private SearchContract.Presenter mPresenter;

    @Override
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static SearchFragment newInstance() { return new SearchFragment(); }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

}
