package com.oscar.pozas.github.sf.movies.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.oscar.pozas.github.sf.movies.Injection;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.fragment.SearchFragment;
import com.oscar.pozas.github.sf.movies.ui.presenter.SearchPresenter;
import com.oscar.pozas.github.sf.movies.utils.ActivityUtils;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        // Create fragment.
        final FragmentManager fm = getSupportFragmentManager();
        SearchFragment fragment = SearchFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fm, fragment, R.id.contentFragment);

        // Create presenter.
        new SearchPresenter(fragment, Injection.provideUseCaseHandler(),
                Injection.provideGetFilms(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
