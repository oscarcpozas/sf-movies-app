package com.oscar.pozas.github.sf.movies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.contract.SearchContract;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchFragment extends Fragment implements
        SearchContract.View, SearchView.OnQueryTextListener {

    public static final String SEARCH_FRAGMENT_ID = "SEARCH_FRAGMENT_ID";

    private SearchContract.Presenter mPresenter;

    @BindView(R.id.search_bar_view) SearchView mSearchView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

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

        mSearchView.setOnQueryTextListener(this);
        mSearchView.setIconified(false);

        return root;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.querySearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public void setLoadingIndicatorView(boolean visible) {
        mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
