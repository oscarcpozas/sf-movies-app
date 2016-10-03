package com.oscar.pozas.github.sf.movies.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.oscar.pozas.github.sf.movies.Injection;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.fragment.MainFragment;
import com.oscar.pozas.github.sf.movies.ui.presenter.MainPresenter;
import com.oscar.pozas.github.sf.movies.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // @BindView(R.id.search_filter_view)
    MaterialSearchView mSearchView;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        // Set up search view.
        mSearchView = (MaterialSearchView) findViewById(R.id.search_filter_view);
        mSearchView.setVoiceSearch(false);
        mSearchView.setOnQueryTextListener(provideSearchViewListener());

        // Create fragment.
        final FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = MainFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fm, fragment, R.id.contentFragment);

        // Create presenter.
        new MainPresenter(fragment, Injection.provideUseCaseHandler(),
                Injection.provideGetFilms(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else onBackPressed();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search_filter);
        mSearchView.setMenuItem(item);

        return true;
    }

    private MaterialSearchView.OnQueryTextListener provideSearchViewListener() {
        return new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainFragment.setSearchTextQuery(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainFragment.setSearchTextQuery(newText);

                return true;
            }
        };
    }

}
